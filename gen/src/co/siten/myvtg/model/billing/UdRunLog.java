package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_RUN_LOG database table.
 * 
 */
@Entity
@Table(name="UD_RUN_LOG")
@NamedQuery(name="UdRunLog.findAll", query="SELECT u FROM UdRunLog u")
public class UdRunLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	@Column(name="ERR_LOG")
	private String errLog;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	public UdRunLog() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getErrLog() {
		return this.errLog;
	}

	public void setErrLog(String errLog) {
		this.errLog = errLog;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}