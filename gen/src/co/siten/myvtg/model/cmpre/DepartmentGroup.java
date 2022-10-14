package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DEPARTMENT_GROUP database table.
 * 
 */
@Entity
@Table(name="DEPARTMENT_GROUP")
@NamedQuery(name="DepartmentGroup.findAll", query="SELECT d FROM DepartmentGroup d")
public class DepartmentGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String code;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	private String name;

	@Column(name="START_DATETIME")
	private Object startDatetime;

	private BigDecimal status;

	public DepartmentGroup() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Object startDatetime) {
		this.startDatetime = startDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}