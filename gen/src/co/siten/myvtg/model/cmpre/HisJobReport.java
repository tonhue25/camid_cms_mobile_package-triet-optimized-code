package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HIS_JOB_REPORT database table.
 * 
 */
@Entity
@Table(name="HIS_JOB_REPORT")
@NamedQuery(name="HisJobReport.findAll", query="SELECT h FROM HisJobReport h")
public class HisJobReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DB_TYPE")
	private BigDecimal dbType;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="ISSUE_DATE")
	private Object issueDate;

	@Column(name="LAST_NUM_RECORD")
	private BigDecimal lastNumRecord;

	@Column(name="REPORT_CODE")
	private String reportCode;

	@Column(name="REPORT_NAME")
	private String reportName;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="TEMP_TABLE_NAME")
	private String tempTableName;

	@Column(name="TOTAL_RECORD")
	private BigDecimal totalRecord;

	public HisJobReport() {
	}

	public BigDecimal getDbType() {
		return this.dbType;
	}

	public void setDbType(BigDecimal dbType) {
		this.dbType = dbType;
	}

	public BigDecimal getHisId() {
		return this.hisId;
	}

	public void setHisId(BigDecimal hisId) {
		this.hisId = hisId;
	}

	public Object getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Object issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getLastNumRecord() {
		return this.lastNumRecord;
	}

	public void setLastNumRecord(BigDecimal lastNumRecord) {
		this.lastNumRecord = lastNumRecord;
	}

	public String getReportCode() {
		return this.reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getTempTableName() {
		return this.tempTableName;
	}

	public void setTempTableName(String tempTableName) {
		this.tempTableName = tempTableName;
	}

	public BigDecimal getTotalRecord() {
		return this.totalRecord;
	}

	public void setTotalRecord(BigDecimal totalRecord) {
		this.totalRecord = totalRecord;
	}

}