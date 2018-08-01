
/*
* 文件名：BatchAccountTradeCommand.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年12月7日
* 修改内容：
*/

package com.ysepay.test.hystrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class BatchAccountTradeCommand
		extends HystrixCommand<List<AccountTradeResponse>> {
	private static final Log log = LogFactory
			.getLog(BatchAccountTradeCommand.class);

	public BatchAccountTradeCommand(List<AccountTrade> batchRequest) {

		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory
						.asKey("BatchAccountTradeCommandGroup"))
				.andCommandKey(HystrixCommandKey.Factory
						.asKey("BatchAccountTradeCommand"))
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
						.withExecutionTimeoutInMilliseconds(10000)
						.withFallbackEnabled(true)
						.withFallbackIsolationSemaphoreMaxConcurrentRequests(
								200))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory
						.asKey("BatchAccountTradeCommandThread"))
				.andThreadPoolPropertiesDefaults(
						HystrixThreadPoolProperties.Setter().withCoreSize(500)
								.withQueueSizeRejectionThreshold(10)));
		this.batchRequest = batchRequest;

	}

	private List<AccountTrade> batchRequest;

	public static AtomicInteger exeCount = new AtomicInteger(0);

	@Override
	protected List<AccountTradeResponse> run() throws Exception {
		String accountid = "";

		StringBuffer detail = new StringBuffer();
		List<AccountTradeResponse> rets = new ArrayList<AccountTradeResponse>();
		for (AccountTrade accountTrade : batchRequest) {
			AccountTradeResponse response = new AccountTradeResponse();
			for (AccountTradeDetailBase change : accountTrade
					.getAccountTranDetailList()) {
				((AccountTradeDetailChange) change)
						.setAccountseqSn("222221111");
			}
			accountid = accountTrade.getAccountid();

			detail.append("批量记账中：" + "thread_id：" + accountTrade.getThreadId()
					+ "; accountid:" + accountTrade.getAccountid()
					+ "; transactionId: " + accountTrade.getAccountTranid()
					+ "\\n");
			response.setTrade(accountTrade);
			rets.add(response);
		}

		log.info("执行批量记账成功, 命令序号：" + exeCount.incrementAndGet() + "; 账户id："
				+ accountid + "; 批记账个数：" + batchRequest.size() + "; 包括的事务明细：\\n"
				+ detail.toString());
		return rets;
	}

	/**
	 * 批量记账失败，进行降级处理，返回异常和原始记账请求
	 */
	protected List<AccountTradeResponse> getFallback() {
		String accountid = "";
		List<AccountTradeResponse> rets = new ArrayList<AccountTradeResponse>();
		StringBuffer detail = new StringBuffer();
		for (AccountTrade accountTrade : batchRequest) {
			AccountTradeResponse response = new AccountTradeResponse();
			rets.add(response);
			response.setException(this
					.getExceptionFromThrowable(this.getExecutionException()));
			response.setTrade(accountTrade);
			accountid = accountTrade.getAccountid();
			detail.append("批量记账中：" + "thread_id：" + accountTrade.getThreadId()
					+ "; accountid:" + accountTrade.getAccountid()
					+ "; transactionId: " + accountTrade.getAccountTranid()
					+ "\\n");
		}

		log.error("执行批量记账失败，进入降级处理, 命令序号：" + exeCount.incrementAndGet()
				+ "; 账户id：" + accountid + "; 批记账个数：" + batchRequest.size()
				+ "; 包括的事务明细：\\n" + detail.toString(),
				this.getExecutionException());

		return rets;
	}

}
