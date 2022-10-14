package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONTRACT_CLOSE database table.
 * 
 */
@Entity
@Table(name="CONTRACT_CLOSE")
@NamedQuery(name="ContractClose.findAll", query="SELECT c FROM ContractClose c")
public class ContractClose implements Serializable {
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

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	public ContractClose() {
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

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

}