package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_TONGHOP_CP database table.
 * 
 */
@Entity
@Table(name="UD_TONGHOP_CP")
@NamedQuery(name="UdTonghopCp.findAll", query="SELECT u FROM UdTonghopCp u")
public class UdTonghopCp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COMMAND_CODE")
	private String commandCode;

	@Column(name="NUM_SMS_RES")
	private BigDecimal numSmsRes;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIVER_DATETIME")
	private Date receiverDatetime;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private String status;

	@Column(name="STATUS_ORIGINAL")
	private String statusOriginal;

	@Column(name="USER_ID")
	private String userId;

	public UdTonghopCp() {
	}

	public String getCommandCode() {
		return this.commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public BigDecimal getNumSmsRes() {
		return this.numSmsRes;
	}

	public void setNumSmsRes(BigDecimal numSmsRes) {
		this.numSmsRes = numSmsRes;
	}

	public Date getReceiverDatetime() {
		return this.receiverDatetime;
	}

	public void setReceiverDatetime(Date receiverDatetime) {
		this.receiverDatetime = receiverDatetime;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusOriginal() {
		return this.statusOriginal;
	}

	public void setStatusOriginal(String statusOriginal) {
		this.statusOriginal = statusOriginal;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}