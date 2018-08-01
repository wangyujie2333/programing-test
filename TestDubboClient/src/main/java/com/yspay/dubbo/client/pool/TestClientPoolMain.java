package com.yspay.dubbo.client.pool;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestClientPoolMain {
	private static final Log log = LogFactory.getLog(TestClientPoolMain.class);

	public static void main(String[] args) throws Exception {
		// 设置并发的线程数
		int index = 10;
		log.info("线程【TestClientPoolMain】启动...数量[" + index + "]");
		Date start = new Date();

		ExecutorService executor = Executors.newFixedThreadPool(index);
		CountDownLatch main = new CountDownLatch(index);

		for (int i = 0; i < index; i++) {
			executor.execute(new TestClientThread(new Integer(i), main));
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

	}
}
