package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAP_ITEM_2_MIG database table.
 * 
 */
@Entity
@Table(name="MAP_ITEM_2_MIG")
@NamedQuery(name="MapItem2Mig.findAll", query="SELECT m FROM MapItem2Mig m")
public class MapItem2Mig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BCCS_ITEM_ID")
	private BigDecimal bccsItemId;

	@Column(name="FPT_ITEM_ID")
	private BigDecimal fptItemId;

	@Column(name="FPT_ITEM_NAME")
	private String fptItemName;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	private String status;

	private String tax;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public MapItem2Mig() {
	}

	public BigDecimal getBccsItemId() {
		return this.bccsItemId;
	}

	public void setBccsItemId(BigDecimal bccsItemId) {
		this.bccsItemId = bccsItemId;
	}

	public BigDecimal getFptItemId() {
		return this.fptItemId;
	}

	public void setFptItemId(BigDecimal fptItemId) {
		this.fptItemId = fptItemId;
	}

	public String getFptItemName() {
		return this.fptItemName;
	}

	public void setFptItemName(String fptItemName) {
		this.fptItemName = fptItemName;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTax() {
		return this.tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}