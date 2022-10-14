package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the V_CHARGE_ROAMING database table.
 * 
 */
@Entity
@Table(name="V_CHARGE_ROAMING")
@NamedQuery(name="VChargeRoaming.findAll", query="SELECT v FROM VChargeRoaming v")
public class VChargeRoaming implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal charge;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public VChargeRoaming() {
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}