package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UD_ALARM_IMPORT_THREAD database table.
 * 
 */
@Entity
@Table(name="UD_ALARM_IMPORT_THREAD")
@NamedQuery(name="UdAlarmImportThread.findAll", query="SELECT u FROM UdAlarmImportThread u")
public class UdAlarmImportThread implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PARAM_VALUE")
	private String paramValue;

	@Column(name="THREAD_NAME")
	private String threadName;

	@Column(name="THREAD_TYPE")
	private String threadType;

	public UdAlarmImportThread() {
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getThreadName() {
		return this.threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadType() {
		return this.threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}

}