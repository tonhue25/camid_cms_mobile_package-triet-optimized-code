package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the KS_FTK_REG_FEE database table.
 * 
 */
@Entity
@Table(name="KS_FTK_REG_FEE")
@NamedQuery(name="KsFtkRegFee.findAll", query="SELECT k FROM KsFtkRegFee k")
public class KsFtkRegFee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public KsFtkRegFee() {
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

}