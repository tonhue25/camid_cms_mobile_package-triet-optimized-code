package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CHARGE_INVOICE database table.
 * 
 */
@Entity
@Table(name="CHARGE_INVOICE")
@NamedQuery(name="ChargeInvoice.findAll", query="SELECT c FROM ChargeInvoice c")
public class ChargeInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	@Column(name="AMOUNT_NOT_TAX")
	private BigDecimal amountNotTax;

	@Column(name="AMOUNT_TAX")
	private BigDecimal amountTax;

	private String barcode;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="BLOCK_NO")
	private String blockNo;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CHARGE_INVOICE_ID")
	private BigDecimal chargeInvoiceId;

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

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="CUST_TYPE")
	private String custType;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="INVOICE_LIST_ID")
	private BigDecimal invoiceListId;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="INVOICE_NUM")
	private String invoiceNum;

	@Column(name="INVOICE_TYPE")
	private String invoiceType;

	@Column(name="INVOICE_TYPE_ID")
	private BigDecimal invoiceTypeId;

	@Column(name="IS_INVOICE")
	private BigDecimal isInvoice;

	@Column(name="IS_PAYMENT")
	private String isPayment;

	private String isdn;

	@Column(name="ITEM_NO")
	private BigDecimal itemNo;

	@Column(name="JOB_IN")
	private String jobIn;

	@Column(name="PAY_AREA_CODE")
	private String payAreaCode;

	@Column(name="PAY_METHOD")
	private String payMethod;

	@Column(name="PRIVILEGE_TYPE")
	private String privilegeType;

	@Column(name="SERIAL_NO")
	private String serialNo;

	@Column(name="SERVICE_TYPES")
	private String serviceTypes;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	@Column(name="TAX_AMOUNT")
	private BigDecimal taxAmount;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TEL_FAX")
	private String telFax;

	private String tin;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	private String unit;

	public ChargeInvoice() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAmountNotTax() {
		return this.amountNotTax;
	}

	public void setAmountNotTax(BigDecimal amountNotTax) {
		this.amountNotTax = amountNotTax;
	}

	public BigDecimal getAmountTax() {
		return this.amountTax;
	}

	public void setAmountTax(BigDecimal amountTax) {
		this.amountTax = amountTax;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getBlockNo() {
		return this.blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getChargeInvoiceId() {
		return this.chargeInvoiceId;
	}

	public void setChargeInvoiceId(BigDecimal chargeInvoiceId) {
		this.chargeInvoiceId = chargeInvoiceId;
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

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustType() {
		return this.custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getInvoiceListId() {
		return this.invoiceListId;
	}

	public void setInvoiceListId(BigDecimal invoiceListId) {
		this.invoiceListId = invoiceListId;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public BigDecimal getInvoiceTypeId() {
		return this.invoiceTypeId;
	}

	public void setInvoiceTypeId(BigDecimal invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}

	public BigDecimal getIsInvoice() {
		return this.isInvoice;
	}

	public void setIsInvoice(BigDecimal isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getIsPayment() {
		return this.isPayment;
	}

	public void setIsPayment(String isPayment) {
		this.isPayment = isPayment;
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

	public String getPayAreaCode() {
		return this.payAreaCode;
	}

	public void setPayAreaCode(String payAreaCode) {
		this.payAreaCode = payAreaCode;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPrivilegeType() {
		return this.privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}