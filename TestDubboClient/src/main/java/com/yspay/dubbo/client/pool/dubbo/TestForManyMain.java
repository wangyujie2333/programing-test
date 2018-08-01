package com.yspay.dubbo.client.pool.dubbo;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import yspay.trade.order.api.ITestApiService;

import com.yspay.base.BaseTest;

public class TestForManyMain extends BaseTest {

	private static final Log logger = LogFactory.getLog(TestForManyMain.class);

	@Resource(name = "testApiService")
	private ITestApiService testApiService;

	@Test
	public void testQueryInfoPool() {
		try {
			// 设置并发的线程数
			int threadPool = 1500;
			int threadNum = 300;
			int runNum = 10;// 每个线程调用次数
			logger.info("线程【TestClientPoolMain】启动...数量[" + threadNum + "]");
			Date start = new Date();

			ExecutorService executor = Executors.newFixedThreadPool(threadPool);
			CountDownLatch main = new CountDownLatch(threadNum);

			for (int i = 0; i < threadNum; i++) {
				executor.execute(new TestClientThreadRun(new Integer(i), main,
						runNum));
			}
			try {
				main.await();
			} catch (InterruptedException e) {
				logger.error("", e);
			}
			Date end = new Date();
			logger.info("所有ALL执行完毕==================Complete all...., total time(ms):"
					+ (end.getTime() - start.getTime()));
			executor.shutdown();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}