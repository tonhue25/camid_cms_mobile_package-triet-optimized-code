package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PHUNH3_ADJUSTMENT_DETAIL database table.
 * 
 */
@Entity
@Table(name="PHUNH3_ADJUSTMENT_DETAIL")
@NamedQuery(name="Phunh3AdjustmentDetail.findAll", query="SELECT p FROM Phunh3AdjustmentDetail p")
public class Phunh3AdjustmentDetail implements Serializable {
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

	private String approver;

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

	@Column(name="OLD_SUB_ID")
	private BigDecimal oldSubId;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="USER_NAME")
	private String userName;

	public Phunh3AdjustmentDetail() {
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

	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
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

	public BigDecimal getOldSubId() {
		return this.oldSubId;
	}

	public void setOldSubId(BigDecimal oldSubId) {
		this.oldSubId = oldSubId;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
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