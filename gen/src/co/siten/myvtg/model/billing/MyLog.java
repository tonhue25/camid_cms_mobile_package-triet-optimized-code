package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MY_LOG database table.
 * 
 */
@Entity
@Table(name="MY_LOG")
@NamedQuery(name="MyLog.findAll", query="SELECT m FROM MyLog m")
public class MyLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CURRENT_RECORD")
	private BigDecimal currentRecord;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	@Column(name="IS_ACTIVE")
	private BigDecimal isActive;

	@Column(name="PACKAGE_NAME")
	private String packageName;

	@Column(name="PROCEDURE_NAME")
	private String procedureName;

	@Column(name="RECORD_TYPE")
	private String recordType;

	@Column(name="SOURCE_TABLE")
	private String sourceTable;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="THREAD_NUMBER")
	private BigDecimal threadNumber;

	public MyLog() {
	}

	public BigDecimal getCurrentRecord() {
		return this.currentRecord;
	}

	public void setCurrentRecord(BigDecimal currentRecord) {
		this.currentRecord = currentRecord;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getProcedureName() {
		return this.procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getRecordType() {
		return this.recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getSourceTable() {
		return this.sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getThreadNumber() {
		return this.threadNumber;
	}

	public void setThreadNumber(BigDecimal threadNumber) {
		this.threadNumber = threadNumber;
	}

}