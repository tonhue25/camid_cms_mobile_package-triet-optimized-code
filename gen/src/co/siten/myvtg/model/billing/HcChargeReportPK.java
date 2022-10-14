package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HC_CHARGE_REPORT database table.
 * 
 */
@Embeddable
public class HcChargeReportPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CHARGE_REPORT_ID")
	private long chargeReportId;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private java.util.Date billCycle;

	public HcChargeReportPK() {
	}
	public long getChargeReportId() {
		return this.chargeReportId;
	}
	public void setChargeReportId(long chargeReportId) {
		this.chargeReportId = chargeReportId;
	}
	public java.util.Date getBillCycle() {
		return this.billCycle;
	}
	public void setBillCycle(java.util.Date billCycle) {
		this.billCycle = billCycle;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HcChargeReportPK)) {
			return false;
		}
		HcChargeReportPK castOther = (HcChargeReportPK)other;
		return 
			(this.chargeReportId == castOther.chargeReportId)
			&& this.billCycle.equals(castOther.billCycle);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.chargeReportId ^ (this.chargeReportId >>> 32)));
		hash = hash * prime + this.billCycle.hashCode();
		
		return hash;
	}
}