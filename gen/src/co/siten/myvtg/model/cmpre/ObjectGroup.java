package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the OBJECT_GROUP database table.
 * 
 */
@Entity
@Table(name="OBJECT_GROUP")
@NamedQuery(name="ObjectGroup.findAll", query="SELECT o FROM ObjectGroup o")
public class ObjectGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String code;

	@Column(name="DEP_GROUP_ID")
	private BigDecimal depGroupId;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	private String name;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	public ObjectGroup() {
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

	public BigDecimal getDepGroupId() {
		return this.depGroupId;
	}

	public void setDepGroupId(BigDecimal depGroupId) {
		this.depGroupId = depGroupId;
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

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}