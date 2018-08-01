package com.yspay.websocket.entity;

import javax.websocket.Session;

/*
 * 文件名：ServerEntity.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2016年8月16日
 * 修改内容：
 */

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2016年8月16日
 * @see ServerEntity
 * @since
 */

public class ServerEntity {
	private Session session;
	private Long time;

	public Session getSession() {

		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Long getTime() {

		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}
