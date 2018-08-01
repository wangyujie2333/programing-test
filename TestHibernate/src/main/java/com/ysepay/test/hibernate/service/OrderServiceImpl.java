
/*
* 文件名：OrderService.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年1月5日
* 修改内容：
*/

package com.ysepay.test.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysepay.test.hibernate.dao.IOrderDao;
import com.ysepay.test.hibernate.entity.Order;

@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private IOrderDao orderDao;

	@Transactional
	@Override
	public Order insert(Order order) {
		return orderDao.save(order);
	}

	@Transactional
	@Override
	public void insertByPersist(Order order) {
		orderDao.persist(order);
	}

	@Override
	public void refresh(Order order) {
		this.orderDao.refresh(order);
	}

	@Override
	public Order getOrder(Long orderId) {
		return orderDao.getOrder(orderId);
	}

	@Override
	public List<Order> getOrderListByUserTelNo(String telNo) {
		return orderDao.getList("from Order where userTelNo=? ", telNo);
	}

	@Transactional
	@Override
	public void delete(Long orderId) {
		orderDao.deleteOrder(orderId);
	}

	@Transactional
	@Override
	public Order update(Order order) {
		return orderDao.merge(order);
	}

	@Transactional
	@Override
	public Order updateOrderStatus(Long orderId, String status) {
		Order order = this.orderDao.getOrder(orderId);

		// 此变动将不会保存到数据库
		order.setUserTelNo("1234444333");

		// 重新从数据库获取order内容
		orderDao.refresh(order);

		order.setStatus(status);

		orderDao.update(order);

		return order;
	}

	@Transactional
	@Override
	public void saveAll(List<Order> entities) {
		if (entities == null || entities.size() == 0) {
			return;
		}
		for (int i = 0; i < entities.size(); i++) {
			orderDao.save(entities.get(i));
		}
	}

	@Transactional
	@Override
	public void updateAll(String userTelNo) {
		List<Order> entities = this.orderDao
				.getList("from Order where userTelNo=? ", userTelNo);

		if (entities == null || entities.size() == 0) {
			return;
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).setUserTelNo("88888888888");
		}
		this.orderDao.saveOrUpdateAll(entities);
	}
}
