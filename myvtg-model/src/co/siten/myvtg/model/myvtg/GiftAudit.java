package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GIFT_AUDIT database table.
 * 
 */
@Entity
@Table(name="GIFT_AUDIT")
@NamedQuery(name="GiftAudit.findAll", query="SELECT g FROM GiftAudit g")
public class GiftAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "GIFT_AUDIT_SEQ", allocationSize = 1)
	private long id;

	@Column(name="CLASS_ID")
	private String classId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_DATE")
	private Date effectDate;

	@Column(name="EVENT_ID")
	private BigDecimal eventId;

	@Column(name="GIFT_ID")
	private Long giftId;

	@Column(name="GIFT_TYPE")
	private BigDecimal giftType;

	@Column(name="MB_ID")
	private BigDecimal mbId;

	private String note;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REASON_NOT_GIFT")
	private BigDecimal reasonNotGift;

	@Column(name="SCR_ID")
	private BigDecimal scrId;

	@Column(name="STAFF_PRESENT")
	private String staffPresent;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="USER_CREATE")
	private String userCreate;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	public GiftAudit() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public Long getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	public BigDecimal getGiftType() {
		return this.giftType;
	}

	public void setGiftType(BigDecimal giftType) {
		this.giftType = giftType;
	}

	public BigDecimal getMbId() {
		return this.mbId;
	}

	public void setMbId(BigDecimal mbId) {
		this.mbId = mbId;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public BigDecimal getReasonNotGift() {
		return this.reasonNotGift;
	}

	public void setReasonNotGift(BigDecimal reasonNotGift) {
		this.reasonNotGift = reasonNotGift;
	}

	public BigDecimal getScrId() {
		return this.scrId;
	}

	public void setScrId(BigDecimal scrId) {
		this.scrId = scrId;
	}

	public String getStaffPresent() {
		return this.staffPresent;
	}

	public void setStaffPresent(String staffPresent) {
		this.staffPresent = staffPresent;
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

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

}