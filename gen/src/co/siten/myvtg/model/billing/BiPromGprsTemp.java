package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_PROM_GPRS_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_PROM_GPRS_TEMP")
@NamedQuery(name="BiPromGprsTemp.findAll", query="SELECT b FROM BiPromGprsTemp b")
public class BiPromGprsTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_TYPE_CODE")
	private String contractTypeCode;

	private String isdn;

	@Column(name="PROM_TOTAL_CHARGE")
	private BigDecimal promTotalCharge;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TOTAL_CHARGE")
	private BigDecimal totalCharge;

	public BiPromGprsTemp() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractTypeCode() {
		return this.contractTypeCode;
	}

	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getPromTotalCharge() {
		return this.promTotalCharge;
	}

	public void setPromTotalCharge(BigDecimal promTotalCharge) {
		this.promTotalCharge = promTotalCharge;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTotalCharge() {
		return this.totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

}