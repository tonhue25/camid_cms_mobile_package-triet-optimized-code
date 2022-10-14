package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the KS_CHK_SUB_USAGE_CHARGE_SAI database table.
 * 
 */
@Entity
@Table(name="KS_CHK_SUB_USAGE_CHARGE_SAI")
@NamedQuery(name="KsChkSubUsageChargeSai.findAll", query="SELECT k FROM KsChkSubUsageChargeSai k")
public class KsChkSubUsageChargeSai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public KsChkSubUsageChargeSai() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}