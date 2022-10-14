package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GROUP_VAS_ITEMS database table.
 * 
 */
@Entity
@Table(name="GROUP_VAS_ITEMS")
@NamedQuery(name="GroupVasItem.findAll", query="SELECT g FROM GroupVasItem g")
public class GroupVasItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="VAS_GROUP_ID")
	private BigDecimal vasGroupId;

	@Column(name="VAS_NAME")
	private String vasName;

	public GroupVasItem() {
	}

	public BigDecimal getVasGroupId() {
		return this.vasGroupId;
	}

	public void setVasGroupId(BigDecimal vasGroupId) {
		this.vasGroupId = vasGroupId;
	}

	public String getVasName() {
		return this.vasName;
	}

	public void setVasName(String vasName) {
		this.vasName = vasName;
	}

}