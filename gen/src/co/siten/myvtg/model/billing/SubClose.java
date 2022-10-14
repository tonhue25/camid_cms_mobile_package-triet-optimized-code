package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_CLOSE database table.
 * 
 */
@Entity
@Table(name="SUB_CLOSE")
@NamedQuery(name="SubClose.findAll", query="SELECT s FROM SubClose s")
public class SubClose implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal closed;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="KEY_SEARCH")
	private BigDecimal keySearch;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	public SubClose() {
	}

	public BigDecimal getClosed() {
		return this.closed;
	}

	public void setClosed(BigDecimal closed) {
		this.closed = closed;
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

	public BigDecimal getKeySearch() {
		return this.keySearch;
	}

	public void setKeySearch(BigDecimal keySearch) {
		this.keySearch = keySearch;
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