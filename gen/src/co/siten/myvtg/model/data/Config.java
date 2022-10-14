package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONFIG database table.
 * 
 */
@Entity
@NamedQuery(name="Config.findAll", query="SELECT c FROM Config c")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEFAULT_VALUE")
	private String defaultValue;

	@Column(name="\"MODULE\"")
	private String module;

	private String note;

	@Column(name="PARAM_NAME")
	private String paramName;

	@Column(name="PARAM_VALUE")
	private String paramValue;

	public Config() {
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}