package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the B1_SUB_DOANH_NGHIEP database table.
 * 
 */
@Entity
@Table(name="B1_SUB_DOANH_NGHIEP")
@NamedQuery(name="B1SubDoanhNghiep.findAll", query="SELECT b FROM B1SubDoanhNghiep b")
public class B1SubDoanhNghiep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public B1SubDoanhNghiep() {
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

}