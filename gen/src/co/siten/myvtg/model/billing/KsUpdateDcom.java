package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the KS_UPDATE_DCOM database table.
 * 
 */
@Entity
@Table(name="KS_UPDATE_DCOM")
@NamedQuery(name="KsUpdateDcom.findAll", query="SELECT k FROM KsUpdateDcom k")
public class KsUpdateDcom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public KsUpdateDcom() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}