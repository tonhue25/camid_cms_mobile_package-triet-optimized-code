package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the IMPORT_FILE database table.
 * 
 */
@Entity
@Table(name="IMPORT_FILE")
@NamedQuery(name="ImportFile.findAll", query="SELECT i FROM ImportFile i")
public class ImportFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FILE_ID")
	private BigDecimal fileId;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_SIZE")
	private BigDecimal fileSize;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_CALL_STAMP")
	private Date firstCallStamp;

	@Temporal(TemporalType.DATE)
	@Column(name="IMPORT_DATE")
	private Date importDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CALL_STAMP")
	private Date lastCallStamp;

	@Temporal(TemporalType.DATE)
	@Column(name="PROCESS_DATE")
	private Date processDate;

	@Column(name="TOT_IMPORT_CALL")
	private BigDecimal totImportCall;

	public ImportFile() {
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

	public BigDecimal getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public Date getFirstCallStamp() {
		return this.firstCallStamp;
	}

	public void setFirstCallStamp(Date firstCallStamp) {
		this.firstCallStamp = firstCallStamp;
	}

	public Date getImportDate() {
		return this.importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public Date getLastCallStamp() {
		return this.lastCallStamp;
	}

	public void setLastCallStamp(Date lastCallStamp) {
		this.lastCallStamp = lastCallStamp;
	}

	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public BigDecimal getTotImportCall() {
		return this.totImportCall;
	}

	public void setTotImportCall(BigDecimal totImportCall) {
		this.totImportCall = totImportCall;
	}

}