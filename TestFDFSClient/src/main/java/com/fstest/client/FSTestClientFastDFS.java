/*
 * 文件名：FSTestClient.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：Cindy
 * 修改时间：2016年12月29日
 * 修改内容：
 */

package com.yspay.fstest.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FSTestClientFastDFS {
	private static final Log log = LogFactory.getLog(FSTestClientFastDFS.class);

	public static void main(String[] args) throws Exception {
		// 设置并发的线程数
		int index = 100;
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

		ExecutorService executor = Executors.newFixedThreadPool(index);
		CountDownLatch main = new CountDownLatch(index);

		for (int i = 0; i < index; i++) {
			executor.execute(
					new Worker(new Integer(i), main, byteOut.toByteArray()));
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

		private byte[] sourceIn;

		private int id;

		public Worker(int id, CountDownLatch main, byte[] sourceIn) {
			this.main = main;
			this.id = new Integer(id).intValue();
			this.sourceIn = sourceIn;
		}

		@Override
		public void run() {
			log.info("Worker{" + id + "} 开始写文件");

			Date start = new Date();
			try {
				// 1、把FastDFS提供的jar包添加到工程中
				// 2、初始化全局配置。加载一个配置文件。
				// ClientGlobal
				// .init("D:\\IdeaProjects\\taobao\\taobao-manager\\taobao-manager-web\\src\\main\\resources\\config\\resource\\client.conf");
				String classPath = new File(
						FSTestClientFastDFS.class.getResource("/").getFile())
								.getCanonicalPath();
				ClientGlobal.init(classPath + File.separator + "client.conf");
				// 3、创建一个TrackerClient对象。
				TrackerClient trackerClient = new TrackerClient();
				// 4、创建一个TrackerServer对象。
				TrackerServer trackerServer = trackerClient.getConnection();
				// 5、声明一个StorageServer对象，null。
				StorageServer storageServer = null;
				// 6、获得StorageClient对象。
				StorageClient storageClient = new StorageClient(trackerServer,
						storageServer);
				// 7、直接调用StorageClient对象方法上传文件即可。
				String[] strings = storageClient.upload_file(sourceIn, "txt",
						null);
				for (String string : strings) {
					log.info(string);
				}

				// 3、创建一个TrackerClient对象。
				TrackerClient trackerClient2 = new TrackerClient();
				// 4、创建一个TrackerServer对象。
				TrackerServer trackerServer2 = trackerClient2.getConnection();
				// 5、声明一个StorageServer对象，null。
				StorageServer storageServer2 = trackerClient2.getFetchStorage(trackerServer2, strings[0], strings[1]);
				// 6、获得StorageClient对象。
				StorageClient storageClient2 = new StorageClient(trackerServer2,
						storageServer2);
				byte[] content = storageClient2.download_file(strings[0],
						strings[1]);
				if (content == null || content.length < 1) {
					throw new Exception("读取文件内容失败！");
				}

				trackerServer.close();
			} catch (Exception e) {
				log.error("写文件失败！", e);
			} finally {
				main.countDown();
			}

			Date end = new Date();
			log.info("Worker{" + id + "} write completed, total time(ms):"
					+ (end.getTime() - start.getTime()));
		}
	}

}
