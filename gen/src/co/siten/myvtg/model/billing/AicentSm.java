package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the AICENT_SMS database table.
 * 
 */
@Entity
@Table(name="AICENT_SMS")
@NamedQuery(name="AicentSm.findAll", query="SELECT a FROM AicentSm a")
public class AicentSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AICENT_MSGID")
	private String aicentMsgid;

	private String cdrtype;

	@Temporal(TemporalType.DATE)
	private Date expirydate;

	private String imsi;

	private String inmsgid;

	private String messagesize;

	private String recipientaddresses;

	private String senderaddresses;

	private String statustext;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Temporal(TemporalType.DATE)
	private Date timemark;

	public AicentSm() {
	}

	public String getAicentMsgid() {
		return this.aicentMsgid;
	}

	public void setAicentMsgid(String aicentMsgid) {
		this.aicentMsgid = aicentMsgid;
	}

	public String getCdrtype() {
		return this.cdrtype;
	}

	public void setCdrtype(String cdrtype) {
		this.cdrtype = cdrtype;
	}

	public Date getExpirydate() {
		return this.expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getInmsgid() {
		return this.inmsgid;
	}

	public void setInmsgid(String inmsgid) {
		this.inmsgid = inmsgid;
	}

	public String getMessagesize() {
		return this.messagesize;
	}

	public void setMessagesize(String messagesize) {
		this.messagesize = messagesize;
	}

	public String getRecipientaddresses() {
		return this.recipientaddresses;
	}

	public void setRecipientaddresses(String recipientaddresses) {
		this.recipientaddresses = recipientaddresses;
	}

	public String getSenderaddresses() {
		return this.senderaddresses;
	}

	public void setSenderaddresses(String senderaddresses) {
		this.senderaddresses = senderaddresses;
	}

	public String getStatustext() {
		return this.statustext;
	}

	public void setStatustext(String statustext) {
		this.statustext = statustext;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Date getTimemark() {
		return this.timemark;
	}

	public void setTimemark(Date timemark) {
		this.timemark = timemark;
	}

}