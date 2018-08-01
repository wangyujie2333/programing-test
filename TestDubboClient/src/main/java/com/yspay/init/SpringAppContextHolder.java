package com.yspay.init;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * @desc ???spring????????????????ж????????bean
 * 
 *       ??? dao = (???)SpringAppContextHolder.getBean("xxxxxbean");//bean?????
 * 
 *       ????????xml <bean class="com.xxx.xxx.SpringAppContextHolder"
 *       lazy-init="false"/>
 * 
 * @author weisd
 * @version create date ??2010-11-2 ????09:38:14
 * 
 *          http://lixiaorong223.blog.163.com/blog/static/4401162920110105536999
 *          /
 * 
 */
public class SpringAppContextHolder implements ApplicationContextAware {

	// Spring????????????
	private static ApplicationContext applicationContext;

	/**
	 * ???ApplicationContextAware?????????????????????????
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		// Assert.isNull(SpringAppContextHolder.applicationContext,"ApplicationContext alread holden");
		SpringAppContextHolder.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		Assert.notNull(applicationContext,
				"SpringAppContextHolder ApplicationContext  must be not null!");
		return applicationContext;
	}

	/**
	 * ??????? ??????д??bean???????????????
	 * 
	 * @param name
	 * @return Object ????????????????bean?????
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * ???i18n
	 * 
	 * @param code
	 * @return
	 * @throws BeansException
	 */
	public static String getMessage(String code) throws BeansException {
		return applicationContext.getMessage(code, null, Locale.CHINESE);
	}

	// public static void autowireBeanProperties(Object existingBean, int
	// autowireMode, boolean dependencyCheck) {
	// getApplicationContext().getAutowireCapableBeanFactory().autowireBeanProperties(existingBean,
	// autowireMode,
	// dependencyCheck);
	// }

	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	// String a = null;
	// Assert.isNull(a,"ApplicationContext alread holden");
	// Assert.notNull(a,"ApplicationContext must be not null!");
	// System.out.println(a);
	//
	// }

}
