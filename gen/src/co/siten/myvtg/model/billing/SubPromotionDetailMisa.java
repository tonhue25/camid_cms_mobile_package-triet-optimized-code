package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_PROMOTION_DETAIL_MISA database table.
 * 
 */
@Entity
@Table(name="SUB_PROMOTION_DETAIL_MISA")
@NamedQuery(name="SubPromotionDetailMisa.findAll", query="SELECT s FROM SubPromotionDetailMisa s")
public class SubPromotionDetailMisa implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	private String closed;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String isdn;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="PROM_TYPE")
	private String promType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="\"TYPE\"")
	private String type;

	public SubPromotionDetailMisa() {
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

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getPromProgramCode() {
		return this.promProgramCode;
	}

	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}

	public String getPromType() {
		return this.promType;
	}

	public void setPromType(String promType) {
		this.promType = promType;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}