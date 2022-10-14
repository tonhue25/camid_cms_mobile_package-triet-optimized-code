package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EMAIL_QUEUE database table.
 * 
 */
@Entity
@Table(name="EMAIL_QUEUE")
@NamedQuery(name="EmailQueue.findAll", query="SELECT e FROM EmailQueue e")
public class EmailQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="QUEUE_ID")
	private long queueId;

	@Column(name="ATTACH_FILE")
	private String attachFile;

	private String cc;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="DELETE_ATTACH")
	private String deleteAttach;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	@Column(name="LAST_RESULT")
	private String lastResult;

	@Column(name="MAX_PROCESS")
	private BigDecimal maxProcess;

	@Column(name="NUM_PROCESS")
	private BigDecimal numProcess;

	private String receiver;

	private BigDecimal status;

	private String subject;

	public EmailQueue() {
	}

	public long getQueueId() {
		return this.queueId;
	}

	public void setQueueId(long queueId) {
		this.queueId = queueId;
	}

	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
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

	public String getDeleteAttach() {
		return this.deleteAttach;
	}

	public void setDeleteAttach(String deleteAttach) {
		this.deleteAttach = deleteAttach;
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

	public String getLastResult() {
		return this.lastResult;
	}

	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
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

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}