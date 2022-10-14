package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LOC_DS_QT database table.
 * 
 */
@Entity
@Table(name="LOC_DS_QT")
@NamedQuery(name="LocDsQt.findAll", query="SELECT l FROM LocDsQt l")
public class LocDsQt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String ignor;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public LocDsQt() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getIgnor() {
		return this.ignor;
	}

	public void setIgnor(String ignor) {
		this.ignor = ignor;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}