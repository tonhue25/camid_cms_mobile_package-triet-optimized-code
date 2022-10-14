package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ERR$_MC_SCRATCH_HISTORY database table.
 * 
 */
@Entity
@Table(name="ERR$_MC_SCRATCH_HISTORY")
@NamedQuery(name="Err$McScratchHistory.findAll", query="SELECT e FROM Err$McScratchHistory e")
public class Err$McScratchHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCESS_METHOD")
	private String accessMethod;

	@Column(name="ACTDAY_AFTER")
	private String actdayAfter;

	@Column(name="ACTDAY_BEFORE")
	private String actdayBefore;

	@Column(name="AMOUNT_AFTER")
	private String amountAfter;

	@Column(name="AMOUNT_BEFORE")
	private String amountBefore;

	private String cell;

	@Column(name="ORA_ERR_MESG$")
	private String oraErrMesg$;

	@Column(name="ORA_ERR_NUMBER$")
	private BigDecimal oraErrNumber$;

	@Column(name="ORA_ERR_OPTYP$")
	private String oraErrOptyp$;

	@Column(name="ORA_ERR_TAG$")
	private String oraErrTag$;

	@Column(name="PIN_NUMBER")
	private String pinNumber;

	@Column(name="PROM_AMOUNT")
	private String promAmount;

	@Column(name="REFILL_AMOUNT")
	private String refillAmount;

	@Column(name="REFILL_DATE")
	private String refillDate;

	@Column(name="REFILL_ISDN")
	private String refillIsdn;

	@Column(name="REFILL_TIMES")
	private String refillTimes;

	@Column(name="SERI_NUMBER")
	private String seriNumber;

	@Column(name="TRADE_TYPE")
	private String tradeType;

	public Err$McScratchHistory() {
	}

	public String getAccessMethod() {
		return this.accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		this.accessMethod = accessMethod;
	}

	public String getActdayAfter() {
		return this.actdayAfter;
	}

	public void setActdayAfter(String actdayAfter) {
		this.actdayAfter = actdayAfter;
	}

	public String getActdayBefore() {
		return this.actdayBefore;
	}

	public void setActdayBefore(String actdayBefore) {
		this.actdayBefore = actdayBefore;
	}

	public String getAmountAfter() {
		return this.amountAfter;
	}

	public void setAmountAfter(String amountAfter) {
		this.amountAfter = amountAfter;
	}

	public String getAmountBefore() {
		return this.amountBefore;
	}

	public void setAmountBefore(String amountBefore) {
		this.amountBefore = amountBefore;
	}

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getOraErrMesg$() {
		return this.oraErrMesg$;
	}

	public void setOraErrMesg$(String oraErrMesg$) {
		this.oraErrMesg$ = oraErrMesg$;
	}

	public BigDecimal getOraErrNumber$() {
		return this.oraErrNumber$;
	}

	public void setOraErrNumber$(BigDecimal oraErrNumber$) {
		this.oraErrNumber$ = oraErrNumber$;
	}

	public String getOraErrOptyp$() {
		return this.oraErrOptyp$;
	}

	public void setOraErrOptyp$(String oraErrOptyp$) {
		this.oraErrOptyp$ = oraErrOptyp$;
	}

	public String getOraErrTag$() {
		return this.oraErrTag$;
	}

	public void setOraErrTag$(String oraErrTag$) {
		this.oraErrTag$ = oraErrTag$;
	}

	public String getPinNumber() {
		return this.pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getPromAmount() {
		return this.promAmount;
	}

	public void setPromAmount(String promAmount) {
		this.promAmount = promAmount;
	}

	public String getRefillAmount() {
		return this.refillAmount;
	}

	public void setRefillAmount(String refillAmount) {
		this.refillAmount = refillAmount;
	}

	public String getRefillDate() {
		return this.refillDate;
	}

	public void setRefillDate(String refillDate) {
		this.refillDate = refillDate;
	}

	public String getRefillIsdn() {
		return this.refillIsdn;
	}

	public void setRefillIsdn(String refillIsdn) {
		this.refillIsdn = refillIsdn;
	}

	public String getRefillTimes() {
		return this.refillTimes;
	}

	public void setRefillTimes(String refillTimes) {
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