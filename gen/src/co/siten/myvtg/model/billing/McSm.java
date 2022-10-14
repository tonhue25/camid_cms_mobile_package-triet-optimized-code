package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MC_SMS database table.
 * 
 */
@Entity
@Table(name="MC_SMS")
@NamedQuery(name="McSm.findAll", query="SELECT m FROM McSm m")
public class McSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_ID")
	private BigDecimal applicationId;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="FILE_ID")
	private BigDecimal fileId;

	private String imsi;

	@Column(name="MC_SMS_ID")
	private BigDecimal mcSmsId;

	@Temporal(TemporalType.DATE)
	@Column(name="PROCESS_DATE")
	private Date processDate;

	@Column(name="SMS_DEBIT_LOG_ID")
	private BigDecimal smsDebitLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private BigDecimal status;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public McSm() {
	}

	public BigDecimal getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(BigDecimal applicationId) {
		this.applicationId = applicationId;
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public BigDecimal getMcSmsId() {
		return this.mcSmsId;
	}

	public void setMcSmsId(BigDecimal mcSmsId) {
		this.mcSmsId = mcSmsId;
	}

	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public BigDecimal getSmsDebitLogId() {
		return this.smsDebitLogId;
	}

	public void setSmsDebitLogId(BigDecimal smsDebitLogId) {
		this.smsDebitLogId = smsDebitLogId;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}