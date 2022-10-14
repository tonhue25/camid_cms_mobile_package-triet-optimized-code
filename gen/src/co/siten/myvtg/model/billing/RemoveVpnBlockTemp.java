package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REMOVE_VPN_BLOCK_TEMP database table.
 * 
 */
@Entity
@Table(name="REMOVE_VPN_BLOCK_TEMP")
@NamedQuery(name="RemoveVpnBlockTemp.findAll", query="SELECT r FROM RemoveVpnBlockTemp r")
public class RemoveVpnBlockTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public RemoveVpnBlockTemp() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}