
/*
* 文件名：TestCuratorSephmore.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年1月10日
* 修改内容：
*/

package com.ysepay.test.hystrix;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.hystrix.HystrixCollapser.Scope;
import com.netflix.hystrix.HystrixCollapser.Setter;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;

/**
 * 
 * 测试Hysrix collapse功能
 * 统计数据对外发布，可以通过http://localhost:8000/hystrix.stream访问
 * 
 * @author Cindy
 * @version 2018年1月2日
 * @see TestHystrixCollapser
 * @since
 */
public class TestHystrixCollapser {
	private static final Log log = LogFactory
			.getLog(TestHystrixCollapser.class);

	static AtomicInteger totalSuccessfullTimes = new AtomicInteger(0);

	static AtomicInteger totalFailedTimes = new AtomicInteger(0);

	static AtomicInteger totalExeCount = new AtomicInteger(0);

	public static void main(String[] args) {
		int length = args.length;
		if (length < 1) {
			log.info("可定制参数: argument0, 模拟并发线程数; " + "argument1, 并发持续时间,单位毫秒 "
					+ "argument2, 记账排队的队列个数（根据账户id分配到不同的队列中）; "
					+ "argument3, 队列中最大排队的记账请求个数（如果到了这个值，就进行一批归并记账）;"
					+ "argument4, 定时从队列中拉取一批记账请求进行批记账,单位毫秒 ;"
					+ "argument5, 定时统计数据对外发布端口");
		}

		// 模拟并发线程数
		int concurrentNum = 500;

		// 记账排队的队列个数（根据账户id分配到不同的队列中）
		int queueNum = 10;

		// 队列中最大排队的记账请求个数（如果到了这个值，就进行一批归并记账）
		int maxRequest = 20;

		// 定时从队列中拉取一批记账请求进行批记账 ,单位毫秒
		int timeInterval = 100;

		// 并发请求持续多久时间
		int durableTime = 100 * 1000;

		if (length > 0)
			concurrentNum = Integer.parseInt(args[0]);

		if (length > 1)
			durableTime = Integer.parseInt(args[1]);

		if (length > 2)
			queueNum = Integer.parseInt(args[2]);

		if (length > 3)
			maxRequest = Integer.parseInt(args[3]);

		if (length > 4)
			timeInterval = Integer.parseInt(args[4]);

		int tomcatPort = 8000;

		if (length > 5)
			tomcatPort = Integer.parseInt(args[5]);

		log.info("start.... 模拟并发线程数: " + concurrentNum + "; 并发持续时间(ms)："
				+ durableTime + "; 记账排队的队列个数（根据账户id分配到不同的队列中）:" + queueNum
				+ "; 队列中最大排队的记账请求个数（如果到了这个值，就进行一批归并记账）:" + maxRequest
				+ "; 定时从队列中拉取一批记账请求进行批记账,单位毫秒 :" + timeInterval
				+ "; 定时统计数据对外发布端口:" + tomcatPort);

		TomcatHttpServer tomcat = new TomcatHttpServer(tomcatPort);

		Date start = new Date();

		ExecutorService executor = Executors.newFixedThreadPool(concurrentNum);
		CountDownLatch main = new CountDownLatch(concurrentNum);

		for (int i = 0; i < concurrentNum; i++) {
			executor.execute(new Worker(i, main, concurrentNum, queueNum,
					maxRequest, timeInterval, durableTime));
		}

		try {
			main.await();
		} catch (InterruptedException e) {
			log.error("", e);
		}

		Date end = new Date();

		log.info("Complete all.... 模拟并发线程数: " + concurrentNum + "; 并发持续时间(ms)："
				+ durableTime + "; 记账排队的队列个数（根据账户id分配到不同的队列中）:" + queueNum
				+ "; 队列中最大排队的记账请求个数（如果到了这个值，就进行一批归并记账）:" + maxRequest
				+ "; 定时从队列中拉取一批记账请求进行批记账,单位毫秒 :" + timeInterval + "; 总执行次数: "
				+ totalExeCount.intValue() + "; 总成功次数："
				+ totalSuccessfullTimes.intValue() + "; 总耗时(ms):"
				+ (end.getTime() - start.getTime()));

		executor.shutdown();
		tomcat.close();
	}

	public static class Worker implements Runnable {
		private CountDownLatch main;

		// 线程序号
		private int id;

		// 总共线程个数
		int concurrentNum = 50;

		// 记账排队的队列个数（根据账户id分配到不同的队列中）
		int queueNum = 10;

