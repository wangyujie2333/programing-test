package com.ysepay.test.hystrix;

import java.util.ArrayList;
import java.util.List;

public class AccountTrade implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String subsystemCode;
	private String accountTranid;
	private String accountid;
	private String threadId;
	public String getThreadId() {
	
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	private int num;
	private String currency;
	private String accountDate;
	private String tradeTime;
	private String ip;
	private List<AccountTradeDetailBase> accountTranDetailList = new ArrayList<AccountTradeDetailBase>();

	public List<AccountTradeDetailBase> getAccountTranDetailList() {
		return accountTranDetailList;
	}

	public void setAccountTranDetailList(
			List<AccountTradeDetailBase> accountTranDetailList) {
		this.accountTranDetailList = accountTranDetailList;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getSubsystemCode() {
		return subsystemCode;
	}

	public void setSubsystemCode(String subsystemCode) {
		this.subsystemCode = subsystemCode;
	}

	public String getAccountTranid() {
		return accountTranid;
	}

	public void setAccountTranid(String accountTranid) {
		this.accountTranid = accountTranid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void addAccountDetail(AccountTradeDetailBase accountTradeDetail) {
		accountTranDetailList.add(accountTradeDetail);
	}

	public String getAccountid() {

		return accountid;

	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;

	}

}
