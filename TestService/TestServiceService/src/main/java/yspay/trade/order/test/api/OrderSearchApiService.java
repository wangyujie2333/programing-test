/*
 * 文件名：ss.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年3月22日
 * 修改内容：
 */

package yspay.trade.order.test.api;

import hikefa.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yspay.trade.order.test.dao.model.OrderBill;
import yspay.trade.order.test.dao.model.Test_Guoy;
import yspay.trade.order.test.dao.model.Test_Iface;
import yspay.trade.order.test.dto.OrderBillDto;
import yspay.trade.order.test.dto.Test_GuoyDto;
import yspay.trade.order.test.dto.Test_IfaceDto;
import yspay.trade.order.test.service.IOrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2017年3月22日
 * @see ss
 * @since
 */
@Service("orderSearchApiService")
public class OrderSearchApiService implements IOrderSearchApiService {

	private Log log = LogFactory.getLog(OrderSearchApiService.class);

	@Autowired
	private Mapper mapper;

	@Autowired
	private IOrderService orderService;

	@Override
	public OrderBillDto getOrderBill(String tradeSn) throws BizException {
		log.info("根据tradeSn:" + tradeSn + "查询订单");

		OrderBill orderBill = this.orderService.getOrderbill(tradeSn);

		OrderBillDto orderBillDto = this.mapper.map(orderBill,
				OrderBillDto.class);
		return orderBillDto;
	}

	@Override
	public void insert(Test_GuoyDto gDto) {
		Test_Guoy gDto1 = this.mapper.map(gDto, Test_Guoy.class);

		this.orderService.insert(gDto1);
	}

	@Override
	public void insertByBatch(List<Test_IfaceDto> gDto) {
		List<Test_Iface> list = new ArrayList<Test_Iface>();
		for (Test_IfaceDto test_GuoyDto : gDto) {
			Test_Iface gto = this.mapper.map(test_GuoyDto, Test_Iface.class);
			list.add(gto);
		}
		this.orderService.insertByBatch(list);
	}

}
