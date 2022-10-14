package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TASK database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	private Object deadline;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="OLD_TASK_ID")
	private BigDecimal oldTaskId;

	@Column(name="SHOP_ID")
	private BigDecimal shopId;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	private BigDecimal status;

	@Column(name="TASK_ID")
	private BigDecimal taskId;

	@Column(name="TASK_NAME")
	private String taskName;

	@Column(name="TASK_TYPE_ID")
	private BigDecimal taskTypeId;

	@Column(name="USER_CREATE")
	private String userCreate;

	public Task() {
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCustReqId() {
		return this.custReqId;
	}

	public void setCustReqId(BigDecimal custReqId) {
		this.custReqId = custReqId;
	}

	public Object getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Object deadline) {
		this.deadline = deadline;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getOldTaskId() {
		return this.oldTaskId;
	}

	public void setOldTaskId(BigDecimal oldTaskId) {
		this.oldTaskId = oldTaskId;
	}

	public BigDecimal getShopId() {
		return this.shopId;
	}

	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTaskId() {
		return this.taskId;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public BigDecimal getTaskTypeId() {
		return this.taskTypeId;
	}

	public void setTaskTypeId(BigDecimal taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}