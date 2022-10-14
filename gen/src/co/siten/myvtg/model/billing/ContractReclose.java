package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONTRACT_RECLOSE database table.
 * 
 */
@Entity
@Table(name="CONTRACT_RECLOSE")
@NamedQuery(name="ContractReclose.findAll", query="SELECT c FROM ContractReclose c")
public class ContractReclose implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public ContractReclose() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}