package com.yspay.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yspay.websocket.entity.ServerEntity;

@ServerEndpoint("/ws")
public class WebSocket {
	private static Log log = LogFactory.getLog(WebSocket.class);
	public static Map<String, String> sessions = new HashMap<String, String>();

	public static ConcurrentHashMap<String, ServerEntity> orderMaps = new ConcurrentHashMap<String, ServerEntity>();

	@OnOpen
	public void onOpen(Session session) {
		log.info("用户：" + session.getId() + "连接成功");
		List<String> list = session.getRequestParameterMap().get("tradeSn");
		String orderId = list.get(0);
		ServerEntity serv = new ServerEntity();
		serv.setSession(session);
		serv.setTime(System.currentTimeMillis());
		orderMaps.put(orderId, serv);
		try {
			session.getBasicRemote().sendText("用户" + session.getId() + "连接成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sessions.put(session.getId(), orderId);
		log.info("当前一共连接了" + sessions.size() + "个用户");
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到用户" + session.getId() + "的消息：" + message);
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		log.info("客户端" + session.getId() + "退出了连接");
		String orderId = sessions.get(session.getId());
		if (sessions.containsKey(session.getId())) {
			sessions.remove(session.getId());
		}
		if (orderMaps.containsKey(orderId)) {
			orderMaps.remove(orderId);
		}
		log.info("当前还剩下" + orderMaps.size() + "个用户");
	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error("客户端" + session.getId() + "由于异常原因断开了连接" + error.getMessage());
	}

	public void closeServe(Session session) {
		try {
			session.getBasicRemote().sendText("close");
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
