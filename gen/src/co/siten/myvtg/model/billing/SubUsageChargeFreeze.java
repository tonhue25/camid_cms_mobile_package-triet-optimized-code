package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_USAGE_CHARGE_FREEZE database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_FREEZE")
@NamedQuery(name="SubUsageChargeFreeze.findAll", query="SELECT s FROM SubUsageChargeFreeze s")
public class SubUsageChargeFreeze implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="COM_CHARGE")
	private BigDecimal comCharge;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DISCOUNT_DOM_CHARGE")
	private BigDecimal discountDomCharge;

	@Column(name="DISCOUNT_INT_CHARGE")
	private BigDecimal discountIntCharge;

	@Column(name="DISCOUNT_SER_CHARGE")
	private BigDecimal discountSerCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FEE_CHARGE")
	private BigDecimal feeCharge;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_BLOCKS")
	private BigDecimal numBlocks;

	@Column(name="NUM_CALLS")
	private BigDecimal numCalls;

	@Column(name="NUM_DURATIONS")
	private BigDecimal numDurations;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tax;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public SubUsageChargeFreeze() {
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

	public BigDecimal getComCharge() {
		return this.comCharge;
	}

	public void setComCharge(BigDecimal comCharge) {
		this.comCharge = comCharge;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDiscountDomCharge() {
		return this.discountDomCharge;
	}

	public void setDiscountDomCharge(BigDecimal discountDomCharge) {
		this.discountDomCharge = discountDomCharge;
	}

	public BigDecimal getDiscountIntCharge() {
		return this.discountIntCharge;
	}

	public void setDiscountIntCharge(BigDecimal discountIntCharge) {
		this.discountIntCharge = discountIntCharge;
	}

	public BigDecimal getDiscountSerCharge() {
		return this.discountSerCharge;
	}

	public void setDiscountSerCharge(BigDecimal discountSerCharge) {
		this.discountSerCharge = discountSerCharge;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getFeeCharge() {
		return this.feeCharge;
	}

	public void setFeeCharge(BigDecimal feeCharge) {
		this.feeCharge = feeCharge;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public BigDecimal getIntCharge() {
		return this.intCharge;
	}

	public void setIntCharge(BigDecimal intCharge) {
		this.intCharge = intCharge;
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

	public BigDecimal getNumBlocks() {
		return this.numBlocks;
	}

	public void setNumBlocks(BigDecimal numBlocks) {
		this.numBlocks = numBlocks;
	}

	public BigDecimal getNumCalls() {
		return this.numCalls;
	}

	public void setNumCalls(BigDecimal numCalls) {
		this.numCalls = numCalls;
	}

	public BigDecimal getNumDurations() {
		return this.numDurations;
	}

	public void setNumDurations(BigDecimal numDurations) {
		this.numDurations = numDurations;
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

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}