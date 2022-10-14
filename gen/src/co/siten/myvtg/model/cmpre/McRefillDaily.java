package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MC_REFILL_DAILY database table.
 * 
 */
@Entity
@Table(name="MC_REFILL_DAILY")
@NamedQuery(name="McRefillDaily.findAll", query="SELECT m FROM McRefillDaily m")
public class McRefillDaily implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_DURATION")
	private BigDecimal actDuration;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Column(name="ADD_DURATION")
	private BigDecimal addDuration;

	@Column(name="ADD_VALUE")
	private BigDecimal addValue;

	@Column(name="AMOUNT_AFTER")
	private BigDecimal amountAfter;

	@Column(name="AMOUNT_BEFORE")
	private BigDecimal amountBefore;

	@Column(name="INACT_DURATION")
	private BigDecimal inactDuration;

	private String isdn;

	@Column(name="NUM_REFILLS")
	private BigDecimal numRefills;

	@Column(name="PIN_NUMBER")
	private String pinNumber;

	@Column(name="PROM_AMOUNT")
	private BigDecimal promAmount;

	@Column(name="PROM_CODE")
	private String promCode;

	@Column(name="REFILL_AMOUNT")
	private BigDecimal refillAmount;

	@Column(name="REFILL_DATE")
	private Object refillDate;

	@Column(name="REFILL_TIMES")
	private BigDecimal refillTimes;

	@Column(name="SERI_NUMBER")
	private String seriNumber;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	private String valid;

	public McRefillDaily() {
	}

	public BigDecimal getActDuration() {
		return this.actDuration;
	}

	public void setActDuration(BigDecimal actDuration) {
		this.actDuration = actDuration;
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public BigDecimal getAddDuration() {
		return this.addDuration;
	}

	public void setAddDuration(BigDecimal addDuration) {
		this.addDuration = addDuration;
	}

	public BigDecimal getAddValue() {
		return this.addValue;
	}

	public void setAddValue(BigDecimal addValue) {
		this.addValue = addValue;
	}

	public BigDecimal getAmountAfter() {
		return this.amountAfter;
	}

	public void setAmountAfter(BigDecimal amountAfter) {
		this.amountAfter = amountAfter;
	}

	public BigDecimal getAmountBefore() {
		return this.amountBefore;
	}

	public void setAmountBefore(BigDecimal amountBefore) {
		this.amountBefore = amountBefore;
	}

	public BigDecimal getInactDuration() {
		return this.inactDuration;
	}

	public void setInactDuration(BigDecimal inactDuration) {
		this.inactDuration = inactDuration;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNumRefills() {
		return this.numRefills;
	}

	public void setNumRefills(BigDecimal numRefills) {
		this.numRefills = numRefills;
	}

	public String getPinNumber() {
		return this.pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public BigDecimal getPromAmount() {
		return this.promAmount;
	}

	public void setPromAmount(BigDecimal promAmount) {
		this.promAmount = promAmount;
	}

	public String getPromCode() {
		return this.promCode;
	}

	public void setPromCode(String promCode) {
		this.promCode = promCode;
	}

	public BigDecimal getRefillAmount() {
		return this.refillAmount;
	}

	public void setRefillAmount(BigDecimal refillAmount) {
		this.refillAmount = refillAmount;
	}

	public Object getRefillDate() {
		return this.refillDate;
	}

	public void setRefillDate(Object refillDate) {
		this.refillDate = refillDate;
	}

	public BigDecimal getRefillTimes() {
		return this.refillTimes;
	}

	public void setRefillTimes(BigDecimal refillTimes) {
		this.refillTimes = refillTimes;
	}

	public String getSeriNumber() {
		return this.seriNumber;
	}

	public void setSeriNumber(String seriNumber) {
		this.seriNumber = seriNumber;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}