package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CT_REMAIN_AUDIT_FILL_SAI database table.
 * 
 */
@Entity
@Table(name="CT_REMAIN_AUDIT_FILL_SAI")
@NamedQuery(name="CtRemainAuditFillSai.findAll", query="SELECT c FROM CtRemainAuditFillSai c")
public class CtRemainAuditFillSai implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="AMOUNT_APPLY")
	private BigDecimal amountApply;

	@Temporal(TemporalType.DATE)
	@Column(name="APPLIED_CYCLE")
	private Date appliedCycle;

	@Column(name="AUDIT_ID")
	private BigDecimal auditId;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="CANCEL_DATE")
	private Date cancelDate;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="ORG_AMOUNT_APPLY")
	private BigDecimal orgAmountApply;

	@Column(name="ORG_AMOUNT_CANCEL")
	private BigDecimal orgAmountCancel;

	@Column(name="PAYMENT_ID")
	private BigDecimal paymentId;

	@Column(name="SOURCE_ISDN")
	private String sourceIsdn;

	@Column(name="SOURCE_SUB_ID")
	private BigDecimal sourceSubId;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public CtRemainAuditFillSai() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountApply() {
		return this.amountApply;
	}

	public void setAmountApply(BigDecimal amountApply) {
		this.amountApply = amountApply;
	}

	public Date getAppliedCycle() {
		return this.appliedCycle;
	}

	public void setAppliedCycle(Date appliedCycle) {
		this.appliedCycle = appliedCycle;
	}

	public BigDecimal getAuditId() {
		return this.auditId;
	}

	public void setAuditId(BigDecimal auditId) {
		this.auditId = auditId;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
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

	public BigDecimal getOrgAmountApply() {
		return this.orgAmountApply;
	}

	public void setOrgAmountApply(BigDecimal orgAmountApply) {
		this.orgAmountApply = orgAmountApply;
	}

	public BigDecimal getOrgAmountCancel() {
		return this.orgAmountCancel;
	}

	public void setOrgAmountCancel(BigDecimal orgAmountCancel) {
		this.orgAmountCancel = orgAmountCancel;
	}

	public BigDecimal getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(BigDecimal paymentId) {
		this.paymentId = paymentId;
	}

	public String getSourceIsdn() {
		return this.sourceIsdn;
	}

	public void setSourceIsdn(String sourceIsdn) {
		this.sourceIsdn = sourceIsdn;
	}

	public BigDecimal getSourceSubId() {
		return this.sourceSubId;
	}

	public void setSourceSubId(BigDecimal sourceSubId) {
		this.sourceSubId = sourceSubId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}