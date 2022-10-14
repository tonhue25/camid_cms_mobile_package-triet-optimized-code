package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_ALL_MONTH_FEE database table.
 * 
 */
@Entity
@Table(name="V_ALL_MONTH_FEE")
@NamedQuery(name="VAllMonthFee.findAll", query="SELECT v FROM VAllMonthFee v")
public class VAllMonthFee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_OF_CYCLE")
	private Date beginOfCycle;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DIS_DOM_CHARGE")
	private BigDecimal disDomCharge;

	@Column(name="DIS_INT_CHARGE")
	private BigDecimal disIntCharge;

	@Column(name="DIS_SER_CHARGE")
	private BigDecimal disSerCharge;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private BigDecimal duration;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	private String paid;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public VAllMonthFee() {
	}

	public Date getBeginOfCycle() {
		return this.beginOfCycle;
	}

	public void setBeginOfCycle(Date beginOfCycle) {
		this.beginOfCycle = beginOfCycle;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDisDomCharge() {
		return this.disDomCharge;
	}

	public void setDisDomCharge(BigDecimal disDomCharge) {
		this.disDomCharge = disDomCharge;
	}

	public BigDecimal getDisIntCharge() {
		return this.disIntCharge;
	}

	public void setDisIntCharge(BigDecimal disIntCharge) {
		this.disIntCharge = disIntCharge;
	}

	public BigDecimal getDisSerCharge() {
		return this.disSerCharge;
	}

	public void setDisSerCharge(BigDecimal disSerCharge) {
		this.disSerCharge = disSerCharge;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getIntCharge() {
		return this.intCharge;
	}

	public void setIntCharge(BigDecimal intCharge) {
		this.intCharge = intCharge;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
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

}