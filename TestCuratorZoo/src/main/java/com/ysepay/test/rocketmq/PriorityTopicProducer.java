
/*
* 文件名：PriorityTopicProducer.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年8月21日
* 修改内容：
*/

package com.ysepay.test.rocketmq;

import java.util.List;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class PriorityTopicProducer {
	public static void main(String[] args) {
		try {
			DefaultMQProducer producer = new DefaultMQProducer(
					"order_msg_producer_group");
			producer.setInstanceName(Long.toString(System.currentTimeMillis()));
			producer.setNamesrvAddr("192.168.0.8:9876");
			producer.start();

			for (int i = 0; i < 300; i++) {
				Message msg = new Message("MockPriorityTopic1", null, "KEY" + i,
						("Hello RocketMQ " + i).getBytes());

				SendResult sendResult = producer.send(msg,
						new MessageQueueSelector() {
							@Override
							public MessageQueue select(List<MessageQueue> mqs,
									Message msg, Object arg) {
								Integer id = (Integer) arg;
								int accountId = id % 2;
								int priority =id % 3;
								
								int index = accountId * (mqs.size() / 2) + priority;
								return mqs.get(index);
							}
						}, i);

				System.out.println(sendResult);
			}

			producer.shutdown();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
