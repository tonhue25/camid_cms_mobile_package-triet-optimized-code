package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the V_LOG database table.
 * 
 */
@Entity
@Table(name="V_LOG")
@NamedQuery(name="VLog.findAll", query="SELECT v FROM VLog v")
public class VLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String content;

	@Column(name="LOG_CODE")
	private String logCode;

	@Column(name="LOG_LEVEL")
	private String logLevel;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIME")
	private Date logTime;

	private String logger;

	@Column(name="REF_ID")
	private String refId;

	public VLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogCode() {
		return this.logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	public String getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getLogger() {
		return this.logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

}