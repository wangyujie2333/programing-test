
/*
* 文件名：TestCuratorSephmore.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年1月10日
* 修改内容：
*/

package com.ysepay.test.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.RetryNTimes;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixRuntimeException.FailureType;
import com.netflix.hystrix.exception.HystrixTimeoutException;

public class TestCuratorSemaphore {
	private static final Log log = LogFactory
			.getLog(TestCuratorSemaphore.class);

	private static CuratorFramework client;

	public static void main(String[] args) {
		int leng = args.length;
		if (leng != 4) {
			log.error(
					"Please set right arguments: argument0, zookeeper address; argument1, targetPath; argument2, maxLease; argument3, thread number");
			return;
		}

		String zkAddress = args[0];

		String semaphoreApiPath = args[1];

		int maxLease = Integer.parseInt(args[2]);

		client = CuratorFrameworkFactory.newClient(zkAddress, 20, 10,
				new RetryNTimes(10, 5000));
		client.start();

		log.info("Start test semaphore....");
		Date start = new Date();

		int concurrentNum = Integer.parseInt(args[3]);

		ExecutorService executor = Executors.newFixedThreadPool(concurrentNum);
		CountDownLatch main = new CountDownLatch(concurrentNum);

		for (int i = 0; i < concurrentNum; i++) {
			executor.execute(new Worker(i, main, semaphoreApiPath, maxLease));
			// Worker worker = new Worker(i, main, semaphoreApiPath, maxLease);
			// worker.run();
		}

		try {
			main.await();
		} catch (InterruptedException e) {
			log.error("", e);
		}

		Date end = new Date();

		log.info("Complete all...., total time(ms):"
				+ (end.getTime() - start.getTime()));

		executor.shutdown();

		client.close();

	}

	public static class Worker implements Runnable {
		private CountDownLatch main;

		private int id;

		private int maxLease;

		private String semaphoreApiPath;

		public Worker(int id, CountDownLatch main, String semaphoreApiPath,
				int maxLease) {
			this.semaphoreApiPath = semaphoreApiPath;
			this.maxLease = maxLease;
			this.main = main;
			this.id = new Integer(id).intValue();
		}

		@Override
		public void run() {

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
			Date start = new Date();
			Lease lease = null;
			InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(
					client, semaphoreApiPath, maxLease);
			SemaphoreHystrixCommand semaphoreCommand = null;
			try {
				semaphoreCommand = new SemaphoreHystrixCommand(semaphore, id);
				// Thread.sleep(id * 10l);
				log.info("Worker{" + id + "} 开始申请信号量！");
				lease = semaphoreCommand.execute();
				// lease = semaphore.acquire(5, TimeUnit.SECONDS);
			} catch (Exception e) {
				log.error("执行信号量获取命令失败！", e);
			}

			log.info("Worker{" + id + "}" + "--------isCircuitBreakerOpen: "
					+ semaphoreCommand.isCircuitBreakerOpen());

			try {
				Date endGetSemaphore = new Date();

				if (lease != null) {
					if (lease instanceof FallbackPassLease) {
						Throwable exeException = semaphoreCommand
								.getExecutionException();

						log.info("Worker{" + id + "}"
								+ "Fallback Exception---------------"
								+ exeException.getMessage());

						FailureType failureCause = null;
						if (exeException instanceof HystrixRuntimeException) {
							failureCause = ((HystrixRuntimeException) exeException)
									.getFailureType();
						} else if (exeException instanceof HystrixTimeoutException) {
							failureCause = FailureType.TIMEOUT;
						} else if (exeException instanceof RejectedExecutionException) {
							failureCause = FailureType.REJECTED_THREAD_EXECUTION;
						} else if (exeException instanceof RuntimeException
								&& exeException.getMessage().startsWith(
										"Hystrix circuit short-circuited and is OPEN")) {
							failureCause = FailureType.SHORTCIRCUIT;
						} else {
							failureCause = FailureType.COMMAND_EXCEPTION;
						}

						log.info("Current request: [" + id + "]"
								+ " get semaphore failed(" + failureCause.name()
								+ ") time(ms): start[" + format.format(start)
								+ "] end[" + format.format(endGetSemaphore)
								+ "]" + (start.getTime()
										- endGetSemaphore.getTime()));
					} else {
						log.info("Current request: [" + id + "]"
								+ " get semaphore success time(ms): start["
								+ format.format(start) + "] end["
								+ format.format(endGetSemaphore) + "]"
								+ (start.getTime()
										- endGetSemaphore.getTime()));
					}
				} else {
					log.info("Current request: [" + id + "]"
							+ " get semaphore failed time(ms)"
							+ "调用次数限制，不能超过并发数：" + maxLease + ": start["
							+ format.format(start) + "] end["
							+ format.format(endGetSemaphore) + "]"
							+ (start.getTime() - endGetSemaphore.getTime()));
				}

			} finally {
				if (semaphore != null && lease != null) {
					semaphore.returnLease(lease);
				}

				main.countDown();
			}

			Date end = new Date();

			log.info("Worker{" + id + "} write completed, total time(ms):"
					+ (end.getTime() - start.getTime()));
		}
	}
}
