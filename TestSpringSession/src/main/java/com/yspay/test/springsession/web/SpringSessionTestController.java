/*
 * 文件名：IndexController.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2016年8月19日
 * 修改内容：
 */

package com.yspay.test.springsession.web;

import javax.servlet.http.HttpServletRequest;

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
 * @see SpringSessionTestController
 * @since
 */
@Controller
public class SpringSessionTestController {
	private Log log = LogFactory.getLog(SpringSessionTestController.class);

	@RequestMapping(value = "/session.do", params = "method=addAttribute")
	public String addAttribute(HttpServletRequest request, String key, String value) {
		request.getSession().setAttribute(key, value);
		return "forward:/session.do?method=listAttribute";
	}

	@RequestMapping(value = "/session.do", params = "method=listAttribute")
	public ModelAndView listAttribute() {
		return new ModelAndView("listAttribute");
	}
}
