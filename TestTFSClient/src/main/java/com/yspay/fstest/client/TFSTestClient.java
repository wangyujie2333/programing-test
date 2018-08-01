/*
 * 文件名：FSTestClient.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：Cindy
 * 修改时间：2016年12月29日
 * 修改内容：
 */

package com.yspay.fstest.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.common.tfs.DefaultTfsManager;

public class TFSTestClient {
	private static final Log log = LogFactory.getLog(TFSTestClient.class);
	private static DefaultTfsManager tfsManager = null;

	public static void main(String[] args) throws Exception {
		args = new String[2];
		args[1] = "10";
		int leng = args.length;
		if (leng != 2) {
			log.error("Please set right arguments: argument0, target file path; argument1, concurrent thread number");
			return;
		}
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "tfs.xml" });
		tfsManager = (DefaultTfsManager) appContext.getBean("tfsManager");

		InputStream inFile = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("batchDsfData.txt");
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {

			byte[] temp = new byte[1024];
			while (-1 != inFile.read(temp)) {
				byteOut.write(temp);
			}
			byteOut.flush();
		} finally {
			if (inFile != null) {
				try {
					inFile.close();
				} catch (Exception e) {
				}
			}

		}

		log.info("Start write file....");
		Date start = new Date();

		int concurrentNum = Integer.parseInt(args[1]);

		ExecutorService executor = Executors.newFixedThreadPool(concurrentNum);
		CountDownLatch main = new CountDownLatch(concurrentNum);

		for (int i = 0; i < concurrentNum; i++) {
			executor.execute(new Worker(new Integer(i + 1), main, byteOut
					.toByteArray(), args[0]));
		}

		try {
			main.await();
		} catch (InterruptedException e) {
			log.error("", e);
		}

		Date end = new Date();
		log.info("Complete all...., total time(ms):"
				+ (end.getTime() - start.getTime()));
		tfsManager.destroy();
		executor.shutdown();

	}

	public static class Worker implements Runnable {
		private CountDownLatch main;

		// 读取文件的目录地址
		private String retFilePath;

		private byte[] data;

		private int id;

		public Worker(int id, CountDownLatch main, byte[] data,
				String retFilePath) {
			this.main = main;
			this.id = new Integer(id).intValue();
			this.data = data;
			this.retFilePath = retFilePath;
		}

		@Override
		public void run() {
			log.info("Worker{" + id + "} 开始写文件");
			// String fileName = retFilePath;
			Date start = new Date();
			// 写文件到tfs
			String name = tfsManager.saveFile(data, null, null);
			// 读文件到tfs,名字相同，可以看出读写内容是否一致
			// tfsManager.fetchFile(name, null, fileName);
			main.countDown();
			Date end = new Date();
			log.info("=========================Worker写文件{" + id + "}完成,文件名："
					+ name + "   time:" + (end.getTime() - start.getTime()));
		}
	}
}
