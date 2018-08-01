/*
 * 文件名：ClientDTO.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2016年8月18日
 * 修改内容：
 */

package com.yspay.websocket.entity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2016年8月18日
 * @see ClientDTO
 * @since
 */

public class ClientDTO {
	private String orderId;
	private String time;

	public String getOrderId() {

		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTime() {

		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
