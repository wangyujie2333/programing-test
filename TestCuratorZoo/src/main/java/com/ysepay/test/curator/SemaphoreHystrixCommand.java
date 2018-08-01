
/*
* 文件名：SemaphoreHystrixCommand.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年1月10日
* 修改内容：
*/

package com.ysepay.test.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class SemaphoreHystrixCommand extends HystrixCommand<Lease> {
	private InterProcessSemaphoreV2 semaphore;

	private int reqId;

	public SemaphoreHystrixCommand(InterProcessSemaphoreV2 semaphore,
			int reqId) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory
						.asKey("SemaphoreCommandGroup"))
				.andCommandKey(
						HystrixCommandKey.Factory.asKey("SemaphoreCommand"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withCircuitBreakerErrorThresholdPercentage(20)
						.withMetricsRollingStatisticalWindowBuckets(10)
						.withMetricsRollingStatisticalWindowInMilliseconds(
								10000)
						.withCircuitBreakerSleepWindowInMilliseconds(1000)
						.withCircuitBreakerRequestVolumeThreshold(3)
						.withCircuitBreakerEnabled(true)
						.withExecutionIsolationStrategy(
								ExecutionIsolationStrategy.THREAD)
						.withExecutionIsolationThreadInterruptOnTimeout(true)
						.withExecutionTimeoutInMilliseconds(3500)
						.withFallbackEnabled(true)
						.withFallbackIsolationSemaphoreMaxConcurrentRequests(
								200))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory
						.asKey("SemaphoreCommandThread"))
				.andThreadPoolPropertiesDefaults(
						HystrixThreadPoolProperties.Setter().withCoreSize(100)
								.withQueueSizeRejectionThreshold(100)));

		this.semaphore = semaphore;
		this.reqId = reqId;
	}

	@Override
	protected Lease run() throws Exception {
		if (reqId == 0 || reqId == 1 || reqId == 2 || reqId == 5 || reqId == 6
				|| reqId == 10 || reqId == 11) {
			throw new Exception("TestException");
		} else {
			return semaphore.acquire(3, TimeUnit.SECONDS);
		}
	}

	protected Lease getFallback() {
		return new FallbackPassLease();
	}
}
