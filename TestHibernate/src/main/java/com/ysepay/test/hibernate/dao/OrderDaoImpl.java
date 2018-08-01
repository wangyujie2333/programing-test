
/*
* 文件名：OrderDao.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年1月5日
* 修改内容：
*/

package com.ysepay.test.hibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ysepay.test.hibernate.entity.Order;

@Repository
public class OrderDaoImpl implements IOrderDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Order save(Order order) {
		hibernateTemplate.save(order);
		return order;
	}

	@Override
	public Order update(Order order) {
		hibernateTemplate.update(order);
		return order;
	}

	@Override
	public Order getOrder(Long orderId) {
		return (Order) hibernateTemplate.get(Order.class, orderId);
	}

	@Override
	public void deleteOrder(Long orderId) {
		Order order = this.hibernateTemplate.get(Order.class, orderId);
		if (order != null) {
			this.hibernateTemplate.delete(order);
		}
	}

	@Override
	public Order merge(Order order) {
		return this.hibernateTemplate.merge(order);
	}

	@Override
	public List<Order> getList(final String hql, Object param) {
		return (List<Order>) this.hibernateTemplate.find(hql, param);
	}
	
	@Override
	public void saveOrUpdateAll(List<Order> entities) {
		this.hibernateTemplate.saveOrUpdateAll(entities);
	}

	@Override
	public void deleteAll(List<Order> entities) {
		this.hibernateTemplate.deleteAll(entities);
	}

	@Override
	public void persist(final Object entity) throws DataAccessException {
		this.hibernateTemplate.persist(entity);
	}

	@Override
	public void refresh(final Object entity) {
		this.hibernateTemplate.refresh(entity);
	}
}
