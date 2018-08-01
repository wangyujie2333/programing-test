/*
 * 文件名：IndexController.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2016年8月19日
 * 修改内容：
 */

package com.yspay.websocket.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2016年8月19日
 * @see IndexController
 * @since
 */
@Controller
public class IndexController {
	private Log log = LogFactory.getLog(IndexController.class);

	@RequestMapping(value = "/demo/index.do", params = "method=welcome")
	public ModelAndView index() {
		log.info("跳转首页");
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/demo/index.do", params = "method=showMoreInfo")
	public ModelAndView clientInfo() {
		log.info("跳转批量页面");
		System.out.println("---------------------------");
		return new ModelAndView("clientInfo");
	}

	@RequestMapping(value = "/demo/index.do", params = "method=jumpShowInfo")
	public ModelAndView showInfo() {
		log.info("跳转展示连接着服务端的客户端页面");
		System.out.println("---------------------------");
		return new ModelAndView("showInfo");
	}
}
