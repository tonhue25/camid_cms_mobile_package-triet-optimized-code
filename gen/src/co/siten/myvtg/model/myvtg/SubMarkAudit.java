package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_MARK_AUDIT database table.
 * 
 */
@Entity
@Table(name="SUB_MARK_AUDIT")
@NamedQuery(name="SubMarkAudit.findAll", query="SELECT s FROM SubMarkAudit s")
public class SubMarkAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="APPLIED_EXCHANGE")
	private BigDecimal appliedExchange;

	@Column(name="APPLIED_RATE")
	private BigDecimal appliedRate;

	@Temporal(TemporalType.DATE)
	@Column(name="CANCEL_DATE")
	private Date cancelDate;

	@Column(name="CANCEL_EXCHANGE")
	private BigDecimal cancelExchange;

	@Column(name="CANCEL_RATE")
	private BigDecimal cancelRate;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="MARK_EXCHANGE")
	private BigDecimal markExchange;

	@Column(name="MARK_RATE")
	private BigDecimal markRate;

	@Column(name="MARK_TYPE_ID")
	private BigDecimal markTypeId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"MONTH\"")
	private Date month;

	private String note;

	@Column(name="ORG_EXCHANGE")
	private BigDecimal orgExchange;

	@Column(name="ORG_RATE")
	private BigDecimal orgRate;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="USER_CREATE")
	private String userCreate;

	public SubMarkAudit() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAppliedExchange() {
		return this.appliedExchange;
	}

	public void setAppliedExchange(BigDecimal appliedExchange) {
		this.appliedExchange = appliedExchange;
	}

	public BigDecimal getAppliedRate() {
		return this.appliedRate;
	}

	public void setAppliedRate(BigDecimal appliedRate) {
		this.appliedRate = appliedRate;
	}

	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public BigDecimal getCancelExchange() {
		return this.cancelExchange;
	}

	public void setCancelExchange(BigDecimal cancelExchange) {
		this.cancelExchange = cancelExchange;
	}

	public BigDecimal getCancelRate() {
		return this.cancelRate;
	}

	public void setCancelRate(BigDecimal cancelRate) {
		this.cancelRate = cancelRate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getMarkExchange() {
		return this.markExchange;
	}

	public void setMarkExchange(BigDecimal markExchange) {
		this.markExchange = markExchange;
	}

	public BigDecimal getMarkRate() {
		return this.markRate;
	}

	public void setMarkRate(BigDecimal markRate) {
		this.markRate = markRate;
	}

	public BigDecimal getMarkTypeId() {
		return this.markTypeId;
	}

	public void setMarkTypeId(BigDecimal markTypeId) {
		this.markTypeId = markTypeId;
	}

	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getOrgExchange() {
		return this.orgExchange;
	}

	public void setOrgExchange(BigDecimal orgExchange) {
		this.orgExchange = orgExchange;
	}

	public BigDecimal getOrgRate() {
		return this.orgRate;
	}

	public void setOrgRate(BigDecimal orgRate) {
		this.orgRate = orgRate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}