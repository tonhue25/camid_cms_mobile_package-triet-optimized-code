package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_SYNC_HISTORY database table.
 * 
 */
@Entity
@Table(name="RP_SYNC_HISTORY")
@NamedQuery(name="RpSyncHistory.findAll", query="SELECT r FROM RpSyncHistory r")
public class RpSyncHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	private String note;

	@Column(name="REPORT_TYPE")
	private String reportType;

	@Column(name="SYNC_DATETIME")
	private Object syncDatetime;

	public RpSyncHistory() {
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Object getSyncDatetime() {
		return this.syncDatetime;
	}

	public void setSyncDatetime(Object syncDatetime) {
		this.syncDatetime = syncDatetime;
	}

}