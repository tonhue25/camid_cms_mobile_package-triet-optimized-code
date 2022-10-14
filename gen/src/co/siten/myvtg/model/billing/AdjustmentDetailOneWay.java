package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADJUSTMENT_DETAIL_ONE_WAY database table.
 * 
 */
@Entity
@Table(name="ADJUSTMENT_DETAIL_ONE_WAY")
@NamedQuery(name="AdjustmentDetailOneWay.findAll", query="SELECT a FROM AdjustmentDetailOneWay a")
public class AdjustmentDetailOneWay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJUSTMENT_ID")
	private BigDecimal adjustmentId;

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

	@Column(name="DAY_NUMBER")
	private BigDecimal dayNumber;

	private String description;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="USER_NAME")
	private String userName;

	public AdjustmentDetailOneWay() {
	}

	public BigDecimal getAdjustmentId() {
		return this.adjustmentId;
	}

	public void setAdjustmentId(BigDecimal adjustmentId) {
		this.adjustmentId = adjustmentId;
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

	public BigDecimal getDayNumber() {
		return this.dayNumber;
	}

	public void setDayNumber(BigDecimal dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}