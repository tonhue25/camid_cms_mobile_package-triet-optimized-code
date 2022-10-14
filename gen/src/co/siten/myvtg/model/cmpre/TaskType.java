package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TASK_TYPE database table.
 * 
 */
@Entity
@Table(name="TASK_TYPE")
@NamedQuery(name="TaskType.findAll", query="SELECT t FROM TaskType t")
public class TaskType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private BigDecimal status;

	@Column(name="TASK_TYPE_ID")
	private BigDecimal taskTypeId;

	public TaskType() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getTaskTypeId() {
		return this.taskTypeId;
	}

	public void setTaskTypeId(BigDecimal taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

}