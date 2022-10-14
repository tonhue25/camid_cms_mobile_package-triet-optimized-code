package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LIMIT_USAGE_BADO database table.
 * 
 */
@Entity
@Table(name="LIMIT_USAGE_BADO")
@NamedQuery(name="LimitUsageBado.findAll", query="SELECT l FROM LimitUsageBado l")
public class LimitUsageBado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Column(name="ALERT_RATE")
	private BigDecimal alertRate;

	@Column(name="BARRING_RATE")
	private BigDecimal barringRate;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal deposit;

	@Column(name="HAN_MUC")
	private BigDecimal hanMuc;

	private String isdn;

	@Column(name="NO_TRUOC")
	private BigDecimal noTruoc;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="TIEN_THUA")
	private BigDecimal tienThua;

	private String vip;

	public LimitUsageBado() {
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
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

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getHanMuc() {
		return this.hanMuc;
	}

	public void setHanMuc(BigDecimal hanMuc) {
		this.hanMuc = hanMuc;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNoTruoc() {
		return this.noTruoc;
	}

	public void setNoTruoc(BigDecimal noTruoc) {
		this.noTruoc = noTruoc;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public BigDecimal getTienThua() {
		return this.tienThua;
	}

	public void setTienThua(BigDecimal tienThua) {
		this.tienThua = tienThua;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

}