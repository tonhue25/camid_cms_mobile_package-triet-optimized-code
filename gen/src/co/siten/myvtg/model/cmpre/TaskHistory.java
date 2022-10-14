package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TASK_HISTORY database table.
 * 
 */
@Entity
@Table(name="TASK_HISTORY")
@NamedQuery(name="TaskHistory.findAll", query="SELECT t FROM TaskHistory t")
public class TaskHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_TYPE")
	private BigDecimal actionType;

	private String description;

	private BigDecimal id;

	@Column(name="LOG_DATETIME")
	private Object logDatetime;

	@Column(name="REASON_CODE")
	private String reasonCode;

	@Column(name="TASK_ID")
	private BigDecimal taskId;

	public TaskHistory() {
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getLogDatetime() {
		return this.logDatetime;
	}

	public void setLogDatetime(Object logDatetime) {
		this.logDatetime = logDatetime;
	}

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public BigDecimal getTaskId() {
		return this.taskId;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

}