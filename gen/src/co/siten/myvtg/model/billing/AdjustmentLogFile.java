package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADJUSTMENT_LOG_FILE database table.
 * 
 */
@Entity
@Table(name="ADJUSTMENT_LOG_FILE")
@NamedQuery(name="AdjustmentLogFile.findAll", query="SELECT a FROM AdjustmentLogFile a")
public class AdjustmentLogFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJUSTMENT_FILE_ID")
	private BigDecimal adjustmentFileId;

	@Column(name="ADJUSTMENT_FILE_NAME")
	private String adjustmentFileName;

	@Column(name="ADJUSTMENT_TYPE")
	private String adjustmentType;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="NUMBER_OF_ERROR_LINE")
	private BigDecimal numberOfErrorLine;

	@Column(name="NUMBER_OF_LINE")
	private BigDecimal numberOfLine;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	public AdjustmentLogFile() {
	}

	public BigDecimal getAdjustmentFileId() {
		return this.adjustmentFileId;
	}

	public void setAdjustmentFileId(BigDecimal adjustmentFileId) {
		this.adjustmentFileId = adjustmentFileId;
	}

	public String getAdjustmentFileName() {
		return this.adjustmentFileName;
	}

	public void setAdjustmentFileName(String adjustmentFileName) {
		this.adjustmentFileName = adjustmentFileName;
	}

	public String getAdjustmentType() {
		return this.adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public BigDecimal getNumberOfErrorLine() {
		return this.numberOfErrorLine;
	}

	public void setNumberOfErrorLine(BigDecimal numberOfErrorLine) {
		this.numberOfErrorLine = numberOfErrorLine;
	}

	public BigDecimal getNumberOfLine() {
		return this.numberOfLine;
	}

	public void setNumberOfLine(BigDecimal numberOfLine) {
		this.numberOfLine = numberOfLine;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

}