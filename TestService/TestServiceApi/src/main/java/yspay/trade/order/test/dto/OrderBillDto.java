/*
 * 文件名：OrderBillDto.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年3月24日
 * 修改内容：
 */

package yspay.trade.order.test.dto;

import java.util.Date;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2017年3月24日
 * @see OrderBillDto
 * @since
 */

public class OrderBillDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String tradesn;
	private String srcUsercode;
	private String srcCustid;
	private String srcAccountid;
	private String srcIp;
	private String orderid;
	private String msgcode;
	private String trantype;
	private String busicode;
	private String accounttype;
	private String shopdate;
	private String payeeUsercode;
	private String payeeCustid;
	private String payeeCustname;
	private String payeeTel;
	private String payeeAccountid;
	private String payeeFeeaccountid;
	private String payerUsercode;
	private String payerCustid;
	private String payerAccountid;
	private String payerFeeaccountid;
	private String currency;
	private Double orderamount;
	private Double remainamount;
	private Double paidamount;
	private Double payeeAmount;
	private Double refundamount;
	private String ordernote;
	private String extradata;
	private String remark;
	private String supportcards;
	private String pgurl;
	private String bgurl;
	private String trandate;
	private Date createtime;
	private Date lasttopaytime;
	private Date expiretime;
	private Double srcFee;
	private String srcFeeflag;
	private Double payeeFee;
	private String payeeFeeflag;
	private String payerFeeflag;
	private String accountdate;
	private String accountTranid;
	private Date accountTrantime;
	private String srcVouchersn;
	private String orderVouchersn;
	private String feeVouchersn;
	private Date stateChangetime;
	private String state;
	private String feeFlag;
	private String businessState;
	private String remark1;
	private String remark2;
	private String remark3;
	private String qsFlag;
	private String busNotifyFlag;
	private String jsFlag;
	private String buyerUserCode;
	private String busAcceptUserCode;
	private Integer busNotifyTimes;
	private String busisn;
	private Double foreignAmount;
	private Double rate;
	private String foreignCur;
	private String messageVersion;
	private Short seq;
	private Double settlementAmount;
	private String merCharset;
	private String merOutsideUserid;
	private String merSubMerchantInfo;
	private String srcDomian;
	private String isSupportCustomcheckout;
	private String isHighriskMerchant;
	private String merTelephone;
	private String linktype;
	private String orgNo;
	private String tradeSource;
	private String consigneeInfo;

	public String getConsigneeInfo() {
		return this.consigneeInfo;
	}

	public void setConsigneeInfo(String consigneeInfo) {
		this.consigneeInfo = consigneeInfo;
	}

	public String getTradeSource() {
		return this.tradeSource;
	}

	public void setTradeSource(String tradeSource) {
		this.tradeSource = tradeSource;
	}

	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getSrcDomian() {
		return this.srcDomian;
	}

	public void setSrcDomian(String srcDomian) {
		this.srcDomian = srcDomian;
	}

	public String getBusinessState() {
		return this.businessState;
	}

	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}

	public String getTradesn() {
		return this.tradesn;
	}

	public void setTradesn(String tradesn) {
		this.tradesn = tradesn;
	}

	public String getSrcUsercode() {
		return this.srcUsercode;
	}

	public void setSrcUsercode(String srcUsercode) {
		this.srcUsercode = srcUsercode;
	}

	public String getSrcCustid() {
		return this.srcCustid;
	}

	public void setSrcCustid(String srcCustid) {
		this.srcCustid = srcCustid;
	}

	public String getSrcAccountid() {
		return this.srcAccountid;
	}

	public void setSrcAccountid(String srcAccountid) {
		this.srcAccountid = srcAccountid;
	}

	public String getSrcIp() {
		return this.srcIp;
	}

	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getMsgcode() {
		return this.msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getTrantype() {
		return this.trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}

	public String getBusicode() {
		return this.busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	public String getAccounttype() {
		return this.accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getShopdate() {
		return this.shopdate;
	}

	public void setShopdate(String shopdate) {
		this.shopdate = shopdate;
	}

	public String getPayeeUsercode() {
		return this.payeeUsercode;
	}

	public void setPayeeUsercode(String payeeUsercode) {
		this.payeeUsercode = payeeUsercode;
	}

	public String getPayeeCustid() {
		return this.payeeCustid;
	}

	public void setPayeeCustid(String payeeCustid) {
		this.payeeCustid = payeeCustid;
	}

	public String getPayeeCustname() {
		return this.payeeCustname;
	}

	public void setPayeeCustname(String payeeCustname) {
		this.payeeCustname = payeeCustname;
	}

	public String getPayeeTel() {
		return this.payeeTel;
	}

	public void setPayeeTel(String payeeTel) {
		this.payeeTel = payeeTel;
	}

	public String getPayeeAccountid() {
		return this.payeeAccountid;
	}

	public void setPayeeAccountid(String payeeAccountid) {
		this.payeeAccountid = payeeAccountid;
	}

	public String getPayeeFeeaccountid() {
		return this.payeeFeeaccountid;
	}

	public void setPayeeFeeaccountid(String payeeFeeaccountid) {
		this.payeeFeeaccountid = payeeFeeaccountid;
	}

	public String getPayerUsercode() {
		return this.payerUsercode;
	}

	public void setPayerUsercode(String payerUsercode) {
		this.payerUsercode = payerUsercode;
	}

	public String getPayerCustid() {
		return this.payerCustid;
	}

	public void setPayerCustid(String payerCustid) {
		this.payerCustid = payerCustid;
	}

	public String getPayerAccountid() {
		return this.payerAccountid;
	}

	public void setPayerAccountid(String payerAccountid) {
		this.payerAccountid = payerAccountid;
	}

	public String getPayerFeeaccountid() {
		return this.payerFeeaccountid;
	}

	public void setPayerFeeaccountid(String payerFeeaccountid) {
		this.payerFeeaccountid = payerFeeaccountid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getOrderamount() {
		return this.orderamount;
	}

	public void setOrderamount(Double orderamount) {
		this.orderamount = orderamount;
	}

	public Double getRemainamount() {
		return this.remainamount;
	}

	public void setRemainamount(Double remainamount) {
		this.remainamount = remainamount;
	}

	public Double getPaidamount() {
		return this.paidamount;
	}

	public void setPaidamount(Double paidamount) {
		this.paidamount = paidamount;
	}

	public Double getPayeeAmount() {
		return this.payeeAmount;
	}

	public void setPayeeAmount(Double payeeAmount) {
		this.payeeAmount = payeeAmount;
	}

	public Double getRefundamount() {
		return this.refundamount;
	}

	public void setRefundamount(Double refundamount) {
		this.refundamount = refundamount;
	}

	public String getOrdernote() {
		return this.ordernote;
	}

	public void setOrdernote(String ordernote) {
		this.ordernote = ordernote;
	}

	public String getExtradata() {
		return this.extradata;
	}

	public void setExtradata(String extradata) {
		this.extradata = extradata;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSupportcards() {
		return this.supportcards;
	}

	public void setSupportcards(String supportcards) {
		this.supportcards = supportcards;
	}

	public String getPgurl() {
		return this.pgurl;
	}

	public void setPgurl(String pgurl) {
		this.pgurl = pgurl;
	}

	public String getBgurl() {
		return this.bgurl;
	}

	public void setBgurl(String bgurl) {
		this.bgurl = bgurl;
	}

	public String getTrandate() {
		return this.trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLasttopaytime() {
		return this.lasttopaytime;
	}

	public void setLasttopaytime(Date lasttopaytime) {
		this.lasttopaytime = lasttopaytime;
	}

	public Date getExpiretime() {
		return this.expiretime;
	}

	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}

	public Double getSrcFee() {
		return this.srcFee;
	}

	public void setSrcFee(Double srcFee) {
		this.srcFee = srcFee;
	}

	public String getSrcFeeflag() {
		return this.srcFeeflag;
	}

	public void setSrcFeeflag(String srcFeeflag) {
		this.srcFeeflag = srcFeeflag;
	}

	public Double getPayeeFee() {
		return this.payeeFee;
	}

	public void setPayeeFee(Double payeeFee) {
		this.payeeFee = payeeFee;
	}

	public String getPayeeFeeflag() {
		return this.payeeFeeflag;
	}

	public void setPayeeFeeflag(String payeeFeeflag) {
		this.payeeFeeflag = payeeFeeflag;
	}

	public String getPayerFeeflag() {
		return this.payerFeeflag;
	}

	public void setPayerFeeflag(String payerFeeflag) {
		this.payerFeeflag = payerFeeflag;
	}

	public String getAccountdate() {
		return this.accountdate;
	}

	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}

	public String getAccountTranid() {
		return this.accountTranid;
	}

	public void setAccountTranid(String accountTranid) {
		this.accountTranid = accountTranid;
	}

	public Date getAccountTrantime() {
		return this.accountTrantime;
	}

	public void setAccountTrantime(Date accountTrantime) {
		this.accountTrantime = accountTrantime;
	}

	public String getSrcVouchersn() {
		return this.srcVouchersn;
	}

	public void setSrcVouchersn(String srcVouchersn) {
		this.srcVouchersn = srcVouchersn;
	}

	public String getOrderVouchersn() {
		return this.orderVouchersn;
	}

	public void setOrderVouchersn(String orderVouchersn) {
		this.orderVouchersn = orderVouchersn;
	}

	public String getFeeVouchersn() {
		return this.feeVouchersn;
	}

	public void setFeeVouchersn(String feeVouchersn) {
		this.feeVouchersn = feeVouchersn;
	}

	public Date getStateChangetime() {
		return this.stateChangetime;
	}

	public void setStateChangetime(Date stateChangetime) {
		this.stateChangetime = stateChangetime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFeeFlag() {
		return this.feeFlag;
	}

	public void setFeeFlag(String feeFlag) {
		this.feeFlag = feeFlag;
	}

	public String getQsFlag() {
		return this.qsFlag;
	}

	public void setQsFlag(String qsFlag) {
		this.qsFlag = qsFlag;
	}

	public String getBusNotifyFlag() {
		return this.busNotifyFlag;
	}

	public void setBusNotifyFlag(String busNotifyFlag) {
		this.busNotifyFlag = busNotifyFlag;
	}

	public String getJsFlag() {
		return this.jsFlag;
	}

	public void setJsFlag(String jsFlag) {
		this.jsFlag = jsFlag;
	}

	public String getBuyerUserCode() {
		return this.buyerUserCode;
	}

	public void setBuyerUserCode(String buyerUserCode) {
		this.buyerUserCode = buyerUserCode;
	}

	public String getBusAcceptUserCode() {
		return this.busAcceptUserCode;
	}

	public void setBusAcceptUserCode(String busAcceptUserCode) {
		this.busAcceptUserCode = busAcceptUserCode;
	}

	public Integer getBusNotifyTimes() {
		return this.busNotifyTimes;
	}

	public void setBusNotifyTimes(Integer busNotifyTimes) {
		this.busNotifyTimes = busNotifyTimes;
	}

	public String getBusisn() {
		return this.busisn;
	}

	public void setBusisn(String busisn) {
		this.busisn = busisn;
	}

	public void setForeignAmount(Double foreignAmount) {
		this.foreignAmount = foreignAmount;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getForeignAmount() {
		return this.foreignAmount;
	}

	public String getForeignCur() {
		return this.foreignCur;
	}

	public void setForeignCur(String foreignCur) {
		this.foreignCur = foreignCur;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getMessageVersion() {
		return this.messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	public Short getSeq() {
		return this.seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public Double getSettlementAmount() {
		return this.settlementAmount;
	}

	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public String getMerCharset() {
		return this.merCharset;
	}

	public void setMerCharset(String merCharset) {
		this.merCharset = merCharset;
	}

	public String getMerOutsideUserid() {
		return this.merOutsideUserid;
	}

	public void setMerOutsideUserid(String merOutsideUserid) {
		this.merOutsideUserid = merOutsideUserid;
	}

	public String getMerSubMerchantInfo() {
		return this.merSubMerchantInfo;
	}

	public void setMerSubMerchantInfo(String merSubMerchantInfo) {
		this.merSubMerchantInfo = merSubMerchantInfo;
	}

	public String getIsSupportCustomcheckout() {
		return this.isSupportCustomcheckout;
	}

	public void setIsSupportCustomcheckout(String isSupportCustomcheckout) {
		this.isSupportCustomcheckout = isSupportCustomcheckout;
	}

	public String getIsHighriskMerchant() {
		return this.isHighriskMerchant;
	}

	public void setIsHighriskMerchant(String isHighriskMerchant) {
		this.isHighriskMerchant = isHighriskMerchant;
	}

	public String getMerTelephone() {
		return this.merTelephone;
	}

	public void setMerTelephone(String merTelephone) {
		this.merTelephone = merTelephone;
	}

	public String getLinktype() {
		return this.linktype;
	}

	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}
}
