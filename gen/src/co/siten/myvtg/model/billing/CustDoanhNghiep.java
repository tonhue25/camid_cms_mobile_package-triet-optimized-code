package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUST_DOANH_NGHIEP database table.
 * 
 */
@Entity
@Table(name="CUST_DOANH_NGHIEP")
@NamedQuery(name="CustDoanhNghiep.findAll", query="SELECT c FROM CustDoanhNghiep c")
public class CustDoanhNghiep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String description;

	private String status;

	public CustDoanhNghiep() {
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}