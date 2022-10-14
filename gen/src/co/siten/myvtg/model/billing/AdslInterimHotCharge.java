package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADSL_INTERIM_HOT_CHARGE database table.
 * 
 */
@Entity
@Table(name="ADSL_INTERIM_HOT_CHARGE")
@NamedQuery(name="AdslInterimHotCharge.findAll", query="SELECT a FROM AdslInterimHotCharge a")
public class AdslInterimHotCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_OF_CYCLE")
	private Date beginOfCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="CALL_ID")
	private BigDecimal callId;

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

	private BigDecimal duration;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FRAME_IP")
	private String frameIp;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="MAC_ADDRESS")
	private String macAddress;

	@Column(name="NAS_IP")
	private String nasIp;

	private String paid;

	@Column(name="PAID_ACCOUNT")
	private String paidAccount;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	private String port;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="REC_TYPE")
	private BigDecimal recType;

	private BigDecimal seq;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="TIME_ZONE_ID")
	private BigDecimal timeZoneId;

	private BigDecimal upload;

	public AdslInterimHotCharge() {
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getBeginOfCycle() {
		return this.beginOfCycle;
	}

	public void setBeginOfCycle(Date beginOfCycle) {
		this.beginOfCycle = beginOfCycle;
	}

	public Date getBillDatetime() {
		return this.billDatetime;
	}

	public void setBillDatetime(Date billDatetime) {
		this.billDatetime = billDatetime;
	}

	public BigDecimal getCallId() {
		return this.callId;
	}

	public void setCallId(BigDecimal callId) {
		this.callId = callId;
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

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFrameIp() {
		return this.frameIp;
	}

	public void setFrameIp(String frameIp) {
		this.frameIp = frameIp;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getNasIp() {
		return this.nasIp;
	}

	public void setNasIp(String nasIp) {
		this.nasIp = nasIp;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getPaidAccount() {
		return this.paidAccount;
	}

	public void setPaidAccount(String paidAccount) {
		this.paidAccount = paidAccount;
	}

	public BigDecimal getPaidContractId() {
		return this.paidContractId;
	}

	public void setPaidContractId(BigDecimal paidContractId) {
		this.paidContractId = paidContractId;
	}

	public BigDecimal getPaidSubId() {
		return this.paidSubId;
	}

	public void setPaidSubId(BigDecimal paidSubId) {
		this.paidSubId = paidSubId;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPromProgramCode() {
		return this.promProgramCode;
	}

	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}

	public BigDecimal getRecType() {
		return this.recType;
	}

	public void setRecType(BigDecimal recType) {
		this.recType = recType;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public BigDecimal getTimeZoneId() {
		return this.timeZoneId;
	}

	public void setTimeZoneId(BigDecimal timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public BigDecimal getUpload() {
		return this.upload;
	}

	public void setUpload(BigDecimal upload) {
		this.upload = upload;
	}

}