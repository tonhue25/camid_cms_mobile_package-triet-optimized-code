package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SMS_CHARGE_REPORT database table.
 * 
 */
@Entity
@Table(name="SMS_CHARGE_REPORT")
@NamedQuery(name="SmsChargeReport.findAll", query="SELECT s FROM SmsChargeReport s")
public class SmsChargeReport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	private BigDecimal adjustment;

	private String barcode;

	@Column(name="BILL_CYCLE")
	private String billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE_TEMP")
	private Date billCycleTemp;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CENTER_CODE")
	private String centerCode;

	@Column(name="CHARGE_REPORT_ID")
	private BigDecimal chargeReportId;

	@Column(name="CHARGE_STATUS")
	private String chargeStatus;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	@Column(name="COLLECTION_GROUP_NAME")
	private String collectionGroupName;

	@Column(name="COLLECTION_STAFF_ID")
	private BigDecimal collectionStaffId;

	@Column(name="COLLECTION_STAFF_NAME")
	private String collectionStaffName;

	@Column(name="CONTRACT_FORM_MNGT")
	private String contractFormMngt;

	@Column(name="CONTRACT_FORM_MNGT_GROUP")
	private String contractFormMngtGroup;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="DISCOUNT_AMOUNT")
	private BigDecimal discountAmount;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="HAND_OR_MACHINE")
	private BigDecimal handOrMachine;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	private String isdn;

	@Column(name="ITEM_NO")
	private BigDecimal itemNo;

	@Column(name="JOB_IN")
	private String jobIn;

	@Column(name="MANY_DETAIL")
	private BigDecimal manyDetail;

	private String name;

	@Column(name="NOTICE_CHARGE")
	private String noticeCharge;

	@Column(name="NUM_CALL")
	private BigDecimal numCall;

	@Column(name="NUM_SUBSCRIBERS")
	private BigDecimal numSubscribers;

	@Column(name="PAY_AREA_CODE")
	private String payAreaCode;

	@Column(name="PAY_BEFORE")
	private String payBefore;

	@Column(name="PAY_METHOD")
	private String payMethod;

	@Column(name="PRINT_PRIOR")
	private String printPrior;

	@Column(name="PRIOR_DEBIT")
	private BigDecimal priorDebit;

	@Column(name="PRIVILEGE_TYPE")
	private String privilegeType;

	@Column(name="PROM_AMOUNT")
	private BigDecimal promAmount;

	@Column(name="SERVICE_TYPES")
	private String serviceTypes;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	private BigDecimal tax;

	@Column(name="TEL_FAX")
	private String telFax;

	@Column(name="TEL_MOBILE")
	private String telMobile;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	private String tin;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USAGE_CHARGE")
	private BigDecimal usageCharge;

	@Column(name="USAGE_CHARGE_NOT_TAX")
	private BigDecimal usageChargeNotTax;

	@Column(name="USAGE_CHARGE_TAX")
	private BigDecimal usageChargeTax;

	public SmsChargeReport() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAdjustment() {
		return this.adjustment;
	}

	public void setAdjustment(BigDecimal adjustment) {
		this.adjustment = adjustment;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public Date getBillCycleTemp() {
		return this.billCycleTemp;
	}

	public void setBillCycleTemp(Date billCycleTemp) {
		this.billCycleTemp = billCycleTemp;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCenterCode() {
		return this.centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public BigDecimal getChargeReportId() {
		return this.chargeReportId;
	}

	public void setChargeReportId(BigDecimal chargeReportId) {
		this.chargeReportId = chargeReportId;
	}

	public String getChargeStatus() {
		return this.chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public String getCollectionGroupName() {
		return this.collectionGroupName;
	}

	public void setCollectionGroupName(String collectionGroupName) {
		this.collectionGroupName = collectionGroupName;
	}

	public BigDecimal getCollectionStaffId() {
		return this.collectionStaffId;
	}

	public void setCollectionStaffId(BigDecimal collectionStaffId) {
		this.collectionStaffId = collectionStaffId;
	}

	public String getCollectionStaffName() {
		return this.collectionStaffName;
	}

	public void setCollectionStaffName(String collectionStaffName) {
		this.collectionStaffName = collectionStaffName;
	}

	public String getContractFormMngt() {
		return this.contractFormMngt;
	}

	public void setContractFormMngt(String contractFormMngt) {
		this.contractFormMngt = contractFormMngt;
	}

	public String getContractFormMngtGroup() {
		return this.contractFormMngtGroup;
	}

	public void setContractFormMngtGroup(String contractFormMngtGroup) {
		this.contractFormMngtGroup = contractFormMngtGroup;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getHandOrMachine() {
		return this.handOrMachine;
	}

	public void setHandOrMachine(BigDecimal handOrMachine) {
		this.handOrMachine = handOrMachine;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(BigDecimal itemNo) {
		this.itemNo = itemNo;
	}

	public String getJobIn() {
		return this.jobIn;
	}

	public void setJobIn(String jobIn) {
		this.jobIn = jobIn;
	}

	public BigDecimal getManyDetail() {
		return this.manyDetail;
	}

	public void setManyDetail(BigDecimal manyDetail) {
		this.manyDetail = manyDetail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoticeCharge() {
		return this.noticeCharge;
	}

	public void setNoticeCharge(String noticeCharge) {
		this.noticeCharge = noticeCharge;
	}

	public BigDecimal getNumCall() {
		return this.numCall;
	}

	public void setNumCall(BigDecimal numCall) {
		this.numCall = numCall;
	}

	public BigDecimal getNumSubscribers() {
		return this.numSubscribers;
	}

	public void setNumSubscribers(BigDecimal numSubscribers) {
		this.numSubscribers = numSubscribers;
	}

	public String getPayAreaCode() {
		return this.payAreaCode;
	}

	public void setPayAreaCode(String payAreaCode) {
		this.payAreaCode = payAreaCode;
	}

	public String getPayBefore() {
		return this.payBefore;
	}

	public void setPayBefore(String payBefore) {
		this.payBefore = payBefore;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPrintPrior() {
		return this.printPrior;
	}

	public void setPrintPrior(String printPrior) {
		this.printPrior = printPrior;
	}

	public BigDecimal getPriorDebit() {
		return this.priorDebit;
	}

	public void setPriorDebit(BigDecimal priorDebit) {
		this.priorDebit = priorDebit;
	}

	public String getPrivilegeType() {
		return this.privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public BigDecimal getPromAmount() {
		return this.promAmount;
	}

	public void setPromAmount(BigDecimal promAmount) {
		this.promAmount = promAmount;
	}

	public String getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(String serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

	public String getTelMobile() {
		return this.telMobile;
	}

	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getUsageCharge() {
		return this.usageCharge;
	}

	public void setUsageCharge(BigDecimal usageCharge) {
		this.usageCharge = usageCharge;
	}

	public BigDecimal getUsageChargeNotTax() {
		return this.usageChargeNotTax;
	}

	public void setUsageChargeNotTax(BigDecimal usageChargeNotTax) {
		this.usageChargeNotTax = usageChargeNotTax;
	}

	public BigDecimal getUsageChargeTax() {
		return this.usageChargeTax;
	}

	public void setUsageChargeTax(BigDecimal usageChargeTax) {
		this.usageChargeTax = usageChargeTax;
	}

}