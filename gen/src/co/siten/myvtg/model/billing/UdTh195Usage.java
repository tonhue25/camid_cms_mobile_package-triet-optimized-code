package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_TH195_USAGE database table.
 * 
 */
@Entity
@Table(name="UD_TH195_USAGE")
@NamedQuery(name="UdTh195Usage.findAll", query="SELECT u FROM UdTh195Usage u")
public class UdTh195Usage implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal charge;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdTh195Usage() {
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