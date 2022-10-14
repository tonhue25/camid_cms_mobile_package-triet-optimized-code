package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MC_SCRATCH_HISTORY database table.
 * 
 */
@Entity
@Table(name="MC_SCRATCH_HISTORY")
@NamedQuery(name="McScratchHistory.findAll", query="SELECT m FROM McScratchHistory m")
public class McScratchHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCESS_METHOD")
	private String accessMethod;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTDAY_AFTER")
	private Date actdayAfter;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTDAY_BEFORE")
	private Date actdayBefore;

	@Column(name="AMOUNT_AFTER")
	private BigDecimal amountAfter;

	@Column(name="AMOUNT_BEFORE")
	private BigDecimal amountBefore;

	private String cell;

	@Column(name="PIN_NUMBER")
	private String pinNumber;

	@Column(name="PROM_AMOUNT")
	private BigDecimal promAmount;

	@Column(name="REFILL_AMOUNT")
	private BigDecimal refillAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="REFILL_DATE")
	private Date refillDate;

	@Column(name="REFILL_ISDN")
	private String refillIsdn;

	@Column(name="REFILL_TIMES")
	private BigDecimal refillTimes;

	@Column(name="SERI_NUMBER")
	private String seriNumber;

	@Column(name="TRADE_TYPE")
	private String tradeType;

	public McScratchHistory() {
	}

	public String getAccessMethod() {
		return this.accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		this.accessMethod = accessMethod;
	}

	public Date getActdayAfter() {
		return this.actdayAfter;
	}

	public void setActdayAfter(Date actdayAfter) {
		this.actdayAfter = actdayAfter;
	}

	public Date getActdayBefore() {
		return this.actdayBefore;
	}

	public void setActdayBefore(Date actdayBefore) {
		this.actdayBefore = actdayBefore;
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

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
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

	public BigDecimal getRefillAmount() {
		return this.refillAmount;
	}

	public void setRefillAmount(BigDecimal refillAmount) {
		this.refillAmount = refillAmount;
	}

	public Date getRefillDate() {
		return this.refillDate;
	}

	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	public String getRefillIsdn() {
		return this.refillIsdn;
	}

	public void setRefillIsdn(String refillIsdn) {
		this.refillIsdn = refillIsdn;
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

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

}