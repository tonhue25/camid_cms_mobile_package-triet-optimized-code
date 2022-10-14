package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_PROMOTION_CLOSE database table.
 * 
 */
@Entity
@Table(name="SUB_PROMOTION_CLOSE")
@NamedQuery(name="SubPromotionClose.findAll", query="SELECT s FROM SubPromotionClose s")
public class SubPromotionClose implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	public SubPromotionClose() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

}