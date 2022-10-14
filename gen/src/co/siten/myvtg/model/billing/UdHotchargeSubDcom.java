package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_HOTCHARGE_SUB_DCOM database table.
 * 
 */
@Entity
@Table(name="UD_HOTCHARGE_SUB_DCOM")
@NamedQuery(name="UdHotchargeSubDcom.findAll", query="SELECT u FROM UdHotchargeSubDcom u")
public class UdHotchargeSubDcom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdHotchargeSubDcom() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}