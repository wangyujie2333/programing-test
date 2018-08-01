package yspay.trade.order.test.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderBill {
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

    private BigDecimal orderamount;

    private BigDecimal remainamount;

    private BigDecimal paidamount;

    private BigDecimal payeeAmount;

    private BigDecimal refundamount;

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

    private BigDecimal srcFee;

    private String srcFeeflag;

    private BigDecimal payeeFee;

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

    private String businessstate;

    private String qsFlag;

    private String busnotifyflag;

    private String jsFlag;

    private String buyerusercode;

    private String busacceptusercode;

    private Integer busnotifytimes;

    private String busisn;

    private BigDecimal foreignamount;

    private BigDecimal rate;

    private String foreigncur;

    private String remark1;

    private String remark2;

    private String remark3;

    private String messageversion;

    private Short seq;

    private BigDecimal settlementAmount;

    private String merCharset;

    private String merOutsideUserid;

    private String merSubmerchantInfo;

    private String srcDomian;

    private String isSupportCustomcheckout;

    private String isHighriskMerchant;

    private String merTelephone;

    private String linktype;

    private String orgNo;

    private String tradeSource;

    private String consigneeInfo;

    public String getTradesn() {
        return tradesn;
    }

    public void setTradesn(String tradesn) {
        this.tradesn = tradesn == null ? null : tradesn.trim();
    }

    public String getSrcUsercode() {
        return srcUsercode;
    }

    public void setSrcUsercode(String srcUsercode) {
        this.srcUsercode = srcUsercode == null ? null : srcUsercode.trim();
    }

    public String getSrcCustid() {
        return srcCustid;
    }

    public void setSrcCustid(String srcCustid) {
        this.srcCustid = srcCustid == null ? null : srcCustid.trim();
    }

    public String getSrcAccountid() {
        return srcAccountid;
    }

    public void setSrcAccountid(String srcAccountid) {
        this.srcAccountid = srcAccountid == null ? null : srcAccountid.trim();
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp == null ? null : srcIp.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode == null ? null : msgcode.trim();
    }

    public String getTrantype() {
        return trantype;
    }

    public void setTrantype(String trantype) {
        this.trantype = trantype == null ? null : trantype.trim();
    }

    public String getBusicode() {
        return busicode;
    }

    public void setBusicode(String busicode) {
        this.busicode = busicode == null ? null : busicode.trim();
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype == null ? null : accounttype.trim();
    }

    public String getShopdate() {
        return shopdate;
    }

    public void setShopdate(String shopdate) {
        this.shopdate = shopdate == null ? null : shopdate.trim();
    }

    public String getPayeeUsercode() {
        return payeeUsercode;
    }

    public void setPayeeUsercode(String payeeUsercode) {
        this.payeeUsercode = payeeUsercode == null ? null : payeeUsercode.trim();
    }

    public String getPayeeCustid() {
        return payeeCustid;
    }

    public void setPayeeCustid(String payeeCustid) {
        this.payeeCustid = payeeCustid == null ? null : payeeCustid.trim();
    }

    public String getPayeeCustname() {
        return payeeCustname;
    }

    public void setPayeeCustname(String payeeCustname) {
        this.payeeCustname = payeeCustname == null ? null : payeeCustname.trim();
    }

    public String getPayeeTel() {
        return payeeTel;
    }

    public void setPayeeTel(String payeeTel) {
        this.payeeTel = payeeTel == null ? null : payeeTel.trim();
    }

    public String getPayeeAccountid() {
        return payeeAccountid;
    }

    public void setPayeeAccountid(String payeeAccountid) {
        this.payeeAccountid = payeeAccountid == null ? null : payeeAccountid.trim();
    }

    public String getPayeeFeeaccountid() {
        return payeeFeeaccountid;
    }

    public void setPayeeFeeaccountid(String payeeFeeaccountid) {
        this.payeeFeeaccountid = payeeFeeaccountid == null ? null : payeeFeeaccountid.trim();
    }

    public String getPayerUsercode() {
        return payerUsercode;
    }

    public void setPayerUsercode(String payerUsercode) {
        this.payerUsercode = payerUsercode == null ? null : payerUsercode.trim();
    }

    public String getPayerCustid() {
        return payerCustid;
    }

    public void setPayerCustid(String payerCustid) {
        this.payerCustid = payerCustid == null ? null : payerCustid.trim();
    }

    public String getPayerAccountid() {
        return payerAccountid;
    }

    public void setPayerAccountid(String payerAccountid) {
        this.payerAccountid = payerAccountid == null ? null : payerAccountid.trim();
    }

    public String getPayerFeeaccountid() {
        return payerFeeaccountid;
    }

    public void setPayerFeeaccountid(String payerFeeaccountid) {
        this.payerFeeaccountid = payerFeeaccountid == null ? null : payerFeeaccountid.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(BigDecimal orderamount) {
        this.orderamount = orderamount;
    }

    public BigDecimal getRemainamount() {
        return remainamount;
    }

    public void setRemainamount(BigDecimal remainamount) {
        this.remainamount = remainamount;
    }

    public BigDecimal getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(BigDecimal paidamount) {
        this.paidamount = paidamount;
    }

    public BigDecimal getPayeeAmount() {
        return payeeAmount;
    }

    public void setPayeeAmount(BigDecimal payeeAmount) {
        this.payeeAmount = payeeAmount;
    }

    public BigDecimal getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(BigDecimal refundamount) {
        this.refundamount = refundamount;
    }

    public String getOrdernote() {
        return ordernote;
    }

    public void setOrdernote(String ordernote) {
        this.ordernote = ordernote == null ? null : ordernote.trim();
    }

    public String getExtradata() {
        return extradata;
    }

    public void setExtradata(String extradata) {
        this.extradata = extradata == null ? null : extradata.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSupportcards() {
        return supportcards;
    }

    public void setSupportcards(String supportcards) {
        this.supportcards = supportcards == null ? null : supportcards.trim();
    }

    public String getPgurl() {
        return pgurl;
    }

    public void setPgurl(String pgurl) {
        this.pgurl = pgurl == null ? null : pgurl.trim();
    }

    public String getBgurl() {
        return bgurl;
    }

    public void setBgurl(String bgurl) {
        this.bgurl = bgurl == null ? null : bgurl.trim();
    }

    public String getTrandate() {
        return trandate;
    }

    public void setTrandate(String trandate) {
        this.trandate = trandate == null ? null : trandate.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttopaytime() {
        return lasttopaytime;
    }

    public void setLasttopaytime(Date lasttopaytime) {
        this.lasttopaytime = lasttopaytime;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public BigDecimal getSrcFee() {
        return srcFee;
    }

    public void setSrcFee(BigDecimal srcFee) {
        this.srcFee = srcFee;
    }

    public String getSrcFeeflag() {
        return srcFeeflag;
    }

    public void setSrcFeeflag(String srcFeeflag) {
        this.srcFeeflag = srcFeeflag == null ? null : srcFeeflag.trim();
    }

    public BigDecimal getPayeeFee() {
        return payeeFee;
    }

    public void setPayeeFee(BigDecimal payeeFee) {
        this.payeeFee = payeeFee;
    }

    public String getPayeeFeeflag() {
        return payeeFeeflag;
    }

    public void setPayeeFeeflag(String payeeFeeflag) {
        this.payeeFeeflag = payeeFeeflag == null ? null : payeeFeeflag.trim();
    }

    public String getPayerFeeflag() {
        return payerFeeflag;
    }

    public void setPayerFeeflag(String payerFeeflag) {
        this.payerFeeflag = payerFeeflag == null ? null : payerFeeflag.trim();
    }

    public String getAccountdate() {
        return accountdate;
    }

    public void setAccountdate(String accountdate) {
        this.accountdate = accountdate == null ? null : accountdate.trim();
    }

    public String getAccountTranid() {
        return accountTranid;
    }

    public void setAccountTranid(String accountTranid) {
        this.accountTranid = accountTranid == null ? null : accountTranid.trim();
    }

    public Date getAccountTrantime() {
        return accountTrantime;
    }

    public void setAccountTrantime(Date accountTrantime) {
        this.accountTrantime = accountTrantime;
    }

    public String getSrcVouchersn() {
        return srcVouchersn;
    }

    public void setSrcVouchersn(String srcVouchersn) {
        this.srcVouchersn = srcVouchersn == null ? null : srcVouchersn.trim();
    }

    public String getOrderVouchersn() {
        return orderVouchersn;
    }

    public void setOrderVouchersn(String orderVouchersn) {
        this.orderVouchersn = orderVouchersn == null ? null : orderVouchersn.trim();
    }

    public String getFeeVouchersn() {
        return feeVouchersn;
    }

    public void setFeeVouchersn(String feeVouchersn) {
        this.feeVouchersn = feeVouchersn == null ? null : feeVouchersn.trim();
    }

    public Date getStateChangetime() {
        return stateChangetime;
    }

    public void setStateChangetime(Date stateChangetime) {
        this.stateChangetime = stateChangetime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getFeeFlag() {
        return feeFlag;
    }

    public void setFeeFlag(String feeFlag) {
        this.feeFlag = feeFlag == null ? null : feeFlag.trim();
    }

    public String getBusinessstate() {
        return businessstate;
    }

    public void setBusinessstate(String businessstate) {
        this.businessstate = businessstate == null ? null : businessstate.trim();
    }

    public String getQsFlag() {
        return qsFlag;
    }

    public void setQsFlag(String qsFlag) {
        this.qsFlag = qsFlag == null ? null : qsFlag.trim();
    }

    public String getBusnotifyflag() {
        return busnotifyflag;
    }

    public void setBusnotifyflag(String busnotifyflag) {
        this.busnotifyflag = busnotifyflag == null ? null : busnotifyflag.trim();
    }

    public String getJsFlag() {
        return jsFlag;
    }

    public void setJsFlag(String jsFlag) {
        this.jsFlag = jsFlag == null ? null : jsFlag.trim();
    }

    public String getBuyerusercode() {
        return buyerusercode;
    }

    public void setBuyerusercode(String buyerusercode) {
        this.buyerusercode = buyerusercode == null ? null : buyerusercode.trim();
    }

    public String getBusacceptusercode() {
        return busacceptusercode;
    }

    public void setBusacceptusercode(String busacceptusercode) {
        this.busacceptusercode = busacceptusercode == null ? null : busacceptusercode.trim();
    }

    public Integer getBusnotifytimes() {
        return busnotifytimes;
    }

    public void setBusnotifytimes(Integer busnotifytimes) {
        this.busnotifytimes = busnotifytimes;
    }

    public String getBusisn() {
        return busisn;
    }

    public void setBusisn(String busisn) {
        this.busisn = busisn == null ? null : busisn.trim();
    }

    public BigDecimal getForeignamount() {
        return foreignamount;
    }

    public void setForeignamount(BigDecimal foreignamount) {
        this.foreignamount = foreignamount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getForeigncur() {
        return foreigncur;
    }

    public void setForeigncur(String foreigncur) {
        this.foreigncur = foreigncur == null ? null : foreigncur.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getMessageversion() {
        return messageversion;
    }

    public void setMessageversion(String messageversion) {
        this.messageversion = messageversion == null ? null : messageversion.trim();
    }

    public Short getSeq() {
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getMerCharset() {
        return merCharset;
    }

    public void setMerCharset(String merCharset) {
        this.merCharset = merCharset == null ? null : merCharset.trim();
    }

    public String getMerOutsideUserid() {
        return merOutsideUserid;
    }

    public void setMerOutsideUserid(String merOutsideUserid) {
        this.merOutsideUserid = merOutsideUserid == null ? null : merOutsideUserid.trim();
    }

    public String getMerSubmerchantInfo() {
        return merSubmerchantInfo;
    }

    public void setMerSubmerchantInfo(String merSubmerchantInfo) {
        this.merSubmerchantInfo = merSubmerchantInfo == null ? null : merSubmerchantInfo.trim();
    }

    public String getSrcDomian() {
        return srcDomian;
    }

    public void setSrcDomian(String srcDomian) {
        this.srcDomian = srcDomian == null ? null : srcDomian.trim();
    }

    public String getIsSupportCustomcheckout() {
        return isSupportCustomcheckout;
    }

    public void setIsSupportCustomcheckout(String isSupportCustomcheckout) {
        this.isSupportCustomcheckout = isSupportCustomcheckout == null ? null : isSupportCustomcheckout.trim();
    }

    public String getIsHighriskMerchant() {
        return isHighriskMerchant;
    }

    public void setIsHighriskMerchant(String isHighriskMerchant) {
        this.isHighriskMerchant = isHighriskMerchant == null ? null : isHighriskMerchant.trim();
    }

    public String getMerTelephone() {
        return merTelephone;
    }

    public void setMerTelephone(String merTelephone) {
        this.merTelephone = merTelephone == null ? null : merTelephone.trim();
    }

    public String getLinktype() {
        return linktype;
    }

    public void setLinktype(String linktype) {
        this.linktype = linktype == null ? null : linktype.trim();
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getTradeSource() {
        return tradeSource;
    }

    public void setTradeSource(String tradeSource) {
        this.tradeSource = tradeSource == null ? null : tradeSource.trim();
    }

    public String getConsigneeInfo() {
        return consigneeInfo;
    }

    public void setConsigneeInfo(String consigneeInfo) {
        this.consigneeInfo = consigneeInfo == null ? null : consigneeInfo.trim();
    }
}