		// 队列中最大排队的记账请求个数（如果到了这个值，就进行一批归并记账）
		int maxRequest = 50;

		// 定时从队列中拉取一批记账请求进行批记账 ,单位毫秒
		int timeInterval = 12000;

		// 并发持久时间
		int durableTime = 60 * 1000;

		java.util.Random random = new java.util.Random();

		AtomicInteger successfullTimes = new AtomicInteger(0);

		AtomicInteger failedTimes = new AtomicInteger(0);

		AtomicInteger exeCount = new AtomicInteger(0);

		public Worker(int id, CountDownLatch main, int concurrentNum,
				int queueNum, int maxRequest, int timeInterval,
				int durableTime) {
			this.main = main;
			this.id = id;
			this.concurrentNum = concurrentNum;
			this.queueNum = queueNum;
			this.maxRequest = maxRequest;
			this.timeInterval = timeInterval;
			this.durableTime = durableTime;
		}

		@Override
		public void run() {

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
			Date start = new Date();

			Date end = start;// 初始化结束时间为开始时间

			AccountTradeResponse response = null;
			try {

				while (end.getTime() - start.getTime() < durableTime) {
					Date currentStart = new Date();

					response = null;
					AccountTrade accountTrade = new AccountTrade();

					// 假设随机一个id作为账户id,随机数在2倍队列数范围内，可以模拟出1个账号多次事务记账情况
					int accountId = random.nextInt(queueNum * 2);
					accountTrade.setAccountid(String.valueOf(accountId));

					// 记录一个线程id，打日志方便跟踪
					accountTrade.setThreadId(String.valueOf(id));

					// 事务id，每次记账唯一
					accountTrade.setAccountTranid(UUID.randomUUID().toString());

					accountTrade.setAccountDate(format.format(start));
					accountTrade.setCurrency("CNY");
					accountTrade.setSubsystemCode("testSubSystem");

					AccountTradeDetailChange detail = new AccountTradeDetailChange();
					detail.setAccountid(String.valueOf(accountId));
					detail.setCustid("1234567890");

					detail.setTranType("Order");
					detail.setRefsn("989999090");

					detail.setVoucherSn(UUID.randomUUID().toString());
					detail.setVoucherType("权益类-订单");

					detail.setAmountType("20");// 分金额出账
					detail.setCashAmount(100);

					List<AccountTradeDetailBase> accountTranDetailList = new ArrayList<AccountTradeDetailBase>();
					accountTranDetailList.add(detail);
					accountTrade
							.setAccountTranDetailList(accountTranDetailList);

					try {
						Setter setter = Setter
								.withCollapserKey(HystrixCollapserKey.Factory
										.asKey("AccountTradeCollapser_"
												+ (accountId % queueNum))) // 每个key一个排队，取余数法会把同一个账号会排队在一个队列里面
								.andScope(Scope.GLOBAL)
								.andCollapserPropertiesDefaults(
										HystrixCollapserProperties.Setter()
												.withCollapsingEnabled(true)
												.withRequestCacheEnabled(false)
												.withMaxRequestsInBatch(
														maxRequest)// 队列排队后，只要满maxRequest个就进行归并
												.withTimerDelayInMilliseconds(
														timeInterval));// 队列排队后，未满maxRequest个时，timeInterval作为一个时间窗口归并

						AccountTradeCollapser collapser = new AccountTradeCollapser(
								accountTrade, setter);
						response = collapser.queue().get();
					} catch (Exception e) {
						log.error("执行记账失败", e);
					} finally {
						// 计算当次记账时间
						end = new Date();

						log.info("线程{" + id + "} 执行第 "
								+ exeCount.incrementAndGet() + "次, 此次耗时(ms):"
								+ (end.getTime() - currentStart.getTime()
										+ "; 账户id："
										+ accountTrade.getAccountid()
										+ "; 记账事务id："
										+ accountTrade.getAccountTranid()));

						if (response != null
								&& response.getException() == null) {
							successfullTimes.incrementAndGet();
							totalSuccessfullTimes.incrementAndGet();
						} else {
							failedTimes.incrementAndGet();
							totalFailedTimes.incrementAndGet();
						}

						totalExeCount.incrementAndGet();
					}
				}
			} finally {
				main.countDown();
				log.info("线程{" + id + "} 完成, 总执行次数: " + exeCount.intValue()
						+ "; 总成功次数：" + successfullTimes.intValue()
						+ "; 总耗时(ms):" + (end.getTime() - start.getTime()));
			}
		}
	}
}
