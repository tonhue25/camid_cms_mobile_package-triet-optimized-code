package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MT_HIS database table.
 * 
 */
@Entity
@Table(name="MT_HIS")
@NamedQuery(name="MtHi.findAll", query="SELECT m FROM MtHi m")
public class MtHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="APP_ID")
	private String appId;

	@Column(name="ISDN_TAIL")
	private String isdnTail;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Column(name="MO_HIS_ID")
	private BigDecimal moHisId;

	private String msisdn;

	@Column(name="MT_HIS_ID")
	private BigDecimal mtHisId;

	@Column(name="RETRY_SENT_COUNT")
	private BigDecimal retrySentCount;

	@Temporal(TemporalType.DATE)
	@Column(name="SENT_TIME")
	private Date sentTime;

	private BigDecimal status;

	public MtHi() {
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIsdnTail() {
		return this.isdnTail;
	}

	public void setIsdnTail(String isdnTail) {
		this.isdnTail = isdnTail;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getMoHisId() {
		return this.moHisId;
	}

	public void setMoHisId(BigDecimal moHisId) {
		this.moHisId = moHisId;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public BigDecimal getMtHisId() {
		return this.mtHisId;
	}

	public void setMtHisId(BigDecimal mtHisId) {
		this.mtHisId = mtHisId;
	}

	public BigDecimal getRetrySentCount() {
		return this.retrySentCount;
	}

	public void setRetrySentCount(BigDecimal retrySentCount) {
		this.retrySentCount = retrySentCount;
	}

	public Date getSentTime() {
		return this.sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}