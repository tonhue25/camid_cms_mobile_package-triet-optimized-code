package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the REASON database table.
 * 
 */
@Entity
@NamedQuery(name="Reason.findAll", query="SELECT r FROM Reason r")
public class Reason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

	private String channel;

	private String code;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATETIME")
	private Date createDatetime;

	private String description;

	private String name;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REG_SPECIAL")
	private String regSpecial;

	@Column(name="REG_SPECIAL_TYPE")
	private String regSpecialType;

	@Column(name="REQ_PROFILE")
	private String reqProfile;

	@Column(name="SER_TRANS_ID")
	private String serTransId;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private String type;

	public Reason() {
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getRegSpecial() {
		return this.regSpecial;
	}

	public void setRegSpecial(String regSpecial) {
		this.regSpecial = regSpecial;
	}

	public String getRegSpecialType() {
		return this.regSpecialType;
	}

	public void setRegSpecialType(String regSpecialType) {
		this.regSpecialType = regSpecialType;
	}

	public String getReqProfile() {
		return this.reqProfile;
	}

	public void setReqProfile(String reqProfile) {
		this.reqProfile = reqProfile;
	}

	public String getSerTransId() {
		return this.serTransId;
	}

	public void setSerTransId(String serTransId) {
		this.serTransId = serTransId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}