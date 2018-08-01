
/*
* 文件名：AccountTradeDetailChange.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年12月11日
* 修改内容：
*/
package com.ysepay.test.hystrix;

public class AccountTradeDetailChange extends AccountTradeDetailBase
		implements java.io.Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private double amount;
	private double cashAmount;
	private double uncashAmount;
	private double creditAmount;

	private String ref_accountid;
	private String ref_custid;

	// 返回流水
	private String accountseqSn;
	private String accountHoldseqSn;
	// 如果是给的总金额，要返回具体的金额 回填到

	private double holdCashamount;
	private double holdUncashamount;
	private double holdCreditLineamount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public double getUncashAmount() {
		return uncashAmount;
	}

	public void setUncashAmount(double uncashAmount) {
		this.uncashAmount = uncashAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getRef_accountid() {
		return ref_accountid;
	}

	public void setRef_accountid(String ref_accountid) {
		this.ref_accountid = ref_accountid;
	}

	public String getRef_custid() {
		return ref_custid;
	}

	public void setRef_custid(String ref_custid) {
		this.ref_custid = ref_custid;
	}

	public String getAccountseqSn() {
		return accountseqSn;
	}

	public void setAccountseqSn(String accountseqSn) {
		this.accountseqSn = accountseqSn;
	}

	public String getAccountHoldseqSn() {
		return accountHoldseqSn;
	}

	public void setAccountHoldseqSn(String accountHoldseqSn) {
		this.accountHoldseqSn = accountHoldseqSn;
	}

	public double getHoldCashamount() {
		return holdCashamount;
	}

	public void setHoldCashamount(double holdCashamount) {
		this.holdCashamount = holdCashamount;
	}

	public double getHoldUncashamount() {
		return holdUncashamount;
	}

	public void setHoldUncashamount(double holdUncashamount) {
		this.holdUncashamount = holdUncashamount;
	}

	public double getHoldCreditLineamount() {
		return holdCreditLineamount;
	}

	public void setHoldCreditLineamount(double holdCreditLineamount) {
		this.holdCreditLineamount = holdCreditLineamount;
	}
}
