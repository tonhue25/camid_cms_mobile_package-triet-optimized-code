package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACCOUNT_HISTORY database table.
 * 
 */
@Embeddable
public class AccountHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MODIFY_DATE")
	private String modifyDate;

	@Column(name="SUB_ID")
	private long subId;

	public AccountHistoryPK() {
	}
	public String getModifyDate() {
		return this.modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getSubId() {
		return this.subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountHistoryPK)) {
			return false;
		}
		AccountHistoryPK castOther = (AccountHistoryPK)other;
		return 
			this.modifyDate.equals(castOther.modifyDate)
			&& (this.subId == castOther.subId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.modifyDate.hashCode();
		hash = hash * prime + ((int) (this.subId ^ (this.subId >>> 32)));
		
		return hash;
	}
}