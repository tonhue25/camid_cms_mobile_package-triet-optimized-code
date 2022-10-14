package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SYNC_CM_NIS_LOG database table.
 * 
 */
@Entity
@Table(name="SYNC_CM_NIS_LOG")
@NamedQuery(name="SyncCmNisLog.findAll", query="SELECT s FROM SyncCmNisLog s")
public class SyncCmNisLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOG_ID")
	private long logId;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	@Column(name="QUEUE_ID")
	private BigDecimal queueId;

	public SyncCmNisLog() {
	}

	public long getLogId() {
		return this.logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getQueueId() {
		return this.queueId;
	}

	public void setQueueId(BigDecimal queueId) {
		this.queueId = queueId;
	}

}