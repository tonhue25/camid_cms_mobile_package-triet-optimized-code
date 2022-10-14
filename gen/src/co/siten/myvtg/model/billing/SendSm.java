package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SEND_SMS database table.
 * 
 */
@Entity
@Table(name="SEND_SMS")
@NamedQuery(name="SendSm.findAll", query="SELECT s FROM SendSm s")
public class SendSm implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String isdn;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Temporal(TemporalType.DATE)
	@Column(name="SEND_DATE")
	private Date sendDate;

	@Column(name="SEND_SMS_ID")
	private BigDecimal sendSmsId;

	private BigDecimal status;

	public SendSm() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public BigDecimal getSendSmsId() {
		return this.sendSmsId;
	}

	public void setSendSmsId(BigDecimal sendSmsId) {
		this.sendSmsId = sendSmsId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}