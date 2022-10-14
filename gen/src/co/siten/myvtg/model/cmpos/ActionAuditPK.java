package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACTION_AUDIT database table.
 * 
 */
@Embeddable
public class ActionAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private long actionAuditId;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATETIME")
	private java.util.Date issueDatetime;

	public ActionAuditPK() {
	}
	public long getActionAuditId() {
		return this.actionAuditId;
	}
	public void setActionAuditId(long actionAuditId) {
		this.actionAuditId = actionAuditId;
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
		if (!(other instanceof ActionAuditPK)) {
			return false;
		}
		ActionAuditPK castOther = (ActionAuditPK)other;
		return 
			(this.actionAuditId == castOther.actionAuditId)
			&& this.issueDatetime.equals(castOther.issueDatetime);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.actionAuditId ^ (this.actionAuditId >>> 32)));
		hash = hash * prime + this.issueDatetime.hashCode();
		
		return hash;
	}
}