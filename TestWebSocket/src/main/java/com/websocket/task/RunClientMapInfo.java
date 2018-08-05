/*
 * 文件名：HanderMap.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2016年8月18日
 * 修改内容：
 */

package com.yspay.websocket.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.springframework.stereotype.Service;

import com.yspay.websocket.WebSocket;
import com.yspay.websocket.entity.ServerEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2016年8月18日
 * @see RunClientMapInfo
 * @since
 */
@Service("runClientMapInfo")
public class RunClientMapInfo {

	public void run() {
		ConcurrentHashMap<String, ServerEntity> orderMaps = WebSocket.orderMaps;
		List<String> keys = new ArrayList<String>(orderMaps.keySet());
		for (int i = 0; i < keys.size(); i++) {
			Long startTime = orderMaps.get(keys.get(i)).getTime();
			Session se = orderMaps.get(keys.get(i)).getSession();
			Long time = System.currentTimeMillis();
			if ((time - startTime) / 1000 >= 300) {
				new WebSocket().closeServe(se);
			}
		}

	}
}
