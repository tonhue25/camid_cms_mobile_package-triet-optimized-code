package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FAMILY_NUMBER_HP database table.
 * 
 */
@Embeddable
public class FamilyNumberHpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private long subId;

	@Column(name="ISDN_DISCOUNT")
	private String isdnDiscount;

	@Column(name="STA_DATETIME")
	private String staDatetime;

	public FamilyNumberHpPK() {
	}
	public long getSubId() {
		return this.subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getIsdnDiscount() {
		return this.isdnDiscount;
	}
	public void setIsdnDiscount(String isdnDiscount) {
		this.isdnDiscount = isdnDiscount;
	}
	public String getStaDatetime() {
		return this.staDatetime;
	}
	public void setStaDatetime(String staDatetime) {
		this.staDatetime = staDatetime;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FamilyNumberHpPK)) {
			return false;
		}
		FamilyNumberHpPK castOther = (FamilyNumberHpPK)other;
		return 
			(this.subId == castOther.subId)
			&& this.isdnDiscount.equals(castOther.isdnDiscount)
			&& this.staDatetime.equals(castOther.staDatetime);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.subId ^ (this.subId >>> 32)));
		hash = hash * prime + this.isdnDiscount.hashCode();
		hash = hash * prime + this.staDatetime.hashCode();
		
		return hash;
	}
}