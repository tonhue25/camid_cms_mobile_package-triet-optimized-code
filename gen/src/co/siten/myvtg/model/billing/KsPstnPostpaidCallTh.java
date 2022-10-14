package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the KS_PSTN_POSTPAID_CALL_TH database table.
 * 
 */
@Entity
@Table(name="KS_PSTN_POSTPAID_CALL_TH")
@NamedQuery(name="KsPstnPostpaidCallTh.findAll", query="SELECT k FROM KsPstnPostpaidCallTh k")
public class KsPstnPostpaidCallTh implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DIS_DOM_CHARGE")
	private BigDecimal disDomCharge;

	@Column(name="DIS_INT_CHARGE")
	private BigDecimal disIntCharge;

	@Column(name="DIS_SER_CHARGE")
	private BigDecimal disSerCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private BigDecimal duration;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_BLOCKS")
	private BigDecimal numBlocks;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_NUMBER")
	private BigDecimal paidNumber;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Temporal(TemporalType.DATE)
	@Column(name="TRUNC_STA_DATETIME")
	private Date truncStaDatetime;

	public KsPstnPostpaidCallTh() {
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

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
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

	public BigDecimal getPaidContractId() {
		return this.paidContractId;
	}

	public void setPaidContractId(BigDecimal paidContractId) {
		this.paidContractId = paidContractId;
	}

	public BigDecimal getPaidNumber() {
		return this.paidNumber;
	}

	public void setPaidNumber(BigDecimal paidNumber) {
		this.paidNumber = paidNumber;
	}

	public BigDecimal getPaidSubId() {
		return this.paidSubId;
	}

	public void setPaidSubId(BigDecimal paidSubId) {
		this.paidSubId = paidSubId;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
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