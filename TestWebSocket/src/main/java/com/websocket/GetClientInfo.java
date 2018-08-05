package com.yspay.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yspay.websocket.entity.ClientDTO;
import com.yspay.websocket.entity.ServerEntity;

@ServerEndpoint("/ys")
public class GetClientInfo {
	private static Log log = LogFactory.getLog(GetClientInfo.class);
	Map<String, ServerEntity> maps = WebSocket.orderMaps;

	@OnOpen
	public void onOpen(Session session) {
		List<ClientDTO> arr = new ArrayList<ClientDTO>();
		List<String> keys = new ArrayList<>(maps.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			ServerEntity value = maps.get(key);
			log.info("订单号：" + key + "--连接时间:" + value.getTime());
			ClientDTO dto = new ClientDTO();
			dto.setOrderId(key);
			dto.setTime(value.getTime().toString());
			arr.add(dto);
		}

		try {
			// 把map转换成字符串
			ObjectMapper mapper = new ObjectMapper();
			String message = mapper.writeValueAsString(arr);
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			maps.get(message).getSession().close();
			session.getBasicRemote().sendText(message);
			WebSocket.orderMaps.remove(message);
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@OnClose
	public void onClose(Session session) {
	}

	@OnError
	public void onError(Session session, Throwable error) {
	}

}
