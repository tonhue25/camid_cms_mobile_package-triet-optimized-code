package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HLR_STATUS_COMMAND database table.
 * 
 */
@Entity
@Table(name="HLR_STATUS_COMMAND")
@NamedQuery(name="HlrStatusCommand.findAll", query="SELECT h FROM HlrStatusCommand h")
public class HlrStatusCommand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"COMMAND\"")
	private String command;

	@Column(name="COMMAND_STATUS")
	private BigDecimal commandStatus;

	@Column(name="DATE_TIME")
	private Object dateTime;

	@Column(name="DATE_TIME_SEND")
	private Object dateTimeSend;

	private String error;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	private BigDecimal id;

	@Column(name="ID_IN")
	private String idIn;

	private String imsi;

	private String isdn;

	@Column(name="NEW_STATUS")
	private BigDecimal newStatus;

	@Column(name="OLD_STATUS")
	private BigDecimal oldStatus;

	@Column(name="PHONE_TYPE")
	private BigDecimal phoneType;

	@Column(name="SEND_TIME")
	private BigDecimal sendTime;

	@Column(name="UPDATE_CM_STATUS")
	private BigDecimal updateCmStatus;

	public HlrStatusCommand() {
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public BigDecimal getCommandStatus() {
		return this.commandStatus;
	}

	public void setCommandStatus(BigDecimal commandStatus) {
		this.commandStatus = commandStatus;
	}

	public Object getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Object dateTime) {
		this.dateTime = dateTime;
	}

	public Object getDateTimeSend() {
		return this.dateTimeSend;
	}

	public void setDateTimeSend(Object dateTimeSend) {
		this.dateTimeSend = dateTimeSend;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIdIn() {
		return this.idIn;
	}

	public void setIdIn(String idIn) {
		this.idIn = idIn;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNewStatus() {
		return this.newStatus;
	}

	public void setNewStatus(BigDecimal newStatus) {
		this.newStatus = newStatus;
	}

	public BigDecimal getOldStatus() {
		return this.oldStatus;
	}

	public void setOldStatus(BigDecimal oldStatus) {
		this.oldStatus = oldStatus;
	}

	public BigDecimal getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(BigDecimal phoneType) {
		this.phoneType = phoneType;
	}

	public BigDecimal getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(BigDecimal sendTime) {
		this.sendTime = sendTime;
	}

	public BigDecimal getUpdateCmStatus() {
		return this.updateCmStatus;
	}

	public void setUpdateCmStatus(BigDecimal updateCmStatus) {
		this.updateCmStatus = updateCmStatus;
	}

}