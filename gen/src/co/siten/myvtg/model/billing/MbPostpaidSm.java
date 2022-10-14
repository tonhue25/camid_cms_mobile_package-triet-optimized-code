package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MB_POSTPAID_SMS database table.
 * 
 */
@Entity
@Table(name="MB_POSTPAID_SMS")
@NamedQuery(name="MbPostpaidSm.findAll", query="SELECT m FROM MbPostpaidSm m")
public class MbPostpaidSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_OF_CYCLE")
	private Date beginOfCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="CALL_ID")
	private String callId;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CELL_ID")
	private String cellId;

	@Column(name="CHARGING_CALL_ID")
	private String chargingCallId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="COUNTRY_NAME")
	private String countryName;

	@Column(name="DELIVER_COUNT")
	private BigDecimal deliverCount;

	@Column(name="DIS_DOM_CHARGE")
	private BigDecimal disDomCharge;

	@Column(name="DIS_INT_CHARGE")
	private BigDecimal disIntCharge;

	@Column(name="DIS_SER_CHARGE")
	private BigDecimal disSerCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private BigDecimal duration;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="OPEAK_TYPE")
	private String opeakType;

	private String paid;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_NUMBER")
	private String paidNumber;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	@Column(name="PO_CODE")
	private String poCode;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="RESOURCE_CHARGE1")
	private BigDecimal resourceCharge1;

	@Column(name="RESOURCE_CHARGE2")
	private BigDecimal resourceCharge2;

	@Column(name="RESOURCE_TYPE1")
	private String resourceType1;

	@Column(name="RESOURCE_TYPE2")
	private String resourceType2;

	private BigDecimal seq;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="TK45_AFTER")
	private String tk45After;

	@Column(name="TK45_BEFORE")
	private String tk45Before;

	@Column(name="TK45_CHARGE")
	private String tk45Charge;

	@Column(name="\"TYPE\"")
	private String type;

	public MbPostpaidSm() {
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

	public String getCellId() {
		return this.cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
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

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public BigDecimal getDeliverCount() {
		return this.deliverCount;
	}

	public void setDeliverCount(BigDecimal deliverCount) {
		this.deliverCount = deliverCount;
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getOpeakType() {
		return this.opeakType;
	}

	public void setOpeakType(String opeakType) {
		this.opeakType = opeakType;
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

	public String getPoCode() {
		return this.poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
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

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
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

	public String getTk45After() {
		return this.tk45After;
	}

	public void setTk45After(String tk45After) {
		this.tk45After = tk45After;
	}

	public String getTk45Before() {
		return this.tk45Before;
	}

	public void setTk45Before(String tk45Before) {
		this.tk45Before = tk45Before;
	}

	public String getTk45Charge() {
		return this.tk45Charge;
	}

	public void setTk45Charge(String tk45Charge) {
		this.tk45Charge = tk45Charge;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}