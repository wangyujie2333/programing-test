
/*
* 文件名：TomcatHttpServer.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年1月2日
* 修改内容：
*/

package com.ysepay.test.hystrix;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

public class TomcatHttpServer {
	private static final Log logger = LogFactory.getLog(TomcatHttpServer.class);

	private final Tomcat tomcat;

	public TomcatHttpServer(int port) {
		String baseDir = new File(System.getProperty("java.io.tmpdir"))
				.getAbsolutePath();
		tomcat = new Tomcat();
		tomcat.setBaseDir(baseDir);
		tomcat.setPort(port);
		tomcat.getConnector().setProperty("maxThreads", "10");
		tomcat.getConnector().setProperty("maxConnections", "5");

		tomcat.getConnector().setProperty("URIEncoding", "UTF-8");
		tomcat.getConnector().setProperty("connectionTimeout", "60000");

		tomcat.getConnector().setProperty("maxKeepAliveRequests", "-1");
		tomcat.getConnector()
				.setProtocol("org.apache.coyote.http11.Http11NioProtocol");

		Context context = tomcat.addContext("/", baseDir);
		Tomcat.addServlet(context, "HystrixMetricsStreamServlet",
				new HystrixMetricsStreamServlet());
		context.addServletMapping("/hystrix.stream",
				"HystrixMetricsStreamServlet");

		try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new IllegalStateException(
					"Failed to start tomcat server at " + port, e);
		}
	}

	public void close() {
		try {
			tomcat.stop();
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}
}
