/*
 * 文件名：IOrderService.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年3月29日
 * 修改内容：
 */

package yspay.trade.order.test.service;

import hikefa.core.exception.BizException;

import java.util.List;

import yspay.trade.order.test.dao.model.OrderBill;
import yspay.trade.order.test.dao.model.Test_Guoy;
import yspay.trade.order.test.dao.model.Test_Iface;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2017年3月29日
 * @see IOrderService
 * @since
 */

public interface IOrderService {

	/**
	 * 
	 * 根据交易流水号查询订单
	 * 
	 * @param tradeSn
	 * @return
	 * @throws BizException
	 * @see
	 */
	public OrderBill getOrderbill(String tradeSn);

	public void insert(Test_Guoy gDto);

	public void insertByBatch(List<Test_Iface> list);
}
