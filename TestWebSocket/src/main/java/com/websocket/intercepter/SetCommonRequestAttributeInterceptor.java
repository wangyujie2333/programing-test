/*
 * 文件名：SetStaticResourceUrlInterceptor.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：Cindy
 * 修改时间：2016年4月27日
 * 修改内容：
 */

package com.yspay.websocket.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SetCommonRequestAttributeInterceptor implements HandlerInterceptor {

	/**
	 * 收银台web服务器地址
	 */
	@Value("${webSocket.url}")
	private String webSocketUrl;

	/**
	 * 静态资源地址
	 */
	@Value("${static.resource.url}")
	private String staticUrl;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("webSocketUrl", webSocketUrl);
		request.setAttribute("staticUrl", staticUrl);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// TODO Auto-generated method stub

	}

}
