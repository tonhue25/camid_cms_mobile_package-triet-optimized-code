package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_TEMP_TABLE database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_TABLE")
@NamedQuery(name="MyTempTable.findAll", query="SELECT m FROM MyTempTable m")
public class MyTempTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal tt;

	public MyTempTable() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getTt() {
		return this.tt;
	}

	public void setTt(BigDecimal tt) {
		this.tt = tt;
	}

}