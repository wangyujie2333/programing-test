
/*
* 文件名：AccountTradeBatchResponse.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年12月11日
* 修改内容：
*/

package com.ysepay.test.hystrix;

public class AccountTradeResponse {
	private AccountTrade trade;
	private Exception exception;

	public AccountTrade getTrade() {

		return trade;
	}

	public void setTrade(AccountTrade trade) {
		this.trade = trade;
	}

	public Exception getException() {

		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
