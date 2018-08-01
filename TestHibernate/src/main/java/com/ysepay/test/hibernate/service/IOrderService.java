
/*
* 文件名：IOrderService.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年1月5日
* 修改内容：
*/

package com.ysepay.test.hibernate.service;

import java.util.List;

import com.ysepay.test.hibernate.entity.Order;

public interface IOrderService {
	void delete(Long orderId);

	Order insert(Order order);

	Order update(Order order);

	Order getOrder(Long orderId);

	Order updateOrderStatus(Long orderId, String status);

	void refresh(Order order);

	void insertByPersist(Order order);

	List<Order> getOrderListByUserTelNo(String telNo);

	void saveAll(List<Order> entities);

	void updateAll(String userTelNo);

}
