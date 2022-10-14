package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MB_ROAMING database table.
 * 
 */
@Entity
@Table(name="MB_ROAMING")
@NamedQuery(name="MbRoaming.findAll", query="SELECT m FROM MbRoaming m")
public class MbRoaming implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CHARGING_CALL_ID")
	private String chargingCallId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String currency;

	@Column(name="DBF_ID")
	private BigDecimal dbfId;

	@Column(name="DIS_IR_CHARGE")
	private BigDecimal disIrCharge;

	@Column(name="DIS_REROUT_AIR")
	private BigDecimal disReroutAir;

	@Column(name="DIS_REROUTE_IDD")
	private BigDecimal disRerouteIdd;

	private BigDecimal duration;

	@Column(name="EXCHANGE_RATE")
	private BigDecimal exchangeRate;

	@Column(name="FILE_NAME")
	private String fileName;

	private String imsi;

	@Column(name="IR_CHARGE")
	private BigDecimal irCharge;

	@Column(name="IR_CHARGE_VND")
	private BigDecimal irChargeVnd;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="MARKUP_CHARGE")
	private BigDecimal markupCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="ORG_STA_DATETIME")
	private Date orgStaDatetime;

	private BigDecimal paid;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_NUMBER")
	private String paidNumber;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	@Column(name="PO_CODE")
	private String poCode;

	@Temporal(TemporalType.DATE)
	@Column(name="PROC_DATE")
	private Date procDate;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="REROUTE_IDD")
	private BigDecimal rerouteIdd;

	private BigDecimal seq;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="\"TYPE\"")
	private String type;

	@Temporal(TemporalType.DATE)
	@Column(name="VN_STA_DATETIME")
	private Date vnStaDatetime;

	private String vplmn;

	public MbRoaming() {
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

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getDbfId() {
		return this.dbfId;
	}

	public void setDbfId(BigDecimal dbfId) {
		this.dbfId = dbfId;
	}

	public BigDecimal getDisIrCharge() {
		return this.disIrCharge;
	}

	public void setDisIrCharge(BigDecimal disIrCharge) {
		this.disIrCharge = disIrCharge;
	}

	public BigDecimal getDisReroutAir() {
		return this.disReroutAir;
	}

	public void setDisReroutAir(BigDecimal disReroutAir) {
		this.disReroutAir = disReroutAir;
	}

	public BigDecimal getDisRerouteIdd() {
		return this.disRerouteIdd;
	}

	public void setDisRerouteIdd(BigDecimal disRerouteIdd) {
		this.disRerouteIdd = disRerouteIdd;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public BigDecimal getIrCharge() {
		return this.irCharge;
	}

	public void setIrCharge(BigDecimal irCharge) {
		this.irCharge = irCharge;
	}

	public BigDecimal getIrChargeVnd() {
		return this.irChargeVnd;
	}

	public void setIrChargeVnd(BigDecimal irChargeVnd) {
		this.irChargeVnd = irChargeVnd;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getMarkupCharge() {
		return this.markupCharge;
	}

	public void setMarkupCharge(BigDecimal markupCharge) {
		this.markupCharge = markupCharge;
	}

	public Date getOrgStaDatetime() {
		return this.orgStaDatetime;
	}

	public void setOrgStaDatetime(Date orgStaDatetime) {
		this.orgStaDatetime = orgStaDatetime;
	}

	public BigDecimal getPaid() {
		return this.paid;
	}

	public void setPaid(BigDecimal paid) {
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

	public String getPoCode() {
		return this.poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public Date getProcDate() {
		return this.procDate;
	}

	public void setProcDate(Date procDate) {
		this.procDate = procDate;
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

	public BigDecimal getRerouteIdd() {
		return this.rerouteIdd;
	}

	public void setRerouteIdd(BigDecimal rerouteIdd) {
		this.rerouteIdd = rerouteIdd;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getVnStaDatetime() {
		return this.vnStaDatetime;
	}

	public void setVnStaDatetime(Date vnStaDatetime) {
		this.vnStaDatetime = vnStaDatetime;
	}

	public String getVplmn() {
		return this.vplmn;
	}

	public void setVplmn(String vplmn) {
		this.vplmn = vplmn;
	}

}