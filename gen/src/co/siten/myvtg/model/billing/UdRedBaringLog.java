package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_RED_BARING_LOG database table.
 * 
 */
@Entity
@Table(name="UD_RED_BARING_LOG")
@NamedQuery(name="UdRedBaringLog.findAll", query="SELECT u FROM UdRedBaringLog u")
public class UdRedBaringLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="END_MAKE_BLOCK")
	private Date endMakeBlock;

	@Temporal(TemporalType.DATE)
	@Column(name="END_MAKE_LIMIT")
	private Date endMakeLimit;

	@Temporal(TemporalType.DATE)
	@Column(name="END_SEND_SMS")
	private Date endSendSms;

	@Column(name="NUM_PROCESS")
	private BigDecimal numProcess;

	@Temporal(TemporalType.DATE)
	@Column(name="RUN_DATE")
	private Date runDate;

	@Column(name="RUN_MODE")
	private BigDecimal runMode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private String status;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public UdRedBaringLog() {
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Date getEndMakeBlock() {
		return this.endMakeBlock;
	}

	public void setEndMakeBlock(Date endMakeBlock) {
		this.endMakeBlock = endMakeBlock;
	}

	public Date getEndMakeLimit() {
		return this.endMakeLimit;
	}

	public void setEndMakeLimit(Date endMakeLimit) {
		this.endMakeLimit = endMakeLimit;
	}

	public Date getEndSendSms() {
		return this.endSendSms;
	}

	public void setEndSendSms(Date endSendSms) {
		this.endSendSms = endSendSms;
	}

	public BigDecimal getNumProcess() {
		return this.numProcess;
	}

	public void setNumProcess(BigDecimal numProcess) {
		this.numProcess = numProcess;
	}

	public Date getRunDate() {
		return this.runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public BigDecimal getRunMode() {
		return this.runMode;
	}

	public void setRunMode(BigDecimal runMode) {
		this.runMode = runMode;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}