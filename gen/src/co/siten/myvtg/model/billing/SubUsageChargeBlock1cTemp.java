package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_USAGE_CHARGE_BLOCK_1C_TEMP database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_BLOCK_1C_TEMP")
@NamedQuery(name="SubUsageChargeBlock1cTemp.findAll", query="SELECT s FROM SubUsageChargeBlock1cTemp s")
public class SubUsageChargeBlock1cTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal cuocphatsinh;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubUsageChargeBlock1cTemp() {
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