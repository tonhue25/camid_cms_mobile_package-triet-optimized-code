package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the B1_ADJUSTMENT database table.
 * 
 */
@Entity
@Table(name="B1_ADJUSTMENT")
@NamedQuery(name="B1Adjustment.findAll", query="SELECT b FROM B1Adjustment b")
public class B1Adjustment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJ_ID")
	private BigDecimal adjId;

	@Column(name="ADJUSTMENT_ID")
	private BigDecimal adjustmentId;

	@Column(name="ADJUSTMENT_TYPE")
	private String adjustmentType;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="APPLIED_CYCLE")
	private Date appliedCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String description;

	@Column(name="PAYMENT_ID")
	private BigDecimal paymentId;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	public B1Adjustment() {
	}

	public BigDecimal getAdjId() {
		return this.adjId;
	}

	public void setAdjId(BigDecimal adjId) {
		this.adjId = adjId;
	}

	public BigDecimal getAdjustmentId() {
		return this.adjustmentId;
	}

	public void setAdjustmentId(BigDecimal adjustmentId) {
		this.adjustmentId = adjustmentId;
	}

	public String getAdjustmentType() {
		return this.adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getAppliedCycle() {
		return this.appliedCycle;
	}

	public void setAppliedCycle(Date appliedCycle) {
		this.appliedCycle = appliedCycle;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(BigDecimal paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

}