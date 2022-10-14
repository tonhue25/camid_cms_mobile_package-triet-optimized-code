package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONTRACT_TEMP database table.
 * 
 */
@Entity
@Table(name="CONTRACT_TEMP")
@NamedQuery(name="ContractTemp.findAll", query="SELECT c FROM ContractTemp c")
public class ContractTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	public ContractTemp() {
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

}