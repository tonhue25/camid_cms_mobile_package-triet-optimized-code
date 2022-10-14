package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_USAGE_CHARGE_LOCK database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_LOCK")
@NamedQuery(name="SubUsageChargeLock.findAll", query="SELECT s FROM SubUsageChargeLock s")
public class SubUsageChargeLock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_OF_CYCLE")
	private Date beginOfCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="CALL_ID")
	private String callId;

	@Column(name="COM_CHARGE")
	private BigDecimal comCharge;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="DIS_DOM_CHARGE")
	private BigDecimal disDomCharge;

	@Column(name="DIS_INT_CHARGE")
	private BigDecimal disIntCharge;

	@Column(name="DIS_SER_CHARGE")
	private BigDecimal disSerCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private String error;

	@Column(name="FEE_CHARGE")
	private BigDecimal feeCharge;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_BLOCK")
	private BigDecimal numBlock;

	@Column(name="NUM_CALL")
	private BigDecimal numCall;

	@Column(name="NUM_DURATION")
	private BigDecimal numDuration;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TABLE_NAME")
	private String tableName;

	private BigDecimal tax;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public SubUsageChargeLock() {
	}

	public Date getBeginOfCycle() {
		return this.beginOfCycle;
	}

	public void setBeginOfCycle(Date beginOfCycle) {
		this.beginOfCycle = beginOfCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
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

	public BigDecimal getNumBlock() {
		return this.numBlock;
	}

	public void setNumBlock(BigDecimal numBlock) {
		this.numBlock = numBlock;
	}

	public BigDecimal getNumCall() {
		return this.numCall;
	}

	public void setNumCall(BigDecimal numCall) {
		this.numCall = numCall;
	}

	public BigDecimal getNumDuration() {
		return this.numDuration;
	}

	public void setNumDuration(BigDecimal numDuration) {
		this.numDuration = numDuration;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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