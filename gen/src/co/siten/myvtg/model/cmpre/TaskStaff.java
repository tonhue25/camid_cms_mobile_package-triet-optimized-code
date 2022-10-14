package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TASK_STAFF database table.
 * 
 */
@Entity
@Table(name="TASK_STAFF")
@NamedQuery(name="TaskStaff.findAll", query="SELECT t FROM TaskStaff t")
public class TaskStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	private BigDecimal status;

	@Column(name="TASK_ID")
	private BigDecimal taskId;

	@Column(name="TASK_STAFF_ID")
	private BigDecimal taskStaffId;

	@Column(name="USER_CREATE")
	private String userCreate;

	public TaskStaff() {
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

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
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

	public BigDecimal getTaskStaffId() {
		return this.taskStaffId;
	}

	public void setTaskStaffId(BigDecimal taskStaffId) {
		this.taskStaffId = taskStaffId;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}