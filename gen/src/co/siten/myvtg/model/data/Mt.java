package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MT database table.
 * 
 */
@Entity
@NamedQuery(name="Mt.findAll", query="SELECT m FROM Mt m")
public class Mt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MT_ID")
	private long mtId;

	private String channel;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Column(name="MO_HIS_ID")
	private BigDecimal moHisId;

	private String msisdn;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIVE_TIME")
	private Date receiveTime;

	@Column(name="RETRY_NUM")
	private BigDecimal retryNum;

	public Mt() {
	}

	public long getMtId() {
		return this.mtId;
	}

	public void setMtId(long mtId) {
		this.mtId = mtId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public BigDecimal getRetryNum() {
		return this.retryNum;
	}

	public void setRetryNum(BigDecimal retryNum) {
		this.retryNum = retryNum;
	}

}