package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONTRACT_NOT_SEND_SMS_TEMP database table.
 * 
 */
@Entity
@Table(name="CONTRACT_NOT_SEND_SMS_TEMP")
@NamedQuery(name="ContractNotSendSmsTemp.findAll", query="SELECT c FROM ContractNotSendSmsTemp c")
public class ContractNotSendSmsTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public ContractNotSendSmsTemp() {
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}