package co.siten.myvtg.model.myvtg;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the APP_PARAM database table.
 * 
 */
@Entity
@Table(name = "APP_PARAM")
@NamedQuery(name = "AppParam.findAll", query = "SELECT a FROM AppParam a")
public class AppParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String des;

	private String name;

	@Column(name = "\"TYPE\"")
	private Integer type;

	@Column(name = "\"VALUE\"")
	private String value;

	@Column(name = "app_version")
	private String appVersion;

	public AppParam() {
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}