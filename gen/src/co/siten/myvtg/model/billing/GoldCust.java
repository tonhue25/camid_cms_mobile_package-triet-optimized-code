package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GOLD_CUST database table.
 * 
 */
@Entity
@Table(name="GOLD_CUST")
@NamedQuery(name="GoldCust.findAll", query="SELECT g FROM GoldCust g")
public class GoldCust implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CUST_TYPE")
	private BigDecimal custType;

	private String description;

	private BigDecimal diem;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public GoldCust() {
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

	public BigDecimal getCustType() {
		return this.custType;
	}

	public void setCustType(BigDecimal custType) {
		this.custType = custType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiem() {
		return this.diem;
	}

	public void setDiem(BigDecimal diem) {
		this.diem = diem;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}