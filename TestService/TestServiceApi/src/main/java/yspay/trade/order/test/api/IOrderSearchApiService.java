/*
 * 文件名：Search.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年3月22日
 * 修改内容：
 */

package yspay.trade.order.test.api;

import hikefa.core.exception.BizException;

import java.util.List;

import yspay.trade.order.test.dto.OrderBillDto;
import yspay.trade.order.test.dto.Test_GuoyDto;
import yspay.trade.order.test.dto.Test_IfaceDto;

public interface IOrderSearchApiService {

	/**
	 * 
	 * 根据交易流水号查询订单
	 * 
	 * @param tradeSn
	 * @return
	 * @throws BizException
	 * @see
	 */
	public OrderBillDto getOrderBill(String tradeSn) throws BizException;

	public void insert(Test_GuoyDto gDto);

	public void insertByBatch(List<Test_IfaceDto> gDto);
}
