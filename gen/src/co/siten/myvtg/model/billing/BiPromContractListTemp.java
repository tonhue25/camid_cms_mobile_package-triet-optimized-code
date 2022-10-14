package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_PROM_CONTRACT_LIST_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_PROM_CONTRACT_LIST_TEMP")
@NamedQuery(name="BiPromContractListTemp.findAll", query="SELECT b FROM BiPromContractListTemp b")
public class BiPromContractListTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="NUM_OF_SUBSCRIBERS")
	private BigDecimal numOfSubscribers;

	public BiPromContractListTemp() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getNumOfSubscribers() {
		return this.numOfSubscribers;
	}

	public void setNumOfSubscribers(BigDecimal numOfSubscribers) {
		this.numOfSubscribers = numOfSubscribers;
	}

}