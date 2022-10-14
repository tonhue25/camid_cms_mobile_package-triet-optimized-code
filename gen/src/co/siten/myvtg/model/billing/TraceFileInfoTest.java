package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TRACE_FILE_INFO_TEST database table.
 * 
 */
@Entity
@Table(name="TRACE_FILE_INFO_TEST")
@NamedQuery(name="TraceFileInfoTest.findAll", query="SELECT t FROM TraceFileInfoTest t")
public class TraceFileInfoTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="FILE_ID")
	private BigDecimal fileId;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_IMPORT_DATE")
	private Date lastImportDate;

	@Column(name="NUMBER_OF_COMMITTED_LINE")
	private BigDecimal numberOfCommittedLine;

	@Column(name="NUMBER_OF_IMPORT")
	private String numberOfImport;

	@Column(name="NUMBER_OF_LINE")
	private BigDecimal numberOfLine;

	@Column(name="PROCESS_TYPE")
	private String processType;

	private BigDecimal status;

	@Column(name="STATUS_TYPE")
	private String statusType;

	@Column(name="TOTAL_BYTES")
	private BigDecimal totalBytes;

	@Column(name="TRACE_FILE_INFO")
	private BigDecimal traceFileInfo;

	public TraceFileInfoTest() {
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getLastImportDate() {
		return this.lastImportDate;
	}

	public void setLastImportDate(Date lastImportDate) {
		this.lastImportDate = lastImportDate;
	}

	public BigDecimal getNumberOfCommittedLine() {
		return this.numberOfCommittedLine;
	}

	public void setNumberOfCommittedLine(BigDecimal numberOfCommittedLine) {
		this.numberOfCommittedLine = numberOfCommittedLine;
	}

	public String getNumberOfImport() {
		return this.numberOfImport;
	}

	public void setNumberOfImport(String numberOfImport) {
		this.numberOfImport = numberOfImport;
	}

	public BigDecimal getNumberOfLine() {
		return this.numberOfLine;
	}

	public void setNumberOfLine(BigDecimal numberOfLine) {
		this.numberOfLine = numberOfLine;
	}

	public String getProcessType() {
		return this.processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getStatusType() {
		return this.statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public BigDecimal getTotalBytes() {
		return this.totalBytes;
	}

	public void setTotalBytes(BigDecimal totalBytes) {
		this.totalBytes = totalBytes;
	}

	public BigDecimal getTraceFileInfo() {
		return this.traceFileInfo;
	}

	public void setTraceFileInfo(BigDecimal traceFileInfo) {
		this.traceFileInfo = traceFileInfo;
	}

}