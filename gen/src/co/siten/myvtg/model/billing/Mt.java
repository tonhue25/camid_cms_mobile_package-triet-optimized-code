package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MT database table.
 * 
 */
@Entity
@NamedQuery(name="Mt.findAll", query="SELECT m FROM Mt m")
public class Mt implements Serializable {
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

	@Column(name="MT_ID")
	private BigDecimal mtId;

	@Column(name="RETRY_SENT_COUNT")
	private BigDecimal retrySentCount;

	private BigDecimal status;

	public Mt() {
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

	public BigDecimal getMtId() {
		return this.mtId;
	}

	public void setMtId(BigDecimal mtId) {
		this.mtId = mtId;
	}

	public BigDecimal getRetrySentCount() {
		return this.retrySentCount;
	}

	public void setRetrySentCount(BigDecimal retrySentCount) {
		this.retrySentCount = retrySentCount;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}