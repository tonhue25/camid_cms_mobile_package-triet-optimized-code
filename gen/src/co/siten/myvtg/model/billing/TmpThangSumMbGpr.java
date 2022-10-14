package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TMP_THANG_SUM_MB_GPRS database table.
 * 
 */
@Entity
@Table(name="TMP_THANG_SUM_MB_GPRS")
@NamedQuery(name="TmpThangSumMbGpr.findAll", query="SELECT t FROM TmpThangSumMbGpr t")
public class TmpThangSumMbGpr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DIS_DOM_CHARGE")
	private BigDecimal disDomCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_BLOCKS")
	private BigDecimal numBlocks;

	@Column(name="NUM_CALLS")
	private BigDecimal numCalls;

	@Column(name="NUM_DURATIONS")
	private BigDecimal numDurations;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Temporal(TemporalType.DATE)
	@Column(name="TRUNC_STA_DATETIME")
	private Date truncStaDatetime;

	public TmpThangSumMbGpr() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDisDomCharge() {
		return this.disDomCharge;
	}

	public void setDisDomCharge(BigDecimal disDomCharge) {
		this.disDomCharge = disDomCharge;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getNumBlocks() {
		return this.numBlocks;
	}

	public void setNumBlocks(BigDecimal numBlocks) {
		this.numBlocks = numBlocks;
	}

	public BigDecimal getNumCalls() {
		return this.numCalls;
	}

	public void setNumCalls(BigDecimal numCalls) {
		this.numCalls = numCalls;
	}

	public BigDecimal getNumDurations() {
		return this.numDurations;
	}

	public void setNumDurations(BigDecimal numDurations) {
		this.numDurations = numDurations;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public Date getTruncStaDatetime() {
		return this.truncStaDatetime;
	}

	public void setTruncStaDatetime(Date truncStaDatetime) {
		this.truncStaDatetime = truncStaDatetime;
	}

}