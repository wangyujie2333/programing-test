package yspay.trade.order.test.dao.mapper;

import yspay.trade.order.test.dao.model.OrderBill;

public interface OrderBillMapper {
	int deleteByPrimaryKey(String tradesn);

	int insert(OrderBill record);

	int insertSelective(OrderBill record);

	OrderBill selectByPrimaryKey(String tradesn);

	int updateByPrimaryKeySelective(OrderBill record);

	int updateByPrimaryKey(OrderBill record);
}