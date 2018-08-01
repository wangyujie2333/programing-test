/*
 * 文件名：OrderServiceImpl1.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年3月29日
 * 修改内容：
 */

package yspay.trade.order.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yspay.trade.order.test.dao.mapper.OrderBillMapper;
import yspay.trade.order.test.dao.mapper.Test_GuoyMapper;
import yspay.trade.order.test.dao.model.OrderBill;
import yspay.trade.order.test.dao.model.Test_Guoy;
import yspay.trade.order.test.dao.model.Test_Iface;
import yspay.trade.order.test.service.IOrderService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2017年3月29日
 * @see OrderServiceImpl1
 * @since
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderBillMapper orderBillMapper;

	@Autowired
	private Test_GuoyMapper testMapper;

	@Override
	public OrderBill getOrderbill(String tradeSn) {
		return this.orderBillMapper.selectByPrimaryKey(tradeSn);
	}

	@Override
	public void insert(Test_Guoy gDto) {

		this.testMapper.insert(gDto);
	}

	@Override
	public void insertByBatch(List<Test_Iface> list) {

		this.testMapper.insertByBatch(list);
	}

}
