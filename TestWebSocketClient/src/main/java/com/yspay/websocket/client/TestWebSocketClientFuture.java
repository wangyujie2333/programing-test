/*
 * 文件名：TestWebSocketClientFuture.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：Cindy
 * 修改时间：2016年8月23日
 * 修改内容：
 */

package com.yspay.websocket.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketFrame;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.client.WebSocketClient;

@WebSocket(maxTextMessageSize = 256 * 1024 * 1024)
public class TestWebSocketClientFuture {
	private static final Log log = LogFactory
			.getLog(TestWebSocketClientFuture.class);

	protected WebSocketClient client;
	private Integer connectionId;
	public Session session;

	public TestWebSocketClientFuture(Integer connectionId,
			WebSocketClient client) {
		this.connectionId = connectionId;
		this.client = client;
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		log.debug("Connect " + connectionId + "Received message: " + msg);
	}

	@OnWebSocketFrame
	public void onFrame(Frame frame) {
		log.debug("Connect " + connectionId + "Received frame: "
				+ frame.getPayload() + " " + frame.getType().name());
	}

	@OnWebSocketConnect
	public void onOpen(Session session) {
		System.out.println("Connect " + connectionId + session.isOpen());
		this.session = session;
		log.debug("Connect " + connectionId + session.isOpen());
	}

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		this.session = null;
		if (statusCode != 1000) {
			log.error("Disconnect " + connectionId.intValue() + "status Code:"
					+ statusCode + ": " + reason);
		} else {
			log.debug("Disconnect " + connectionId.intValue() + " status Code:"
					+ statusCode + ": " + reason);
		}
	}

	@OnWebSocketError
	public void onError(Session session, Throwable cause) {
		this.session = null;
		log.error("Connect " + connectionId + " error:", cause);
	}
}
