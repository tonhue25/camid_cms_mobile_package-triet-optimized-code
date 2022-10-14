package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LL_VAS_MONTHLY_FEE database table.
 * 
 */
@Entity
@Table(name="LL_VAS_MONTHLY_FEE")
@NamedQuery(name="LlVasMonthlyFee.findAll", query="SELECT l FROM LlVasMonthlyFee l")
public class LlVasMonthlyFee implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="BILLING_NUMBER")
	private String billingNumber;

	@Column(name="CALL_ID")
	private String callId;

	@Column(name="CHARGING_CALL_ID")
	private String chargingCallId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DIS_AMOUNT")
	private BigDecimal disAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FILE_NAME")
	private String fileName;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	private String paid;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_NUMBER")
	private String paidNumber;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="RELATED_PRODUCT_CODE")
	private String relatedProductCode;

	private BigDecimal seq;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Temporal(TemporalType.DATE)
	@Column(name="UNTIL_DATE")
	private Date untilDate;

	public LlVasMonthlyFee() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
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

	public BigDecimal getDisAmount() {
		return this.disAmount;
	}

	public void setDisAmount(BigDecimal disAmount) {
		this.disAmount = disAmount;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getRelatedProductCode() {
		return this.relatedProductCode;
	}

	public void setRelatedProductCode(String relatedProductCode) {
		this.relatedProductCode = relatedProductCode;
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

	public Date getUntilDate() {
		return this.untilDate;
	}

	public void setUntilDate(Date untilDate) {
		this.untilDate = untilDate;
	}

}