package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the "EXECUTE" database table.
 * 
 */
@Entity
@Table(name="\"EXECUTE\"")
@NamedQuery(name="Execute.findAll", query="SELECT e FROM Execute e")
public class Execute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CURRENT_POINT")
	private BigDecimal currentPoint;

	@Column(name="FILE_ID")
	private BigDecimal fileId;

	@Column(name="PROCESS_ID")
	private BigDecimal processId;

	@Temporal(TemporalType.DATE)
	@Column(name="SUBMIT_TIME")
	private Date submitTime;

	public Execute() {
	}

	public BigDecimal getCurrentPoint() {
		return this.currentPoint;
	}

	public void setCurrentPoint(BigDecimal currentPoint) {
		this.currentPoint = currentPoint;
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public BigDecimal getProcessId() {
		return this.processId;
	}

	public void setProcessId(BigDecimal processId) {
		this.processId = processId;
	}

	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

}