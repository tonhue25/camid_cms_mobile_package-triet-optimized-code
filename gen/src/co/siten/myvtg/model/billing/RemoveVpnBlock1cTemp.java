package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REMOVE_VPN_BLOCK_1C_TEMP database table.
 * 
 */
@Entity
@Table(name="REMOVE_VPN_BLOCK_1C_TEMP")
@NamedQuery(name="RemoveVpnBlock1cTemp.findAll", query="SELECT r FROM RemoveVpnBlock1cTemp r")
public class RemoveVpnBlock1cTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public RemoveVpnBlock1cTemp() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}