package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BI_SUB_PROMOTION_DETAIL database table.
 * 
 */
@Embeddable
public class BiSubPromotionDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private long subId;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="BILL_CYCLE_FROM")
	private long billCycleFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private java.util.Date billCycle;

	public BiSubPromotionDetailPK() {
	}
	public long getSubId() {
		return this.subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getPromProgramCode() {
		return this.promProgramCode;
	}
	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}
	public long getBillCycleFrom() {
		return this.billCycleFrom;
	}
	public void setBillCycleFrom(long billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
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
		if (!(other instanceof BiSubPromotionDetailPK)) {
			return false;
		}
		BiSubPromotionDetailPK castOther = (BiSubPromotionDetailPK)other;
		return 
			(this.subId == castOther.subId)
			&& this.promProgramCode.equals(castOther.promProgramCode)
			&& (this.billCycleFrom == castOther.billCycleFrom)
			&& this.billCycle.equals(castOther.billCycle);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.subId ^ (this.subId >>> 32)));
		hash = hash * prime + this.promProgramCode.hashCode();
		hash = hash * prime + ((int) (this.billCycleFrom ^ (this.billCycleFrom >>> 32)));
		hash = hash * prime + this.billCycle.hashCode();
		
		return hash;
	}
}