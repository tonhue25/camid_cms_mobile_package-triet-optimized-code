package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DC_VTCI_PSTN_TEMP database table.
 * 
 */
@Embeddable
public class DcVtciPstnTempPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private long subId;

	private String isdn;

	@Column(name="BILL_CYCLE")
	private String billCycle;

	public DcVtciPstnTempPK() {
	}
	public long getSubId() {
		return this.subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getIsdn() {
		return this.isdn;
	}
	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}
	public String getBillCycle() {
		return this.billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DcVtciPstnTempPK)) {
			return false;
		}
		DcVtciPstnTempPK castOther = (DcVtciPstnTempPK)other;
		return 
			(this.subId == castOther.subId)
			&& this.isdn.equals(castOther.isdn)
			&& this.billCycle.equals(castOther.billCycle);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.subId ^ (this.subId >>> 32)));
		hash = hash * prime + this.isdn.hashCode();
		hash = hash * prime + this.billCycle.hashCode();
		
		return hash;
	}
}