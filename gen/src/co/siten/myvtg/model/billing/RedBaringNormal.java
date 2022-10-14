package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RED_BARING_NORMAL database table.
 * 
 */
@Entity
@Table(name="RED_BARING_NORMAL")
@NamedQuery(name="RedBaringNormal.findAll", query="SELECT r FROM RedBaringNormal r")
public class RedBaringNormal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_COMMENT")
	private String actionComment;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="ALERT_RATE")
	private BigDecimal alertRate;

	@Column(name="BARING_ID")
	private BigDecimal baringId;

	@Column(name="BARRING_RATE")
	private BigDecimal barringRate;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private BigDecimal debit;

	@Column(name="ERROR_LOG")
	private String errorLog;

	private String isdn;

	@Column(name="LIMIT_AMOUNT")
	private BigDecimal limitAmount;

	@Column(name="MONEY_ARISE_PRESENT")
	private BigDecimal moneyArisePresent;

	@Column(name="SMS_STATUS")
	private BigDecimal smsStatus;

	@Column(name="STATUS_BARRING")
	private String statusBarring;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USER_NAME")
	private String userName;

	private BigDecimal vip;

	public RedBaringNormal() {
	}

	public String getActionComment() {
		return this.actionComment;
	}

	public void setActionComment(String actionComment) {
		this.actionComment = actionComment;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public BigDecimal getAlertRate() {
		return this.alertRate;
	}

	public void setAlertRate(BigDecimal alertRate) {
		this.alertRate = alertRate;
	}

	public BigDecimal getBaringId() {
		return this.baringId;
	}

	public void setBaringId(BigDecimal baringId) {
		this.baringId = baringId;
	}

	public BigDecimal getBarringRate() {
		return this.barringRate;
	}

	public void setBarringRate(BigDecimal barringRate) {
		this.barringRate = barringRate;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getDebit() {
		return this.debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public String getErrorLog() {
		return this.errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getLimitAmount() {
		return this.limitAmount;
	}

	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}

	public BigDecimal getMoneyArisePresent() {
		return this.moneyArisePresent;
	}

	public void setMoneyArisePresent(BigDecimal moneyArisePresent) {
		this.moneyArisePresent = moneyArisePresent;
	}

	public BigDecimal getSmsStatus() {
		return this.smsStatus;
	}

	public void setSmsStatus(BigDecimal smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getStatusBarring() {
		return this.statusBarring;
	}

	public void setStatusBarring(String statusBarring) {
		this.statusBarring = statusBarring;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getVip() {
		return this.vip;
	}

	public void setVip(BigDecimal vip) {
		this.vip = vip;
	}

}