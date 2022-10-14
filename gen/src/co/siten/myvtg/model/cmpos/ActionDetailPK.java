package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACTION_DETAIL database table.
 * 
 */
@Embeddable
public class ActionDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_DETAIL_ID")
	private long actionDetailId;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATETIME")
	private java.util.Date issueDatetime;

	public ActionDetailPK() {
	}
	public long getActionDetailId() {
		return this.actionDetailId;
	}
	public void setActionDetailId(long actionDetailId) {
		this.actionDetailId = actionDetailId;
	}
	public java.util.Date getIssueDatetime() {
		return this.issueDatetime;
	}
	public void setIssueDatetime(java.util.Date issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActionDetailPK)) {
			return false;
		}
		ActionDetailPK castOther = (ActionDetailPK)other;
		return 
			(this.actionDetailId == castOther.actionDetailId)
			&& this.issueDatetime.equals(castOther.issueDatetime);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.actionDetailId ^ (this.actionDetailId >>> 32)));
		hash = hash * prime + this.issueDatetime.hashCode();
		
		return hash;
	}
}