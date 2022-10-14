package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TRACE_FILE_INFO database table.
 * 
 */
@Embeddable
public class TraceFileInfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TRACE_FILE_INFO")
	private long traceFileInfo;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private java.util.Date createdDate;

	public TraceFileInfoPK() {
	}
	public long getTraceFileInfo() {
		return this.traceFileInfo;
	}
	public void setTraceFileInfo(long traceFileInfo) {
		this.traceFileInfo = traceFileInfo;
	}
	public java.util.Date getCreatedDate() {
		return this.createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TraceFileInfoPK)) {
			return false;
		}
		TraceFileInfoPK castOther = (TraceFileInfoPK)other;
		return 
			(this.traceFileInfo == castOther.traceFileInfo)
			&& this.createdDate.equals(castOther.createdDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.traceFileInfo ^ (this.traceFileInfo >>> 32)));
		hash = hash * prime + this.createdDate.hashCode();
		
		return hash;
	}
}