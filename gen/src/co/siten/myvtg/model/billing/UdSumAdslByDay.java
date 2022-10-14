package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_SUM_ADSL_BY_DAY database table.
 * 
 */
@Entity
@Table(name="UD_SUM_ADSL_BY_DAY")
@NamedQuery(name="UdSumAdslByDay.findAll", query="SELECT u FROM UdSumAdslByDay u")
public class UdSumAdslByDay implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Temporal(TemporalType.DATE)
	@Column(name="TRUNC_STA_DATETIME")
	private Date truncStaDatetime;

	private BigDecimal upload;

	public UdSumAdslByDay() {
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

	public BigDecimal getUpload() {
		return this.upload;
	}

	public void setUpload(BigDecimal upload) {
		this.upload = upload;
	}

}