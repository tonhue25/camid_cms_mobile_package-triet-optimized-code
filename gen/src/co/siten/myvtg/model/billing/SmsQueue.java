package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SMS_QUEUE database table.
 * 
 */
@Entity
@Table(name="SMS_QUEUE")
@NamedQuery(name="SmsQueue.findAll", query="SELECT s FROM SmsQueue s")
public class SmsQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="QUEUE_ID")
	private long queueId;

	private String cc;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	@Column(name="MAX_PROCESS")
	private BigDecimal maxProcess;

	@Column(name="NUM_PROCESS")
	private BigDecimal numProcess;

	private BigDecimal status;

	public SmsQueue() {
	}

	public long getQueueId() {
		return this.queueId;
	}

	public void setQueueId(long queueId) {
		this.queueId = queueId;
	}

	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getMaxProcess() {
		return this.maxProcess;
	}

	public void setMaxProcess(BigDecimal maxProcess) {
		this.maxProcess = maxProcess;
	}

	public BigDecimal getNumProcess() {
		return this.numProcess;
	}

	public void setNumProcess(BigDecimal numProcess) {
		this.numProcess = numProcess;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}