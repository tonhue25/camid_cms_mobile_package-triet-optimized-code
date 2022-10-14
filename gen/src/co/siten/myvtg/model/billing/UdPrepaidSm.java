package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_PREPAID_SMS database table.
 * 
 */
@Entity
@Table(name="UD_PREPAID_SMS")
@NamedQuery(name="UdPrepaidSm.findAll", query="SELECT u FROM UdPrepaidSm u")
public class UdPrepaidSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BONUS_CONSUME")
	private String bonusConsume;

	@Column(name="CALL_COST")
	private String callCost;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="MONTHLY_CONSUME")
	private String monthlyConsume;

	@Column(name="MONTHLY_REMAIN")
	private String monthlyRemain;

	@Column(name="ONNET_CONSUME")
	private String onnetConsume;

	@Column(name="REMAIN_CREDIT")
	private String remainCredit;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="\"TYPE\"")
	private String type;

	public UdPrepaidSm() {
	}

	public String getBonusConsume() {
		return this.bonusConsume;
	}

	public void setBonusConsume(String bonusConsume) {
		this.bonusConsume = bonusConsume;
	}

	public String getCallCost() {
		return this.callCost;
	}

	public void setCallCost(String callCost) {
		this.callCost = callCost;
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public String getMonthlyConsume() {
		return this.monthlyConsume;
	}

	public void setMonthlyConsume(String monthlyConsume) {
		this.monthlyConsume = monthlyConsume;
	}

	public String getMonthlyRemain() {
		return this.monthlyRemain;
	}

	public void setMonthlyRemain(String monthlyRemain) {
		this.monthlyRemain = monthlyRemain;
	}

	public String getOnnetConsume() {
		return this.onnetConsume;
	}

	public void setOnnetConsume(String onnetConsume) {
		this.onnetConsume = onnetConsume;
	}

	public String getRemainCredit() {
		return this.remainCredit;
	}

	public void setRemainCredit(String remainCredit) {
		this.remainCredit = remainCredit;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}