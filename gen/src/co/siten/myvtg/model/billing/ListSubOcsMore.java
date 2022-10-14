package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LIST_SUB_OCS_MORE database table.
 * 
 */
@Entity
@Table(name="LIST_SUB_OCS_MORE")
@NamedQuery(name="ListSubOcsMore.findAll", query="SELECT l FROM ListSubOcsMore l")
public class ListSubOcsMore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public ListSubOcsMore() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}