package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_USAGE_CHARGE_SYS_IVR database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_SYS_IVR")
@NamedQuery(name="SubUsageChargeSysIvr.findAll", query="SELECT s FROM SubUsageChargeSysIvr s")
public class SubUsageChargeSysIvr implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal charge;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubUsageChargeSysIvr() {
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