package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SYNC_CM_NIS database table.
 * 
 */
@Entity
@Table(name="SYNC_CM_NIS")
@NamedQuery(name="SyncCmNi.findAll", query="SELECT s FROM SyncCmNi s")
public class SyncCmNi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QUEUE_ID")
	private long queueId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	private String description;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	@Column(name="MAX_PROCESS")
	private BigDecimal maxProcess;

	@Column(name="NEW_CUST_ID")
	private BigDecimal newCustId;

	@Column(name="NUM_PROCESS")
	private BigDecimal numProcess;

	@Column(name="OLD_CUST_ID")
	private BigDecimal oldCustId;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SyncCmNi() {
	}

	public long getQueueId() {
		return this.queueId;
	}

	public void setQueueId(long queueId) {
		this.queueId = queueId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getNewCustId() {
		return this.newCustId;
	}

	public void setNewCustId(BigDecimal newCustId) {
		this.newCustId = newCustId;
	}

	public BigDecimal getNumProcess() {
		return this.numProcess;
	}

	public void setNumProcess(BigDecimal numProcess) {
		this.numProcess = numProcess;
	}

	public BigDecimal getOldCustId() {
		return this.oldCustId;
	}

	public void setOldCustId(BigDecimal oldCustId) {
		this.oldCustId = oldCustId;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}