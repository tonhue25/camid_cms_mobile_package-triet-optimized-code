package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_CHARGE_REPORT_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_CHARGE_REPORT_TEMP")
@NamedQuery(name="BiChargeReportTemp.findAll", query="SELECT b FROM BiChargeReportTemp b")
public class BiChargeReportTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	private BigDecimal adjustment;

	private String barcode;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

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

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="DISCOUNT_AMOUNT")
	private BigDecimal discountAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="ITEM_NO")
	private BigDecimal itemNo;

	@Column(name="JOB_IN")
	private String jobIn;

	private String name;

	@Column(name="NUM_SUBSCRIBERS")
	private BigDecimal numSubscribers;

	@Column(name="PAY_AREA_CODE")
	private String payAreaCode;

	@Column(name="PAY_METHOD")
	private String payMethod;

	@Column(name="PRINT_PRIOR")
	private String printPrior;

	@Column(name="PRIOR_DEBIT")
	private BigDecimal priorDebit;

	@Column(name="PRIVILEGE_TYPE")
	private BigDecimal privilegeType;

	@Column(name="PROM_AMOUNT")
	private BigDecimal promAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	private BigDecimal tax;

	@Column(name="TEL_FAX")
	private String telFax;

	private String tin;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USAGE_CHARGE_NOT_TAX")
	private BigDecimal usageChargeNotTax;

	@Column(name="USAGE_CHARGE_TAX")
	private BigDecimal usageChargeTax;

	public BiChargeReportTemp() {
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public BigDecimal getPrivilegeType() {
		return this.privilegeType;
	}

	public void setPrivilegeType(BigDecimal privilegeType) {
		this.privilegeType = privilegeType;
	}

	public BigDecimal getPromAmount() {
		return this.promAmount;
	}

	public void setPromAmount(BigDecimal promAmount) {
		this.promAmount = promAmount;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
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