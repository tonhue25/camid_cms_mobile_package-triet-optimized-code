package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RED_ALERT_LIST database table.
 * 
 */
@Entity
@Table(name="RED_ALERT_LIST")
@NamedQuery(name="RedAlertList.findAll", query="SELECT r FROM RedAlertList r")
public class RedAlertList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="ALERT_RATE")
	private BigDecimal alertRate;

	@Column(name="BARRING_RATE")
	private BigDecimal barringRate;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	private BigDecimal charge;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal debit;

	@Column(name="HAN_MUC")
	private BigDecimal hanMuc;

	@Column(name="IS_SEND")
	private BigDecimal isSend;

	private String isdn;

	@Column(name="RM_REAL")
	private BigDecimal rmReal;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TIEN_TRA_THUA")
	private BigDecimal tienTraThua;

	@Column(name="\"TYPE\"")
	private String type;

	private BigDecimal vip;

	public RedAlertList() {
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public BigDecimal getAlertRate() {
		return this.alertRate;
	}

	public void setAlertRate(BigDecimal alertRate) {
		this.alertRate = alertRate;
	}

	public BigDecimal getBarringRate() {
		return this.barringRate;
	}

	public void setBarringRate(BigDecimal barringRate) {
		this.barringRate = barringRate;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDebit() {
		return this.debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getHanMuc() {
		return this.hanMuc;
	}

	public void setHanMuc(BigDecimal hanMuc) {
		this.hanMuc = hanMuc;
	}

	public BigDecimal getIsSend() {
		return this.isSend;
	}

	public void setIsSend(BigDecimal isSend) {
		this.isSend = isSend;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getRmReal() {
		return this.rmReal;
	}

	public void setRmReal(BigDecimal rmReal) {
		this.rmReal = rmReal;
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

	public BigDecimal getTienTraThua() {
		return this.tienTraThua;
	}

	public void setTienTraThua(BigDecimal tienTraThua) {
		this.tienTraThua = tienTraThua;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getVip() {
		return this.vip;
	}

	public void setVip(BigDecimal vip) {
		this.vip = vip;
	}

}