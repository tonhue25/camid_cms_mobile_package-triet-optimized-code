package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_US_CHARGE_BAODO database table.
 * 
 */
@Entity
@Table(name="SUB_US_CHARGE_BAODO")
@NamedQuery(name="SubUsChargeBaodo.findAll", query="SELECT s FROM SubUsChargeBaodo s")
public class SubUsChargeBaodo implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal cuocphatsinh;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubUsChargeBaodo() {
	}

	public BigDecimal getCuocphatsinh() {
		return this.cuocphatsinh;
	}

	public void setCuocphatsinh(BigDecimal cuocphatsinh) {
		this.cuocphatsinh = cuocphatsinh;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}