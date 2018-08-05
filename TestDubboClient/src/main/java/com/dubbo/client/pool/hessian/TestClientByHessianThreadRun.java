/*
 * 文件名：TTTT.java
 * 版权：Copyright by weisd
 * 修改人：weisd
 * 修改内容：
 */

package com.yspay.dubbo.client.pool.hessian;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yspay.trade.order.api.ITestApiService;

import com.caucho.hessian.client.HessianProxyFactory;

public class TestClientByHessianThreadRun implements Runnable {

	private static final Log log = LogFactory
			.getLog(TestClientByHessianThreadRun.class);

	private CountDownLatch main;

	private int id;
	private int runNum;

	public TestClientByHessianThreadRun(int id, CountDownLatch main, int runNum) {
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

				String url_pre = " ";
				url_pre = "http://10.213.24.12:4182/";
				url_pre = "http://10.213.23.44:4180/";
				url_pre = "http://localhost:8088/";

				String url = url_pre + "order-search-service/hessian/testApi";
				if (url_pre.contains("localhost")) {
					url = url_pre + "order-search-service/hessian/testApi";
				}

				String reqUid = UUID.randomUUID().toString()
						.replaceAll("-", "").toUpperCase();
				String msg = "run(" + id + ")";

				HessianProxyFactory factory = new HessianProxyFactory();
				ITestApiService testApiService = (ITestApiService) factory
						.create(ITestApiService.class, url);

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
