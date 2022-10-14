package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the KS_FTK_DCH database table.
 * 
 */
@Entity
@Table(name="KS_FTK_DCH")
@NamedQuery(name="KsFtkDch.findAll", query="SELECT k FROM KsFtkDch k")
public class KsFtkDch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tien;

	public KsFtkDch() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTien() {
		return this.tien;
	}

	public void setTien(BigDecimal tien) {
		this.tien = tien;
	}

}