package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_RECLOSE database table.
 * 
 */
@Entity
@Table(name="SUB_RECLOSE")
@NamedQuery(name="SubReclose.findAll", query="SELECT s FROM SubReclose s")
public class SubReclose implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CLOSE_ERROR")
	private String closeError;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public SubReclose() {
	}

	public String getCloseError() {
		return this.closeError;
	}

	public void setCloseError(String closeError) {
		this.closeError = closeError;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}