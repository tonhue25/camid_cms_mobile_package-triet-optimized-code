package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FILL_TIEN_THUA_LAI database table.
 * 
 */
@Entity
@Table(name="FILL_TIEN_THUA_LAI")
@NamedQuery(name="FillTienThuaLai.findAll", query="SELECT f FROM FillTienThuaLai f")
public class FillTienThuaLai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public FillTienThuaLai() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}