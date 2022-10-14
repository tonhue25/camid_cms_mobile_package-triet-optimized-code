package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_SYNC_LOG database table.
 * 
 */
@Entity
@Table(name="UD_SYNC_LOG")
@NamedQuery(name="UdSyncLog.findAll", query="SELECT u FROM UdSyncLog u")
public class UdSyncLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="FINISHED_TIME")
	private Date finishedTime;

	@Column(name="SYNC_ID")
	private BigDecimal syncId;

	@Column(name="SYNC_NAME")
	private String syncName;

	public UdSyncLog() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFinishedTime() {
		return this.finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	public BigDecimal getSyncId() {
		return this.syncId;
	}

	public void setSyncId(BigDecimal syncId) {
		this.syncId = syncId;
	}

	public String getSyncName() {
		return this.syncName;
	}

	public void setSyncName(String syncName) {
		this.syncName = syncName;
	}

}