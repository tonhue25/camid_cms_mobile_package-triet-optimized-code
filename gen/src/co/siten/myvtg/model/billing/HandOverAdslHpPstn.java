package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HAND_OVER_ADSL_HP_PSTN database table.
 * 
 */
@Entity
@Table(name="HAND_OVER_ADSL_HP_PSTN")
@NamedQuery(name="HandOverAdslHpPstn.findAll", query="SELECT h FROM HandOverAdslHpPstn h")
public class HandOverAdslHpPstn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUS_AMOUNT")
	private BigDecimal cusAmount;

	@Column(name="CUS_NUMBER")
	private BigDecimal cusNumber;

	@Column(name="CUS_TYPE")
	private String cusType;

	@Column(name="TEL_SERVICE_NAME")
	private String telServiceName;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public HandOverAdslHpPstn() {
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