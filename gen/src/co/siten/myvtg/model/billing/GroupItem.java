package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GROUP_ITEMS database table.
 * 
 */
@Entity
@Table(name="GROUP_ITEMS")
@NamedQuery(name="GroupItem.findAll", query="SELECT g FROM GroupItem g")
public class GroupItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FPT_ITEM_ID")
	private BigDecimal fptItemId;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	private String name;

	public GroupItem() {
	}

	public BigDecimal getFptItemId() {
		return this.fptItemId;
	}

	public void setFptItemId(BigDecimal fptItemId) {
		this.fptItemId = fptItemId;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}