package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_THCK_BUUCHINH_PSTN database table.
 * 
 */
@Entity
@Table(name="BI_THCK_BUUCHINH_PSTN")
@NamedQuery(name="BiThckBuuchinhPstn.findAll", query="SELECT b FROM BiThckBuuchinhPstn b")
public class BiThckBuuchinhPstn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJ_AMOUNT")
	private BigDecimal adjAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="APPLIED_CYCLE")
	private Date appliedCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSE_DATETIME")
	private Date closeDatetime;

	private String closed;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATETIME")
	private Date createDatetime;

	private String description;

	private String isdn;

	@Column(name="SUB_ID")
	private String subId;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public BiThckBuuchinhPstn() {
	}

	public BigDecimal getAdjAmount() {
		return this.adjAmount;
	}

	public void setAdjAmount(BigDecimal adjAmount) {
		this.adjAmount = adjAmount;
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

	public Date getCloseDatetime() {
		return this.closeDatetime;
	}

	public void setCloseDatetime(Date closeDatetime) {
		this.closeDatetime = closeDatetime;
	}

	public String getClosed() {
		return this.closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}