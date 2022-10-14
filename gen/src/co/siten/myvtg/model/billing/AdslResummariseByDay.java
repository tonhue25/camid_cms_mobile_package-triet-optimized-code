package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADSL_RESUMMARISE_BY_DAY database table.
 * 
 */
@Entity
@Table(name="ADSL_RESUMMARISE_BY_DAY")
@NamedQuery(name="AdslResummariseByDay.findAll", query="SELECT a FROM AdslResummariseByDay a")
public class AdslResummariseByDay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CHARGE_DOWNLOAD")
	private BigDecimal chargeDownload;

	@Column(name="CHARGE_UPLOAD")
	private BigDecimal chargeUpload;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DIS_CHARGE_DOWNLOAD")
	private BigDecimal disChargeDownload;

	@Column(name="DIS_CHARGE_UPLOAD")
	private BigDecimal disChargeUpload;

	private BigDecimal download;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="PAID_NUMBER")
	private String paidNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	private BigDecimal upload;

	public AdslResummariseByDay() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public BigDecimal getChargeDownload() {
		return this.chargeDownload;
	}

	public void setChargeDownload(BigDecimal chargeDownload) {
		this.chargeDownload = chargeDownload;
	}

	public BigDecimal getChargeUpload() {
		return this.chargeUpload;
	}

	public void setChargeUpload(BigDecimal chargeUpload) {
		this.chargeUpload = chargeUpload;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDisChargeDownload() {
		return this.disChargeDownload;
	}

	public void setDisChargeDownload(BigDecimal disChargeDownload) {
		this.disChargeDownload = disChargeDownload;
	}

	public BigDecimal getDisChargeUpload() {
		return this.disChargeUpload;
	}

	public void setDisChargeUpload(BigDecimal disChargeUpload) {
		this.disChargeUpload = disChargeUpload;
	}

	public BigDecimal getDownload() {
		return this.download;
	}

	public void setDownload(BigDecimal download) {
		this.download = download;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public String getPaidNumber() {
		return this.paidNumber;
	}

	public void setPaidNumber(String paidNumber) {
		this.paidNumber = paidNumber;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
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

	public BigDecimal getUpload() {
		return this.upload;
	}

	public void setUpload(BigDecimal upload) {
		this.upload = upload;
	}

}