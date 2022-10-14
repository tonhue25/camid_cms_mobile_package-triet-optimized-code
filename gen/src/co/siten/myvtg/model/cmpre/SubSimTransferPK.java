package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SUB_SIM_TRANSFER database table.
 * 
 */
@Embeddable
public class SubSimTransferPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="END_DATETIME")
	private String endDatetime;

	public SubSimTransferPK() {
	}
	public String getIsdn() {
		return this.isdn;
	}
	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}
	public String getEndDatetime() {
		return this.endDatetime;
	}
	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubSimTransferPK)) {
			return false;
		}
		SubSimTransferPK castOther = (SubSimTransferPK)other;
		return 
			this.isdn.equals(castOther.isdn)
			&& this.endDatetime.equals(castOther.endDatetime);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.isdn.hashCode();
		hash = hash * prime + this.endDatetime.hashCode();
		
		return hash;
	}
}