package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_SUB_USAGE_CHARGE_GROUP database table.
 * 
 */
@Entity
@Table(name="V_SUB_USAGE_CHARGE_GROUP")
@NamedQuery(name="VSubUsageChargeGroup.findAll", query="SELECT v FROM VSubUsageChargeGroup v")
public class VSubUsageChargeGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="GROUP_DOM_CHARGE_PAID")
	private BigDecimal groupDomChargePaid;

	@Column(name="GROUP_INT_CHARGE_PAID")
	private BigDecimal groupIntChargePaid;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	@Column(name="GROUP_SER_CHARGE_PAID")
	private BigDecimal groupSerChargePaid;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_BLOCKS")
	private BigDecimal numBlocks;

	@Column(name="NUM_CALLS")
	private BigDecimal numCalls;

	@Column(name="NUM_DURATIONS")
	private BigDecimal numDurations;

	@Column(name="PAID_CONTRACT_ID")
	private BigDecimal paidContractId;

	@Column(name="PAID_SUB_ID")
	private BigDecimal paidSubId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

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

	@Column(name="USAGE_GROUP_PAID")
	private BigDecimal usageGroupPaid;

	@Column(name="USAGE_PERSONAL_PAID")
	private BigDecimal usagePersonalPaid;

	public VSubUsageChargeGroup() {
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

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getGroupDomChargePaid() {
		return this.groupDomChargePaid;
	}

	public void setGroupDomChargePaid(BigDecimal groupDomChargePaid) {
		this.groupDomChargePaid = groupDomChargePaid;
	}

	public BigDecimal getGroupIntChargePaid() {
		return this.groupIntChargePaid;
	}

	public void setGroupIntChargePaid(BigDecimal groupIntChargePaid) {
		this.groupIntChargePaid = groupIntChargePaid;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public BigDecimal getGroupSerChargePaid() {
		return this.groupSerChargePaid;
	}

	public void setGroupSerChargePaid(BigDecimal groupSerChargePaid) {
		this.groupSerChargePaid = groupSerChargePaid;
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

	public BigDecimal getPaidContractId() {
		return this.paidContractId;
	}

	public void setPaidContractId(BigDecimal paidContractId) {
		this.paidContractId = paidContractId;
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

	public BigDecimal getUsageGroupPaid() {
		return this.usageGroupPaid;
	}

	public void setUsageGroupPaid(BigDecimal usageGroupPaid) {
		this.usageGroupPaid = usageGroupPaid;
	}

	public BigDecimal getUsagePersonalPaid() {
		return this.usagePersonalPaid;
	}

	public void setUsagePersonalPaid(BigDecimal usagePersonalPaid) {
		this.usagePersonalPaid = usagePersonalPaid;
	}

}