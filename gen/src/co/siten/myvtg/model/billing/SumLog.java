package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SUM_LOG database table.
 * 
 */
@Entity
@Table(name="SUM_LOG")
@NamedQuery(name="SumLog.findAll", query="SELECT s FROM SumLog s")
public class SumLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BEG_TIME")
	private Date begTime;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	@Column(name="ERR_MSG")
	private String errMsg;

	@Column(name="LOG_TYPE")
	private String logType;

	@Temporal(TemporalType.DATE)
	@Column(name="SUM_DATE")
	private Date sumDate;

	public SumLog() {
	}

	public Date getBegTime() {
		return this.begTime;
	}

	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public Date getSumDate() {
		return this.sumDate;
	}

	public void setSumDate(Date sumDate) {
		this.sumDate = sumDate;
	}

}