package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HAND_OVER_MOBILE database table.
 * 
 */
@Entity
@Table(name="HAND_OVER_MOBILE")
@NamedQuery(name="HandOverMobile.findAll", query="SELECT h FROM HandOverMobile h")
public class HandOverMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_TYPE")
	private String contractType;

	@Column(name="CUS_AMOUNT")
	private BigDecimal cusAmount;

	@Column(name="CUS_NUMBER")
	private BigDecimal cusNumber;

	@Column(name="CUS_TYPE")
	private String cusType;

	@Column(name="CUS_TYPE_COM")
	private String cusTypeCom;

	private String norm;

	@Column(name="PRIOR_DEBIT")
	private BigDecimal priorDebit;

	@Column(name="PRIVILEGE_LEVEL")
	private String privilegeLevel;

	@Column(name="TEL_SERVICE_NAME")
	private String telServiceName;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public HandOverMobile() {
	}

	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public BigDecimal getCusAmount() {
		return this.cusAmount;
	}

	public void setCusAmount(BigDecimal cusAmount) {
		this.cusAmount = cusAmount;
	}

	public BigDecimal getCusNumber() {
		return this.cusNumber;
	}

	public void setCusNumber(BigDecimal cusNumber) {
		this.cusNumber = cusNumber;
	}

	public String getCusType() {
		return this.cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusTypeCom() {
		return this.cusTypeCom;
	}

	public void setCusTypeCom(String cusTypeCom) {
		this.cusTypeCom = cusTypeCom;
	}

	public String getNorm() {
		return this.norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public BigDecimal getPriorDebit() {
		return this.priorDebit;
	}

	public void setPriorDebit(BigDecimal priorDebit) {
		this.priorDebit = priorDebit;
	}

	public String getPrivilegeLevel() {
		return this.privilegeLevel;
	}

	public void setPrivilegeLevel(String privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	public String getTelServiceName() {
		return this.telServiceName;
	}

	public void setTelServiceName(String telServiceName) {
		this.telServiceName = telServiceName;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}