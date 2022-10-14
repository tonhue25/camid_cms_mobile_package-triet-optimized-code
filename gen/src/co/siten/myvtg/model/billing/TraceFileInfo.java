package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TRACE_FILE_INFO database table.
 * 
 */
@Entity
@Table(name="TRACE_FILE_INFO")
@NamedQuery(name="TraceFileInfo.findAll", query="SELECT t FROM TraceFileInfo t")
public class TraceFileInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TraceFileInfoPK id;

	@Column(name="ERROR_MESSAGE")
	private String errorMessage;

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

	@Column(name="SERVICE_CODE")
	private String serviceCode;

	private BigDecimal status;

	@Column(name="STATUS_TYPE")
	private String statusType;

	@Column(name="TOTAL_BYTES")
	private BigDecimal totalBytes;

	public TraceFileInfo() {
	}

	public TraceFileInfoPK getId() {
		return this.id;
	}

	public void setId(TraceFileInfoPK id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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

}