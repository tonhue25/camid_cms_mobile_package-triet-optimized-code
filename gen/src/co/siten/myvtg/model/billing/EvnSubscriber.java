package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EVN_SUBSCRIBER database table.
 * 
 */
@Entity
@Table(name="EVN_SUBSCRIBER")
@NamedQuery(name="EvnSubscriber.findAll", query="SELECT e FROM EvnSubscriber e")
public class EvnSubscriber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public EvnSubscriber() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}