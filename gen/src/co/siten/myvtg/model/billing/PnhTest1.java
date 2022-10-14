package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PNH_TEST1 database table.
 * 
 */
@Entity
@Table(name="PNH_TEST1")
@NamedQuery(name="PnhTest1.findAll", query="SELECT p FROM PnhTest1 p")
public class PnhTest1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public PnhTest1() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}