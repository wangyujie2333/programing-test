/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.ysepay.test.curator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.PathAndBytesable;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * <p>
 * A counting semaphore that works across JVMs. All processes in all JVMs that
 * use the same lock path will achieve an inter-process limited set of leases.
 * Further, this semaphore is mostly "fair" - each user will get a lease in the
 * order requested (from ZK's point of view).
 * </p>
 * <p>
 * There are two modes for determining the max leases for the semaphore. In the
 * first mode the max leases is a convention maintained by the users of a given
 * path. In the second mode a {@link SharedCountReader} is used as the method
 * for semaphores of a given path to determine the max leases.
 * </p>
 * <p>
 * If a {@link SharedCountReader} is <b>not</b> used, no internal checks are
 * done to prevent Process A acting as if there are 10 leases and Process B
 * acting as if there are 20. Therefore, make sure that all instances in all
 * processes use the same numberOfLeases value.
 * </p>
 * <p>
 * The various acquire methods return {@link Lease} objects that represent
 * acquired leases. Clients must take care to close lease objects (ideally in a
 * <code>finally</code> block) else the lease will be lost. However, if the
 * client session drops (crash, etc.), any leases held by the client are
 * automatically closed and made available to other clients.
 * </p>
 * <p>
 * Thanks to Ben Bangert (ben@groovie.org) for the algorithm used.
 * </p>
 */
public class MyInterProcessSemaphore {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final CuratorFramework client;
	private final String leasesPath;

	private volatile byte[] nodeData;
	private volatile int maxLeases;

	private static final String LEASE_PARENT = "leases";
	private static final String LEASE_BASE_NAME = "lease-";
	private static final int LEASE_BASE_NAME_LENGTH = LEASE_BASE_NAME.length();

	/**
	 * 把所有产生的租赁保持住，使用过后，统一释放
	 */
	private Collection<Lease> allLeases = new ArrayList<Lease>();

	/**
	 * @param client
	 *            the client
	 * @param path
	 *            path for the semaphore
	 * @param maxLeases
	 *            the max number of leases to allow for this instance
	 */
	public MyInterProcessSemaphore(CuratorFramework client, String path,
			int maxLeases) {
		this(client, path, maxLeases, null);
	}

	/**
	 * @param client
	 *            the client
	 * @param path
	 *            path for the semaphore
	 * @param count
	 *            the shared count to use for the max leases
	 */
	public MyInterProcessSemaphore(CuratorFramework client, String path,
			SharedCountReader count) {
		this(client, path, 0, count);
	}

	private MyInterProcessSemaphore(CuratorFramework client, String path,
			int maxLeases, SharedCountReader count) {
		this.client = client;
		path = PathUtils.validatePath(path);
		this.maxLeases = (count != null) ? count.getCount() : maxLeases;
		leasesPath = ZKPaths.makePath(path, LEASE_PARENT);

		if (count != null) {
			count.addListener(new SharedCountListener() {
				@Override
				public void countHasChanged(SharedCountReader sharedCount,
						int newCount) throws Exception {
					MyInterProcessSemaphore.this.maxLeases = newCount;
				}

				@Override
				public void stateChanged(CuratorFramework client,
						ConnectionState newState) {
					// no need to handle this here - clients should set their
					// own connection state listener
				}
			});
		}
	}

	/**
	 * Set the data to put for the node created by this semaphore. This must be
	 * called prior to calling one of the acquire() methods.
	 *
	 * @param nodeData
	 *            node data
	 */
	public void setNodeData(byte[] nodeData) {
		this.nodeData = (nodeData != null)
				? Arrays.copyOf(nodeData, nodeData.length) : null;
	}

	/**
	 * Return a list of all current nodes participating in the semaphore
	 *
	 * @return list of nodes
	 * @throws Exception
	 *             ZK errors, interruptions, etc.
	 */
	public Collection<String> getParticipantNodes() throws Exception {
		return client.getChildren().forPath(leasesPath);
	}

	/**
	 * Convenience method. Closes all leases in the given collection of leases
	 *
	 * @param leases
	 *            leases to close
	 */
	public void returnAll(Collection<Lease> leases) {
		for (Lease l : leases) {
			CloseableUtils.closeQuietly(l);
		}
	}

	/**
	 * Convenience method. Closes the lease
	 *
	 * @param lease
	 *            lease to close
	 */
	public void returnLease(Lease lease) {
		CloseableUtils.closeQuietly(lease);
	}

	/**
	 * <p>
	 * Acquire a lease. If no leases are available, this method blocks until
	 * either the maximum number of leases is increased or another
	 * client/process closes a lease.
	 * </p>
	 * <p>
	 * The client must close the lease when it is done with it. You should do
	 * this in a <code>finally</code> block.
	 * </p>
	 *
	 * @return the new lease
	 * @throws Exception
	 *             ZK errors, interruptions, etc.
	 */
	public Lease acquire() throws Exception {
		Collection<Lease> leases = acquire(1);
		if (leases != null) {
			return leases.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * Acquire <code>qty</code> leases. If there are not enough leases
	 * available, this method blocks until either the maximum number of leases
	 * is increased enough or other clients/processes close enough leases.
	 * However, this method will only block to a maximum of the time parameters
	 * given. If time expires before all leases are acquired, the subset of
	 * acquired leases are automatically closed.
	 * </p>
	 * <p>
	 * The client must close the leases when it is done with them. You should do
	 * this in a <code>finally</code> block. NOTE: You can use
	 * {@link #returnAll(Collection)} for this.
	 * </p>
	 *
	 * @param qty
	 *            number of leases to acquire
	 * @param time
	 *            time to wait
	 * @param unit
	 *            time unit
	 * @return the new leases or null if time ran out
	 * @throws Exception
	 *             ZK errors, interruptions, etc.
	 */
	public Collection<Lease> acquire(int qty) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
		Date start = new Date();

		Preconditions.checkArgument(qty > 0, "qty cannot be 0");

		ImmutableList.Builder<Lease> builder = ImmutableList.builder();
		boolean success = false;
		try {
			while (qty-- > 0) {
				int retryCount = 0;
				long startMillis = System.currentTimeMillis();
				boolean isDone = false;
				while (!isDone) {
					switch (internalAcquire1Lease(builder)) {
					case CONTINUE: {
						isDone = true;
						break;
					}

					case RETURN_NULL: {
						return null;
					}

					case RETRY_DUE_TO_MISSING_NODE: {
						// gets thrown by internalAcquire1Lease when it can't
						// find the lock node
						// this can happen when the session expires, etc. So, if
						// the retry allows, just try it all again
						if (!client.getZookeeperClient().getRetryPolicy()
								.allowRetry(retryCount++,
										System.currentTimeMillis()
												- startMillis,
										RetryLoop.getDefaultRetrySleeper())) {
							throw new KeeperException.NoNodeException(
									"Sequential path not found - possible session loss");
						}
						// try again
						break;
					}
					}
				}
			}
			success = true;
		} finally {
			if (!success) {
				returnAll(builder.build());
			}

			Date end = new Date();
			log.info(" check get semephore time(ms): start["
					+ format.format(start) + "] end[" + format.format(end) + "]"
					+ (start.getTime() - end.getTime()));
		}

		List<Lease> list = builder.build();
		allLeases.addAll(list);
		return list;
	}

	private enum InternalAcquireResult {
		CONTINUE, RETURN_NULL, RETRY_DUE_TO_MISSING_NODE
	}

	private InternalAcquireResult internalAcquire1Lease(
			ImmutableList.Builder<Lease> builder) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
		Date start = new Date();

		if (client.getState() != CuratorFrameworkState.STARTED) {
			return InternalAcquireResult.RETURN_NULL;
		}

		PathAndBytesable<String> createBuilder = client.create()
				.creatingParentsIfNeeded().withProtection()
				.withMode(CreateMode.EPHEMERAL_SEQUENTIAL);
		String path = (nodeData != null)
				? createBuilder.forPath(
						ZKPaths.makePath(leasesPath, LEASE_BASE_NAME), nodeData)
				: createBuilder
						.forPath(ZKPaths.makePath(leasesPath, LEASE_BASE_NAME));
		String nodeName = ZKPaths.getNodeFromPath(path);
		builder.add(makeLease(path));

		Date end = new Date();

		log.info(" zk create time(ms): start[" + format.format(start) + "] end["
				+ format.format(end) + "]" + (start.getTime() - end.getTime()));

		start = new Date();

		String currentNodeSeq = nodeName.substring(
				nodeName.indexOf(LEASE_BASE_NAME) + LEASE_BASE_NAME_LENGTH);
		List<String> children = client.getChildren().forPath(leasesPath);

		end = new Date();

		log.info(" zk getlist time(ms): start[" + format.format(start)
				+ "] end[" + format.format(end) + "]"
				+ (start.getTime() - end.getTime()));

		start = new Date();
		try {
			int smallSeqNum = 1;
			for (int i = 0; i < children.size(); i++) {
				String tmpNodeName = children.get(i);
				String tmpNodeSeq = tmpNodeName
						.substring(tmpNodeName.indexOf(LEASE_BASE_NAME)
								+ LEASE_BASE_NAME_LENGTH);
				if (currentNodeSeq.compareTo(tmpNodeSeq) > 0) {
					smallSeqNum++;
				}

				if (smallSeqNum > maxLeases) {
					return InternalAcquireResult.RETURN_NULL;
				}
			}
		} finally {
			end = new Date();
			log.info(" check seq time(ms): start[" + format.format(start)
					+ "] end[" + format.format(end) + "]"
					+ (start.getTime() - end.getTime()));
		}

		return InternalAcquireResult.CONTINUE;
	}

	private Lease makeLease(final String path) {
		return new Lease() {
			@Override
			public void close() throws IOException {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
				Date start = new Date();
				try {
					client.delete().guaranteed().forPath(path);
				} catch (KeeperException.NoNodeException e) {
					log.warn("Lease already released", e);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Exception e) {
					throw new IOException(e);
				} finally {
					Date end = new Date();
					log.info(" check delete time(ms): start["
							+ format.format(start) + "] end["
							+ format.format(end) + "]"
							+ (start.getTime() - end.getTime()));
				}
			}

			@Override
			public byte[] getData() throws Exception {
				return client.getData().forPath(path);
			}
		};
	}

	/**
	 * 去掉所有已经产生的信号量
	 *  
	 * @see
	 */
	public void returnAll() {
		this.returnAll(allLeases);
	}
}
