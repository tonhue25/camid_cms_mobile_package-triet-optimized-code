package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRIVILEGE_BIRTHDAY database table.
 * 
 */
@Entity
@Table(name="PRIVILEGE_BIRTHDAY")
@NamedQuery(name="PrivilegeBirthday.findAll", query="SELECT p FROM PrivilegeBirthday p")
public class PrivilegeBirthday implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	private BigDecimal point;

	@Column(name="SU_ID")
	private BigDecimal suId;

	public PrivilegeBirthday() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getPoint() {
		return this.point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public BigDecimal getSuId() {
		return this.suId;
	}

	public void setSuId(BigDecimal suId) {
		this.suId = suId;
	}

}