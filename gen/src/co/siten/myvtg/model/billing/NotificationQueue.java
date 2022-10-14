package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the NOTIFICATION_QUEUE database table.
 * 
 */
@Entity
@Table(name="NOTIFICATION_QUEUE")
@NamedQuery(name="NotificationQueue.findAll", query="SELECT n FROM NotificationQueue n")
public class NotificationQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="QUEUE_ID")
	private long queueId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Temporal(TemporalType.DATE)
	@Column(name="CYCLE_TIME")
	private Date cycleTime;

	private String description;

	@Column(name="EMAIL_QUEUE_ID")
	private BigDecimal emailQueueId;

	private String ip;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="ROW_ID")
	private String rowId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SMS_QUEUE_ID")
	private BigDecimal smsQueueId;

	@Column(name="STAFF_CODE")
	private String staffCode;

	private BigDecimal status;

	@Column(name="TABLE_NAME")
	private String tableName;

	public NotificationQueue() {
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

	public Date getCycleTime() {
		return this.cycleTime;
	}

	public void setCycleTime(Date cycleTime) {
		this.cycleTime = cycleTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getEmailQueueId() {
		return this.emailQueueId;
	}

	public void setEmailQueueId(BigDecimal emailQueueId) {
		this.emailQueueId = emailQueueId;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRowId() {
		return this.rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getSmsQueueId() {
		return this.smsQueueId;
	}

	public void setSmsQueueId(BigDecimal smsQueueId) {
		this.smsQueueId = smsQueueId;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}