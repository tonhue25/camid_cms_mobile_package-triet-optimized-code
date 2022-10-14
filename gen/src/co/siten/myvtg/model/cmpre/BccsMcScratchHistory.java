package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BCCS_MC_SCRATCH_HISTORY database table.
 * 
 */
@Entity
@Table(name="BCCS_MC_SCRATCH_HISTORY")
@NamedQuery(name="BccsMcScratchHistory.findAll", query="SELECT b FROM BccsMcScratchHistory b")
public class BccsMcScratchHistory implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="PIN_NUMBER")
	private String pinNumber;

	@Column(name="REFILL_AMOUNT")
	private BigDecimal refillAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="REFILL_DATE")
	private Date refillDate;

	@Column(name="REFILL_ISDN")
	private String refillIsdn;

	@Column(name="SERI_NUMBER")
	private String seriNumber;

	public BccsMcScratchHistory() {
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

	public String getPinNumber() {
		return this.pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
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

	public String getSeriNumber() {
		return this.seriNumber;
	}

	public void setSeriNumber(String seriNumber) {
		this.seriNumber = seriNumber;
	}

}