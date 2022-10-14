package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_TEMP_CONTRACT database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_CONTRACT")
@NamedQuery(name="MyTempContract.findAll", query="SELECT m FROM MyTempContract m")
public class MyTempContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public MyTempContract() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}