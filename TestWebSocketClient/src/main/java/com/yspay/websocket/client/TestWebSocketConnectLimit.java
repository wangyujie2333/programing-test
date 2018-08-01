/*
 * 文件名：TestWebSocketConnectLimit.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：Cindy
 * 修改时间：2016年8月23日
 * 修改内容：
 */

package com.yspay.websocket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class TestWebSocketConnectLimit {
	private static final Log log = LogFactory
			.getLog(TestWebSocketConnectLimit.class);

	private static Map<Integer, TestWebSocketClientFuture> connectionList = new HashMap<Integer, TestWebSocketClientFuture>();

	private static ExecutorService executor = Executors.newFixedThreadPool(200);

	public static void main(String[] args) {
		int leng = args.length;
		if (leng != 4 && leng != 2) {
			log.error("请设置正确的参数");
			return;
		}
		int maxCon = 100;
		Map<String, String> map = new HashMap<String, String>();

		for (int k = 0; k < leng - 1; k++) {
			map.put(args[k], args[k + 1]);
		}
		if (map.containsKey("-n")) {
			maxCon = Integer.parseInt(map.get("-n"));
		}
		if (!map.containsKey("-a")) {
			log.error("请输入连接地址");
			return;
		}
		String urlParams = map.get("-a");
		SslContextFactory sslContexFactory = new SslContextFactory();
		sslContexFactory.setTrustAll(true);
		WebSocketClient webSocketClient = new WebSocketClient(sslContexFactory,
				executor);

		for (int i = 0; i < maxCon; i++) {
			TestWebSocketClientFuture tsc = new TestWebSocketClientFuture(i,
					webSocketClient);

			// Start WebSocket client thread and upgrage HTTP connection
			try {
				webSocketClient.start();
				ClientUpgradeRequest request = new ClientUpgradeRequest();
				webSocketClient.connect(tsc, getUri(i, urlParams), request);
				connectionList.put(i, tsc);
			} catch (Exception e) {
				log.error("连接失败 ConnectionId:" + i, e);
			}

		}
		while (true) {
			try {
				InputStreamReader stream = new InputStreamReader(System.in);
				BufferedReader bd = new BufferedReader(stream);
				// message 由命令和信息组成 例如:send 123
				String message = bd.readLine();
				String arr[] = message.split(" ");
				if (StringUtil.isNotBlank(message)) {
					if ("send".equalsIgnoreCase(arr[0])) {
						List<Integer> keys = new ArrayList<Integer>(
								connectionList.keySet());
						log.info("连接的设备数量：" + keys.size());
						for (int j = 0; j < keys.size(); j++) {
							TestWebSocketClientFuture s = connectionList
									.get(keys.get(j));
							try {
								if (s != null) {
									s.session.getRemote().sendString(arr[1]);
								}
							} catch (IOException e) {
								log.error("发送消息给连接"+keys.get(j)+"失败：", e);
							}
						}
					} else if ("close".equalsIgnoreCase(arr[0])) {
						String id = arr[1];
						Integer sid = Integer.parseInt(id);
						if (connectionList.containsKey(sid)) {
							TestWebSocketClientFuture sess = connectionList
									.get(sid);
							sess.session.close();
							connectionList.remove(sid);
						}
					} else {
						log.error("请输入正确的格式命令");
					}
				}
			} catch (IOException e) {
				log.error("读取控制台命令失败：", e);
			}
		}

	}

	public static URI getUri(int connectionId, String urlParams)
			throws Exception {
		urlParams = urlParams.replaceAll("#\\{n\\}",
				Integer.toString(connectionId));
		return new URI(urlParams);
	}
}
