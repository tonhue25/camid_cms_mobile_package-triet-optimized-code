package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SMS_ALARM database table.
 * 
 */
@Entity
@Table(name="SMS_ALARM")
@NamedQuery(name="SmsAlarm.findAll", query="SELECT s FROM SmsAlarm s")
public class SmsAlarm implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String isdn;

	private BigDecimal retry;

	@Temporal(TemporalType.DATE)
	@Column(name="SEND_DATE")
	private Date sendDate;

	private String status;

	public SmsAlarm() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getRetry() {
		return this.retry;
	}

	public void setRetry(BigDecimal retry) {
		this.retry = retry;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}