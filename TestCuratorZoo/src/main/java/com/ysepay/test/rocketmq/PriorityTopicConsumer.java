
/*
* 文件名：PriorityTopicConsumer.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年8月21日
* 修改内容：
*/

package com.ysepay.test.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class PriorityTopicConsumer {
	private static final int ZOOKEEPER_PORT = 2181;

	private static final String ZOOKEEPER_CONNECTION_STRING = "10.213.24.37:"
			+ ZOOKEEPER_PORT;

	private static final String JOB_NAMESPACE = "priority-queue-job";

	public static void main(String[] args) throws MQClientException {
		ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(
				ZOOKEEPER_CONNECTION_STRING, JOB_NAMESPACE);
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
				zkConfig);
		regCenter.init();

		JobCoreConfiguration coreConfig = JobCoreConfiguration
				.newBuilder("javaSimpleJob", "0/5 * * * * ?", 2)
				.shardingItemParameters("0=0,1=1").build();

		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(
				coreConfig, PriorityQueueConsumerJob.class.getCanonicalName());
		new JobScheduler(regCenter,
				LiteJobConfiguration.newBuilder(simpleJobConfig).build())
						.init();

	}

}
