package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RP_LOG_ERROR database table.
 * 
 */
@Entity
@Table(name="RP_LOG_ERROR")
@NamedQuery(name="RpLogError.findAll", query="SELECT r FROM RpLogError r")
public class RpLogError implements Serializable {
	private static final long serialVersionUID = 1L;

	private String app;

	private String description;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="\"OBJECT\"")
	private String object;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="\"TYPE\"")
	private String type;

	public RpLogError() {
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}