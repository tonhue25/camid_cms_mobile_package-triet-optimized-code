package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADJUSTMENT_DETAIL_VTCI_PSTN database table.
 * 
 */
@Entity
@Table(name="ADJUSTMENT_DETAIL_VTCI_PSTN")
@NamedQuery(name="AdjustmentDetailVtciPstn.findAll", query="SELECT a FROM AdjustmentDetailVtciPstn a")
public class AdjustmentDetailVtciPstn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJUSTMENT_DETAIL_ID")
	private BigDecimal adjustmentDetailId;

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

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String description;

	private String detail;

	@Column(name="USER_NAME")
	private String userName;

	public AdjustmentDetailVtciPstn() {
	}

	public BigDecimal getAdjustmentDetailId() {
		return this.adjustmentDetailId;
	}

	public void setAdjustmentDetailId(BigDecimal adjustmentDetailId) {
		this.adjustmentDetailId = adjustmentDetailId;
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}