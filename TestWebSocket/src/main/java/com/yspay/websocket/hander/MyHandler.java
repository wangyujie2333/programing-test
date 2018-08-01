
 /*
 * 文件名：MyHandler.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：Cindy
 * 修改时间：2016年8月17日
 * 修改内容：
 */

package com.yspay.websocket.hander;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler {
	private final Log log = LogFactory.getLog(MyHandler.class);
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug(session.getId());
	}

	
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.debug(message.getPayload());
        try {
			session.sendMessage(new TextMessage("test"));
			session.close();
		} catch (IOException e) {
			log.error("发送消息出错",e);			
		}
    }
}

