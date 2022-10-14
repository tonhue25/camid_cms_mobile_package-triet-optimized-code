package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONTRACT_REBUILD database table.
 * 
 */
@Entity
@Table(name="CONTRACT_REBUILD")
@NamedQuery(name="ContractRebuild.findAll", query="SELECT c FROM ContractRebuild c")
public class ContractRebuild implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String status;

	public ContractRebuild() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}