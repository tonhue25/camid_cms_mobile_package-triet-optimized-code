package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TMP_THANG_SUM_MB_POSTPAID_SMS database table.
 * 
 */
@Entity
@Table(name="TMP_THANG_SUM_MB_POSTPAID_SMS")
@NamedQuery(name="TmpThangSumMbPostpaidSm.findAll", query="SELECT t FROM TmpThangSumMbPostpaidSm t")
public class TmpThangSumMbPostpaidSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DIS_DOM_CHARGE")
	private BigDecimal disDomCharge;

	@Column(name="DIS_INT_CHARGE")
	private BigDecimal disIntCharge;

	@Column(name="DIS_SER_CHARGE")
	private BigDecimal disSerCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_BLOCKS")
	private BigDecimal numBlocks;

	@Column(name="NUM_CALLS")
	private BigDecimal numCalls;

	@Column(name="NUM_DURATIONS")
	private BigDecimal numDurations;

	@Column(name="PAID_NUMBER")
	private String paidNumber;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Temporal(TemporalType.DATE)
	@Column(name="TRUNC_STA_DATETIME")
	private Date truncStaDatetime;

	public TmpThangSumMbPostpaidSm() {
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

	public BigDecimal getDisIntCharge() {
		return this.disIntCharge;
	}

	public void setDisIntCharge(BigDecimal disIntCharge) {
		this.disIntCharge = disIntCharge;
	}

	public BigDecimal getDisSerCharge() {
		return this.disSerCharge;
	}

	public void setDisSerCharge(BigDecimal disSerCharge) {
		this.disSerCharge = disSerCharge;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public BigDecimal getIntCharge() {
		return this.intCharge;
	}

	public void setIntCharge(BigDecimal intCharge) {
		this.intCharge = intCharge;
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

	public String getPaidNumber() {
		return this.paidNumber;
	}

	public void setPaidNumber(String paidNumber) {
		this.paidNumber = paidNumber;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
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