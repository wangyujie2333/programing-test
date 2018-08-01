
/*
* 文件名：PriorityQueueConsumerJob.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年8月23日
* 修改内容：
*/

package com.ysepay.test.rocketmq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.consumer.store.ReadOffsetType;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class PriorityQueueConsumerJob implements SimpleJob {
	private static Map<Integer, DefaultMQPullConsumer> clientConsumerMap = new HashMap<Integer, DefaultMQPullConsumer>();

	public PriorityQueueConsumerJob() {
		System.out.println(
				"job construct: Thread id" + Thread.currentThread().getId());
	}

	private synchronized DefaultMQPullConsumer getDefaultMQPullConsumer(
			int clientId) throws Exception {
		if (clientConsumerMap.get(new Integer(clientId)) == null) {// 首次初始化
			DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(
					"MockPriorityTopicConsumerGroup");
			consumer.setInstanceName("MockPriorityTopicConsumer_" + clientId);
			consumer.setNamesrvAddr("192.168.0.8:9876");
			consumer.setPersistConsumerOffsetInterval(3000);
			consumer.setMessageModel(MessageModel.CLUSTERING);
			consumer.setAllocateMessageQueueStrategy(
					new FiexedAllocateMessageQueueStrategy());
			consumer.start();
			System.out.println("Consumer Started.");
			clientConsumerMap.put(new Integer(clientId), consumer);

			return consumer;
		} else {
			return clientConsumerMap.get(new Integer(clientId));
		}
	}

	@Override
	public void execute(ShardingContext shardingContext) {
		System.out
				.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
						shardingContext.getShardingItem(),
						new SimpleDateFormat("HH:mm:ss").format(new Date()),
						Thread.currentThread().getId(), "SIMPLE"));

		int clientId = shardingContext.getShardingItem(); // 分片id

		try {
			DefaultMQPullConsumer consumer = this
					.getDefaultMQPullConsumer(clientId);

			List<MessageQueue> currentClientMQs = new ArrayList<MessageQueue>();
			Set<MessageQueue> mqs = consumer
					.fetchSubscribeMessageQueues("MockPriorityTopic1");
			for (MessageQueue mq : mqs) {
				if (mq.getQueueId() == clientId * 3
						|| mq.getQueueId() == (clientId * 3 + 1)
						|| mq.getQueueId() == (clientId * 3 + 2))
					currentClientMQs.add(mq);
			}
			Collections.sort(currentClientMQs, new Comparator<MessageQueue>() {

				@Override
				public int compare(MessageQueue o1, MessageQueue o2) {
					return o1.getQueueId() - o2.getQueueId();
				}

			});

			long startTime = System.currentTimeMillis();
			while (System.currentTimeMillis() - startTime < 1000 * 60 * 1) {
				System.out.print("job run time(ms) :"
						+ (System.currentTimeMillis() - startTime));
				for (MessageQueue mq : currentClientMQs) {
					try {
						long offset = getMessageQueueOffset(mq, consumer);
						PullResult pullResult = consumer.pull(mq, null, offset,
								10);
						System.out.println("本次拉取队列(" + mq.getQueueId()
								+ ")的消息开始 queue offset:" + offset);

						boolean isPullHighPriority = true;
						switch (pullResult.getPullStatus()) {
						case FOUND:
							System.out
									.println("本次拉取队列(" + mq.getQueueId()
											+ ")的消息个数为：" + pullResult
													.getMsgFoundList().size()
											+ "，放入线程池处理:");
							for (int i = 0; i < pullResult.getMsgFoundList()
									.size(); i++) {
								MessageExt msg = pullResult.getMsgFoundList()
										.get(i);
								System.out.println("msg.getQueueId():"
										+ msg.getQueueId() + "   offset:"
										+ msg.getQueueOffset() + "  key:"
										+ msg.getKeys());
							}
							putMessageQueueOffset(mq,
									pullResult.getNextBeginOffset(), consumer);
							isPullHighPriority = true;
							break;
						case NO_MATCHED_MSG:
							isPullHighPriority = false;
							break;
						case NO_NEW_MSG:
							isPullHighPriority = false;
							break;
						case OFFSET_ILLEGAL:
							isPullHighPriority = false;
							break;
						default:
							isPullHighPriority = false;
							break;
						}

						if (isPullHighPriority) {
							break;
						}
					} catch (RemotingException e) {

						// TODO Auto-generated catch block
						e.printStackTrace();

					} catch (MQBrokerException e) {

						// TODO Auto-generated catch block
						e.printStackTrace();

					} catch (InterruptedException e) {

						// TODO Auto-generated catch block
						e.printStackTrace();

					}

				}
			}
		} catch (Exception e) {
			System.out.println("Job execute failed! " + e.getStackTrace());
		}

	}

	private void putMessageQueueOffset(MessageQueue mq, long offset,
			DefaultMQPullConsumer consumer) throws MQClientException {
		consumer.getDefaultMQPullConsumerImpl().updateConsumeOffset(mq, offset);
	}

	private long getMessageQueueOffset(MessageQueue mq,
			DefaultMQPullConsumer consumer) throws MQClientException {
		long offset = consumer.getDefaultMQPullConsumerImpl().getOffsetStore()
				.readOffset(mq, ReadOffsetType.READ_FROM_MEMORY);
		if (offset != -1) {
			return offset;
		} else {
			offset = consumer.getDefaultMQPullConsumerImpl().getOffsetStore()
					.readOffset(mq, ReadOffsetType.READ_FROM_STORE);
			if (offset == -1) {
				return 0;
			} else {
				return offset;
			}
		}
	}

}
