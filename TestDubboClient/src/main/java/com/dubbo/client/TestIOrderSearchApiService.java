package com.yspay.dubbo.client;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import yspay.trade.order.api.IOrderSearchApiService;
import yspay.trade.order.dto.OrderBillSearchRequestDto;
import yspay.trade.order.dto.OrderIdTradeNoDto;

import com.yspay.base.BaseTest;

public class TestIOrderSearchApiService extends BaseTest {

	private static final Log logger = LogFactory
			.getLog(TestIOrderSearchApiService.class);

	@Resource(name = "orderSearchApiService")
	private IOrderSearchApiService orderSearchApiService;

	@Test
	public void testGetBySrcOrderId() {
		try {
			String srcCustId = "2015041605225867";
			String orderId = "88982668";
			OrderBillSearchRequestDto requestDto = new OrderBillSearchRequestDto();
			requestDto.setSrcCustId(srcCustId);
			requestDto.setOrderId(orderId);
			Object obj = orderSearchApiService.getBySrcOrderId(requestDto);
			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOrderBill() {
		// Object obj = SpringAppContextHolder.getBean("helloService");
		try {
			String tradeSn = "311170502006938362";
			Object obj = orderSearchApiService.getOrderBill(tradeSn);
			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryRefundbill() {
		try {
			String srcCustId = "2015041605225867";
			srcCustId = "2015041605225867";
			String orgOrderid = "88982668";
			// srcCustId = null;
			orgOrderid = null;
			String orgTradeSn = "311170327006344408";
			// orgTradeSn = null;
			// orgOrderid = null;
			String refundOrderid = "323170327006344412";

			OrderIdTradeNoDto idNoDto = null;

			idNoDto = new OrderIdTradeNoDto();

			idNoDto.setSrcCustId(srcCustId);
			idNoDto.setOrderId(orgOrderid);
			idNoDto.setTradeSn(orgTradeSn);

			Object obj = orderSearchApiService.queryRefundbill(idNoDto,
					refundOrderid);

			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOrderBillPayDetailById() {
		try {
			String tradesn = "301170504006960546";
			Short seq = 1;
			Object obj = orderSearchApiService.getOrderBillPayDetailById(
					tradesn, seq);

			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOrderBillPayDetailByPaygateBusisn() {
		try {
			String paygateBusisn = "P3111705020175444723";
			Object obj = orderSearchApiService
					.getOrderBillPayDetailByPaygateBusisn(paygateBusisn);

			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";

			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOrderDetailPayerById() {
		try {
			String tradesn = "311170502006938362";
			Short seq = 1;
			Object obj = orderSearchApiService.getOrderDetailPayerById(tradesn,
					seq);

			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOrderDetailPayerByPaygateBusisn() {
		try {
			String paygateBusisn = "P3111705020175444723";
			// paygateBusisn = null;
			Object obj = orderSearchApiService
					.getOrderDetailPayerByPaygateBusisn(paygateBusisn);

			// String res = JsonUtil.fmtObj2JsonStr(obj);
			String res = "end";
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}