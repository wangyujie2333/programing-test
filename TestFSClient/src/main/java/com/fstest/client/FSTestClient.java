
/*
* 文件名：FSTestClient.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2016年12月29日
* 修改内容：
*/

package com.yspay.fstest.client;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FSTestClient {
	private static final Log log = LogFactory.getLog(FSTestClient.class);

	public static void main(String[] args) throws Exception {
		int leng = args.length;
		if (leng != 2) {
			log.error(
					"Please set right arguments: argument0, target file path; argument1, concurrent thread number");
			return;
		}

		InputStream inFile = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("batchDsfData.txt");
		ByteArrayInputStream byteIn;
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
			byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			executor.execute(new Worker(new Integer(i), main, byteIn, args[0]));
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

	public static class Worker implements Runnable {
		private CountDownLatch main;

		private String targetFilePath;

		private InputStream sourceIn;

		private int id;

		public Worker(int id, CountDownLatch main, InputStream sourceIn,
				String targetFilePath) {
			this.main = main;
			this.id = new Integer(id).intValue();
			this.sourceIn = sourceIn;
			this.targetFilePath = targetFilePath;
		}

		@Override
		public void run() {
			log.info("Worker{" + id + "} 开始写文件");

			String fileName = targetFilePath + File.separator
					+ UUID.randomUUID() + ".txt";

			Date start = new Date();
			OutputStream newFile = null;
			try {
				newFile = new BufferedOutputStream(
						new FileOutputStream(fileName));
				byte[] temp = new byte[1024];
				while (-1 != sourceIn.read(temp)) {
					newFile.write(temp);
				}
				newFile.flush();
			} catch (IOException e) {
				log.error("写文件失败！", e);
			} finally {
				if (newFile != null) {
					try {
						newFile.close();
					} catch (IOException e) {
					}
				}

				main.countDown();
			}

			Date end = new Date();
			log.info("Worker{" + id + "} write completed, total time(ms):"
					+ (end.getTime() - start.getTime()));
		}
	}

}
