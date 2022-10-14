package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TMP_SUB_USAGE_NUM_CALL database table.
 * 
 */
@Entity
@Table(name="TMP_SUB_USAGE_NUM_CALL")
@NamedQuery(name="TmpSubUsageNumCall.findAll", query="SELECT t FROM TmpSubUsageNumCall t")
public class TmpSubUsageNumCall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="NUM_CALL")
	private BigDecimal numCall;

	public TmpSubUsageNumCall() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getNumCall() {
		return this.numCall;
	}

	public void setNumCall(BigDecimal numCall) {
		this.numCall = numCall;
	}

}