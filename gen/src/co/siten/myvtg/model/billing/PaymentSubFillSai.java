package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PAYMENT_SUB_FILL_SAI database table.
 * 
 */
@Entity
@Table(name="PAYMENT_SUB_FILL_SAI")
@NamedQuery(name="PaymentSubFillSai.findAll", query="SELECT p FROM PaymentSubFillSai p")
public class PaymentSubFillSai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Temporal(TemporalType.DATE)
	@Column(name="APPLIED_CYCLE")
	private Date appliedCycle;

	@Column(name="ASSIGN_GROUP_ID")
	private BigDecimal assignGroupId;

	@Column(name="ASSIGN_STAFF_ID")
	private BigDecimal assignStaffId;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Temporal(TemporalType.DATE)
	@Column(name="BANK_DATE")
	private Date bankDate;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="BANK_NO")
	private String bankNo;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	@Column(name="COLLECTION_STAFF_ID")
	private BigDecimal collectionStaffId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CURR_TYPE")
	private String currType;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Temporal(TemporalType.DATE)
	@Column(name="DESTROY_DATE")
	private Date destroyDate;

	@Column(name="DESTROY_REASON_ID")
	private BigDecimal destroyReasonId;

	@Column(name="DESTROY_USER")
	private String destroyUser;

	@Column(name="ISDN_CHARGE")
	private String isdnCharge;

	@Column(name="MIG_STATUS")
	private String migStatus;

	@Column(name="OLD_PAYMENT_ID")
	private BigDecimal oldPaymentId;

	@Column(name="ORG_AMOUNT")
	private BigDecimal orgAmount;

	@Column(name="PAYMENT_AMOUNT")
	private BigDecimal paymentAmount;

	@Column(name="PAYMENT_ID")
	private BigDecimal paymentId;

	@Column(name="PAYMENT_INVOICE_ID")
	private BigDecimal paymentInvoiceId;

	@Column(name="PAYMENT_LEVEL")
	private String paymentLevel;

	@Column(name="PAYMENT_TYPE")
	private String paymentType;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIPT_DATE")
	private Date receiptDate;

	@Column(name="STAFF_INSERT_ID")
	private BigDecimal staffInsertId;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="USER_NAME")
	private String userName;

	public PaymentSubFillSai() {
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getAppliedCycle() {
		return this.appliedCycle;
	}

	public void setAppliedCycle(Date appliedCycle) {
		this.appliedCycle = appliedCycle;
	}

	public BigDecimal getAssignGroupId() {
		return this.assignGroupId;
	}

	public void setAssignGroupId(BigDecimal assignGroupId) {
		this.assignGroupId = assignGroupId;
	}

	public BigDecimal getAssignStaffId() {
		return this.assignStaffId;
	}

	public void setAssignStaffId(BigDecimal assignStaffId) {
		this.assignStaffId = assignStaffId;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getBankDate() {
		return this.bankDate;
	}

	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public BigDecimal getCollectionStaffId() {
		return this.collectionStaffId;
	}

	public void setCollectionStaffId(BigDecimal collectionStaffId) {
		this.collectionStaffId = collectionStaffId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCurrType() {
		return this.currType;
	}

	public void setCurrType(String currType) {
		this.currType = currType;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public Date getDestroyDate() {
		return this.destroyDate;
	}

	public void setDestroyDate(Date destroyDate) {
		this.destroyDate = destroyDate;
	}

	public BigDecimal getDestroyReasonId() {
		return this.destroyReasonId;
	}

	public void setDestroyReasonId(BigDecimal destroyReasonId) {
		this.destroyReasonId = destroyReasonId;
	}

	public String getDestroyUser() {
		return this.destroyUser;
	}

	public void setDestroyUser(String destroyUser) {
		this.destroyUser = destroyUser;
	}

	public String getIsdnCharge() {
		return this.isdnCharge;
	}

	public void setIsdnCharge(String isdnCharge) {
		this.isdnCharge = isdnCharge;
	}

	public String getMigStatus() {
		return this.migStatus;
	}

	public void setMigStatus(String migStatus) {
		this.migStatus = migStatus;
	}

	public BigDecimal getOldPaymentId() {
		return this.oldPaymentId;
	}

	public void setOldPaymentId(BigDecimal oldPaymentId) {
		this.oldPaymentId = oldPaymentId;
	}

	public BigDecimal getOrgAmount() {
		return this.orgAmount;
	}

	public void setOrgAmount(BigDecimal orgAmount) {
		this.orgAmount = orgAmount;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(BigDecimal paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getPaymentInvoiceId() {
		return this.paymentInvoiceId;
	}

	public void setPaymentInvoiceId(BigDecimal paymentInvoiceId) {
		this.paymentInvoiceId = paymentInvoiceId;
	}

	public String getPaymentLevel() {
		return this.paymentLevel;
	}

	public void setPaymentLevel(String paymentLevel) {
		this.paymentLevel = paymentLevel;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getReceiptDate() {
		return this.receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public BigDecimal getStaffInsertId() {
		return this.staffInsertId;
	}

	public void setStaffInsertId(BigDecimal staffInsertId) {
		this.staffInsertId = staffInsertId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}