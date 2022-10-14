package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_BCCS_CALL_ADSL database table.
 * 
 */
@Entity
@Table(name="V_BCCS_CALL_ADSL")
@NamedQuery(name="VBccsCallAdsl.findAll", query="SELECT v FROM VBccsCallAdsl v")
public class VBccsCallAdsl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_STATION_ID")
	private String callingStationId;

	@Temporal(TemporalType.DATE)
	@Column(name="MAX_DATE")
	private Date maxDate;

	@Column(name="MAX_DOWNLOAD")
	private BigDecimal maxDownload;

	@Column(name="MAX_DURATION")
	private BigDecimal maxDuration;

	@Column(name="MAX_UPLOAD")
	private BigDecimal maxUpload;

	@Temporal(TemporalType.DATE)
	@Column(name="MIN_DATE")
	private Date minDate;

	@Column(name="MIN_DOWNLOAD")
	private BigDecimal minDownload;

	@Column(name="MIN_DURATION")
	private BigDecimal minDuration;

	@Column(name="MIN_UPLOAD")
	private BigDecimal minUpload;

	private String port;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public VBccsCallAdsl() {
	}

	public String getCallingStationId() {
		return this.callingStationId;
	}

	public void setCallingStationId(String callingStationId) {
		this.callingStationId = callingStationId;
	}

	public Date getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public BigDecimal getMaxDownload() {
		return this.maxDownload;
	}

	public void setMaxDownload(BigDecimal maxDownload) {
		this.maxDownload = maxDownload;
	}

	public BigDecimal getMaxDuration() {
		return this.maxDuration;
	}

	public void setMaxDuration(BigDecimal maxDuration) {
		this.maxDuration = maxDuration;
	}

	public BigDecimal getMaxUpload() {
		return this.maxUpload;
	}

	public void setMaxUpload(BigDecimal maxUpload) {
		this.maxUpload = maxUpload;
	}

	public Date getMinDate() {
		return this.minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public BigDecimal getMinDownload() {
		return this.minDownload;
	}

	public void setMinDownload(BigDecimal minDownload) {
		this.minDownload = minDownload;
	}

	public BigDecimal getMinDuration() {
		return this.minDuration;
	}

	public void setMinDuration(BigDecimal minDuration) {
		this.minDuration = minDuration;
	}

	public BigDecimal getMinUpload() {
		return this.minUpload;
	}

	public void setMinUpload(BigDecimal minUpload) {
		this.minUpload = minUpload;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}