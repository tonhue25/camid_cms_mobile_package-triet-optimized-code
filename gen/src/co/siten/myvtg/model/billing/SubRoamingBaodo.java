package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_ROAMING_BAODO database table.
 * 
 */
@Entity
@Table(name="SUB_ROAMING_BAODO")
@NamedQuery(name="SubRoamingBaodo.findAll", query="SELECT s FROM SubRoamingBaodo s")
public class SubRoamingBaodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubRoamingBaodo() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}