
/*
* 文件名：Main.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年1月5日
* 修改内容：
*/

package com.ysepay.test.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysepay.framework.sensitive.SensitiveConverterUtils;
import com.ysepay.test.hibernate.entity.Order;
import com.ysepay.test.hibernate.service.IOrderService;

public class TestHibernateMain {

	private static ApplicationContext context;

	public static void main(String[] args) {

		// 测试基于hibernate的增删改查，保存之前自动脱敏，取出自动解密
		context = new ClassPathXmlApplicationContext("ctx_main.xml");

		IOrderService orderService = context.getBean(IOrderService.class);

		orderService.delete(122l);

		Order order = new Order();
		order.setOrderId(122l);
		order.setUserId(231);
		order.setStatus("Initial");
		String telNo = "13890293339";
		order.setUserTelNo(telNo);
		orderService.insert(order);
		System.out.println("Insert保存：加密前为：" + telNo + "; "
				+ "保存数据库之后，用户电话号码经过脱敏加密为：" + order.getUserTelNo());

		Order searchOrder = orderService.getOrder(122l);
		System.out.println(
				"get查询出来用户电话号码经过脱敏转换为正常信息：" + searchOrder.getUserTelNo());

		Order updatedOrder = orderService.updateOrderStatus(122l, "updated");
		System.out.println("update更新之前用户电话号码为：" + telNo + "; "
				+ "保存数据库之后，用户电话号码经过脱敏加密为：" + updatedOrder.getUserTelNo());

		Order order2 = new Order();
		order2.setOrderId(122l);
		order2.setUserId(231);
		order2.setStatus("Merged");
		telNo = "13890293339";
		order2.setUserTelNo(telNo);
		Order mergedOrder = orderService.update(order2);
		System.out.println("Merge更新之前用户电话号码为：" + telNo + "; "
				+ "保存数据库之后，用户电话号码经过脱敏加密为：" + mergedOrder.getUserTelNo());

		Order orderPersist = new Order();
		orderPersist.setOrderId(123l);
		orderPersist.setUserId(231);
		orderPersist.setStatus("Initial");
		telNo = "13890293339";
		orderPersist.setUserTelNo(telNo);
		orderService.insertByPersist(orderPersist);
		System.out.println("Insert保存：加密前为：" + telNo + "; "
				+ "保存数据库之后，用户电话号码经过脱敏加密为：" + orderPersist.getUserTelNo());

		// 批量查询
		List<Order> orders = orderService
				.getOrderListByUserTelNo("13890293339_测试加密");
		if (orders != null && orders.size() > 0) {
			for (int i = 0; i < orders.size(); i++) {
				System.out.println(
						"批量获取到的对象，用户电话号码为：" + orders.get(i).getUserTelNo());
			}
		}

		// 批量保存
		List<Order> saveList = new ArrayList<Order>();
		for (int i = 0; i < 10; i++) {
			Order saveOrder = new Order();
			saveOrder.setOrderId(130 + i);
			saveOrder.setUserId(231);
			saveOrder.setStatus("Initial");
			saveOrder.setUserTelNo("13890293339");
			saveOrder.setHerTelNo("13890293338");
			saveOrder.setHisTelNo("13890293337");
			saveOrder.setYourTelNo("13890293336");
			saveOrder.setOwnerTelNo("13890293335");
			saveList.add(saveOrder);
		}
		orderService.saveAll(saveList);

		// 批量更新
		orderService.updateAll("13890293339_测试加密");

		// 测试对象脱敏转换性能百万次
		long start = new Date().getTime();
		for (int i = 0; i < 1000000; i++) {
			order = new Order();
			order.setOrderId(122l);
			order.setUserId(231);
			order.setStatus("Initial");
			order.setUserTelNo("13890293339");
			order.setHerTelNo("13890293338");
			order.setHisTelNo("13890293337");
			order.setYourTelNo("13890293336");
			order.setOwnerTelNo("13890293335");

			SensitiveConverterUtils.encodeDBSensitiveFileds(order);
			SensitiveConverterUtils.decodeDBSensitiveFileds(order);
			SensitiveConverterUtils.convertLogMaskSensitiveFileds(order);
		}
		long end = new Date().getTime();
		System.out.println("执行300万次对象脱敏转换，消耗时间（ms）：" + (end - start));
	}

}
