/*
 * 文件名：TTTT.java
 * 版权：Copyright by weisd
 * 修改人：weisd
 * 修改内容：
 */

package com.yspay.dubbo.client.pool.dubbo;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yspay.trade.order.api.ITestApiService;

import com.yspay.init.SpringAppContextHolder;

public class TestClientThreadRun implements Runnable {

	private static final Log log = LogFactory.getLog(TestClientThreadRun.class);

	private CountDownLatch main;

	private int id;
	private int runNum;

	public TestClientThreadRun(int id, CountDownLatch main, int runNum) {
		this.main = main;
		this.id = new Integer(id).intValue();
		this.runNum = runNum;
	}

	@Override
	public void run() {
		log.info("------one-Thread-{" + id + "}(" + runNum + ")----start");
		Date start = new Date();
		try {
			for (int i = 0; i < runNum; i++) {
				// Thread.sleep(20000);
				// log.info("------Thread-{" + id + "}(" + runNum +
				// ")----start");

				String reqUid = UUID.randomUUID().toString()
						.replaceAll("-", "").toUpperCase();
				String msg = "run(" + id + ")";

				ITestApiService testApiService = (ITestApiService) SpringAppContextHolder
						.getBean("testApiService");
				testApiService.queryInfo(reqUid, msg, 0);
				// System.out.println(res);
			}
		} catch (Exception e) {
			log.error("写文件失败！", e);
		} finally {
			Date end = new Date();
			log.info("------one-Thread-{" + id + "}(" + runNum
					+ ")----end, total time(ms):"
					+ (end.getTime() - start.getTime()));
			main.countDown();
		}
	}

}
