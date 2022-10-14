package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GIFT database table.
 * 
 */
@Entity
@NamedQuery(name="Gift.findAll", query="SELECT g FROM Gift g")
public class Gift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GIFT_ID")
	private long giftId;

	private String code;

	private BigDecimal cost;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="FROM_DATE")
	private Date fromDate;

	@Column(name="GIFT_GROUP_ID")
	private BigDecimal giftGroupId;

	@Column(name="GIFT_TYPE")
	private String giftType;

	private BigDecimal mark;

	@Column(name="MARK_TYPE_ID")
	private BigDecimal markTypeId;

	private String name;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="TO_DATE")
	private Date toDate;

	@Column(name="USER_CREATE")
	private String userCreate;

	public Gift() {
	}

	public long getGiftId() {
		return this.giftId;
	}

	public void setGiftId(long giftId) {
		this.giftId = giftId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public BigDecimal getGiftGroupId() {
		return this.giftGroupId;
	}

	public void setGiftGroupId(BigDecimal giftGroupId) {
		this.giftGroupId = giftGroupId;
	}

	public String getGiftType() {
		return this.giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	public BigDecimal getMark() {
		return this.mark;
	}

	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}

	public BigDecimal getMarkTypeId() {
		return this.markTypeId;
	}

	public void setMarkTypeId(BigDecimal markTypeId) {
		this.markTypeId = markTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}