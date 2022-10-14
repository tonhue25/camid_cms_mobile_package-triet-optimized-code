package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PSTN_CHAN_CAT_LOG database table.
 * 
 */
@Entity
@Table(name="PSTN_CHAN_CAT_LOG")
@NamedQuery(name="PstnChanCatLog.findAll", query="SELECT p FROM PstnChanCatLog p")
public class PstnChanCatLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String process;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public PstnChanCatLog() {
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

}