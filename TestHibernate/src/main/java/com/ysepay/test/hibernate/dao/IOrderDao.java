
/*
* 文件名：IOrderDao.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年1月5日
* 修改内容：
*/

package com.ysepay.test.hibernate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ysepay.test.hibernate.entity.Order;

public interface IOrderDao {

	Order save(Order order);

	Order getOrder(Long orderId);

	void deleteOrder(Long orderId);

	Order update(Order order);

	Order merge(Order order);

	void refresh(final Object entity);

	void persist(final Object entity) throws DataAccessException;

	void deleteAll(List<Order> entities);

	void saveOrUpdateAll(List<Order> entities);

	List<Order> getList(final String hql, Object param);
}
