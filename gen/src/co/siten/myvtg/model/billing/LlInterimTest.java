package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LL_INTERIM_TEST database table.
 * 
 */
@Entity
@Table(name="LL_INTERIM_TEST")
@NamedQuery(name="LlInterimTest.findAll", query="SELECT l FROM LlInterimTest l")
public class LlInterimTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_OF_CYCLE")
	private Date beginOfCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="BILLING_NUMBER")
	private String billingNumber;

	@Column(name="CALL_ID")
	private String callId;

	@Column(name="CHARGE_DOWNLOAD")
	private BigDecimal chargeDownload;

	@Column(name="CHARGE_UPLOAD")
	private BigDecimal chargeUpload;

	@Column(name="CHARGING_CALL_ID")
	private String chargingCallId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal download;

	private BigDecimal duration;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FRAME_IP")
	private String frameIp;

	@Column(name="MAC_ADDRESS")
	private String macAddress;

	@Column(name="NAS_IP")
	private String nasIp;

	private String paid;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_NUMBER")
	private String paidNumber;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	private String port;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="REC_TYPE")
	private String recType;

	@Column(name="RESOURCE_CHARGE1")
	private BigDecimal resourceCharge1;

	@Column(name="RESOURCE_CHARGE2")
	private BigDecimal resourceCharge2;

	@Column(name="RESOURCE_TYPE1")
	private String resourceType1;

	@Column(name="RESOURCE_TYPE2")
	private String resourceType2;

	private BigDecimal seq;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	private BigDecimal upload;

	public LlInterimTest() {
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

	public String getBillingNumber() {
		return this.billingNumber;
	}

	public void setBillingNumber(String billingNumber) {
		this.billingNumber = billingNumber;
	}

	public String getCallId() {
		return this.callId;
	}

	public void setCallId(String callId) {
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

	public String getChargingCallId() {
		return this.chargingCallId;
	}

	public void setChargingCallId(String chargingCallId) {
		this.chargingCallId = chargingCallId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
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

	public BigDecimal getPaidContractId() {
		return this.paidContractId;
	}

	public void setPaidContractId(BigDecimal paidContractId) {
		this.paidContractId = paidContractId;
	}

	public String getPaidNumber() {
		return this.paidNumber;
	}

	public void setPaidNumber(String paidNumber) {
		this.paidNumber = paidNumber;
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

	public String getRecType() {
		return this.recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public BigDecimal getResourceCharge1() {
		return this.resourceCharge1;
	}

	public void setResourceCharge1(BigDecimal resourceCharge1) {
		this.resourceCharge1 = resourceCharge1;
	}

	public BigDecimal getResourceCharge2() {
		return this.resourceCharge2;
	}

	public void setResourceCharge2(BigDecimal resourceCharge2) {
		this.resourceCharge2 = resourceCharge2;
	}

	public String getResourceType1() {
		return this.resourceType1;
	}

	public void setResourceType1(String resourceType1) {
		this.resourceType1 = resourceType1;
	}

	public String getResourceType2() {
		return this.resourceType2;
	}

	public void setResourceType2(String resourceType2) {
		this.resourceType2 = resourceType2;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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

	public BigDecimal getUpload() {
		return this.upload;
	}

	public void setUpload(BigDecimal upload) {
		this.upload = upload;
	}

}