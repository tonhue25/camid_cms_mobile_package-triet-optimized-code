package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONTRACT_PROMOTION_NOPRINT database table.
 * 
 */
@Entity
@Table(name="CONTRACT_PROMOTION_NOPRINT")
@NamedQuery(name="ContractPromotionNoprint.findAll", query="SELECT c FROM ContractPromotionNoprint c")
public class ContractPromotionNoprint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String description;

	public ContractPromotionNoprint() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}