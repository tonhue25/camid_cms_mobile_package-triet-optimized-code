package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ERROR_LOG database table.
 * 
 */
@Entity
@Table(name="ERROR_LOG")
@NamedQuery(name="ErrorLog.findAll", query="SELECT e FROM ErrorLog e")
public class ErrorLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="ERR_CODE")
	private String errCode;

	@Column(name="FUNCTION_NAME")
	private String functionName;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TIME\"")
	private Date time;

	public ErrorLog() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}