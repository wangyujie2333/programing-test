package com.yspay.dubbo.client.pool;

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

public class TestPool extends BaseTest {

	private static final Log logger = LogFactory.getLog(TestPool.class);

	@Resource(name = "testApiService")
	private ITestApiService testApiService;

	@Test
	public void testQueryInfoPool() {
		try {
			// 设置并发的线程数
			int index = 10;
			logger.info("线程【TestClientPoolMain】启动...数量[" + index + "]");
			Date start = new Date();

			ExecutorService executor = Executors.newFixedThreadPool(index);
			CountDownLatch main = new CountDownLatch(index);

			for (int i = 0; i < index; i++) {
				executor.execute(new TestClientThread(new Integer(i), main));
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
			logger.info("执行wen");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}