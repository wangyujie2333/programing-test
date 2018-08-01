package com.ysepay.test.hystrix;

public class AccountTradeDetailBase implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String accountid;

	private String custid;
	private String accountType;

	private String tranType;
	private String refsn;

	private String voucherType;
	private String voucherSn;
	private String note;

	private String accountentry;
	private String amountType;

	public String getAccountentry() {
		return accountentry;
	}

	public void setAccountentry(String accountentry) {
		this.accountentry = accountentry;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getVoucherSn() {
		return voucherSn;
	}

	public void setVoucherSn(String voucherSn) {
		this.voucherSn = voucherSn;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getRefsn() {
		return refsn;
	}

	public void setRefsn(String refsn) {
		this.refsn = refsn;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
