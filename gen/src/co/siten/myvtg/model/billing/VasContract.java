package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the VAS_CONTRACT database table.
 * 
 */
@Entity
@Table(name="VAS_CONTRACT")
@NamedQuery(name="VasContract.findAll", query="SELECT v FROM VasContract v")
public class VasContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILLING_CYCLE_FROM")
	private BigDecimal billingCycleFrom;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String description;

	private String name;

	private String username;

	@Column(name="VAS_CODE")
	private String vasCode;

	public VasContract() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillingCycleFrom() {
		return this.billingCycleFrom;
	}

	public void setBillingCycleFrom(BigDecimal billingCycleFrom) {
		this.billingCycleFrom = billingCycleFrom;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVasCode() {
		return this.vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

}