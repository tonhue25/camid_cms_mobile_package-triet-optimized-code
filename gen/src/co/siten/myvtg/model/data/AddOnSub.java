package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADD_ON_SUB database table.
 * 
 */
@Entity
@Table(name="ADD_ON_SUB")
@NamedQuery(name="AddOnSub.findAll", query="SELECT a FROM AddOnSub a")
public class AddOnSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	@Column(name="EXTEND_CYCLE")
	private BigDecimal extendCycle;

	private BigDecimal id;

	private String msisdn;

	private String name;

	@Column(name="NUMBER_BUY_CYCLE")
	private BigDecimal numberBuyCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="PAID_TIME")
	private Date paidTime;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private BigDecimal subType;

	public AddOnSub() {
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getExtendCycle() {
		return this.extendCycle;
	}

	public void setExtendCycle(BigDecimal extendCycle) {
		this.extendCycle = extendCycle;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNumberBuyCycle() {
		return this.numberBuyCycle;
	}

	public void setNumberBuyCycle(BigDecimal numberBuyCycle) {
		this.numberBuyCycle = numberBuyCycle;
	}

	public Date getPaidTime() {
		return this.paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public BigDecimal getSubType() {
		return this.subType;
	}

	public void setSubType(BigDecimal subType) {
		this.subType = subType;
	}

}