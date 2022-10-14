package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRINT_CHARGE_REPORT_TEMP1 database table.
 * 
 */
@Entity
@Table(name="PRINT_CHARGE_REPORT_TEMP1")
@NamedQuery(name="PrintChargeReportTemp1.findAll", query="SELECT p FROM PrintChargeReportTemp1 p")
public class PrintChargeReportTemp1 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	private BigDecimal adjustment;

	@Column(name="APPLIED_CYCLE")
	private String appliedCycle;

	private String barcode;

	@Column(name="BARCODE_TO_PRINT")
	private String barcodeToPrint;

	@Column(name="BILL_CYCLE")
	private String billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE_TEMP")
	private Date billCycleTemp;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CHARGE_BY_WORDS")
	private BigDecimal chargeByWords;

	@Column(name="CHARGE_REPORT_ID")
	private BigDecimal chargeReportId;

	private String chukycuoc;

	@Column(name="COLLECTION_GROUP_ID")
	private String collectionGroupId;

	@Column(name="COLLECTION_STAFF_ID")
	private String collectionStaffId;

	@Column(name="CONTRACT_FORM_MNGT")
	private String contractFormMngt;

	@Column(name="CONTRACT_FORM_MNGT_GROUP")
	private String contractFormMngtGroup;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="DISCOUNT_AMOUNT")
	private BigDecimal discountAmount;

	@Column(name="END_DATE")
	private String endDate;

	private String isdn;

	@Column(name="ITEM_NO")
	private BigDecimal itemNo;

	@Column(name="JOB_IN")
	private String jobIn;

	private String name;

	@Column(name="NGAY_IN")
	private String ngayIn;

	@Column(name="NUM_SUBSCRIBERS")
	private BigDecimal numSubscribers;

	@Column(name="PAY_BEFORE")
	private String payBefore;

	@Column(name="PRIOR_DEBIT")
	private BigDecimal priorDebit;

	@Column(name="PRIVILEGE_TYPE")
	private String privilegeType;

	@Column(name="PROM_AMOUNT")
	private BigDecimal promAmount;

	private BigDecimal tax;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	@Column(name="TOT_CHARGE_2")
	private BigDecimal totCharge2;

	public PrintChargeReportTemp1() {
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

	public String getAppliedCycle() {
		return this.appliedCycle;
	}

	public void setAppliedCycle(String appliedCycle) {
		this.appliedCycle = appliedCycle;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeToPrint() {
		return this.barcodeToPrint;
	}

	public void setBarcodeToPrint(String barcodeToPrint) {
		this.barcodeToPrint = barcodeToPrint;
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

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getChargeByWords() {
		return this.chargeByWords;
	}

	public void setChargeByWords(BigDecimal chargeByWords) {
		this.chargeByWords = chargeByWords;
	}

	public BigDecimal getChargeReportId() {
		return this.chargeReportId;
	}

	public void setChargeReportId(BigDecimal chargeReportId) {
		this.chargeReportId = chargeReportId;
	}

	public String getChukycuoc() {
		return this.chukycuoc;
	}

	public void setChukycuoc(String chukycuoc) {
		this.chukycuoc = chukycuoc;
	}

	public String getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(String collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public String getCollectionStaffId() {
		return this.collectionStaffId;
	}

	public void setCollectionStaffId(String collectionStaffId) {
		this.collectionStaffId = collectionStaffId;
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

	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNgayIn() {
		return this.ngayIn;
	}

	public void setNgayIn(String ngayIn) {
		this.ngayIn = ngayIn;
	}

	public BigDecimal getNumSubscribers() {
		return this.numSubscribers;
	}

	public void setNumSubscribers(BigDecimal numSubscribers) {
		this.numSubscribers = numSubscribers;
	}

	public String getPayBefore() {
		return this.payBefore;
	}

	public void setPayBefore(String payBefore) {
		this.payBefore = payBefore;
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

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

	public BigDecimal getTotCharge2() {
		return this.totCharge2;
	}

	public void setTotCharge2(BigDecimal totCharge2) {
		this.totCharge2 = totCharge2;
	}

}