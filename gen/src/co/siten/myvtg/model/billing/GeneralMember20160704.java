package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GENERAL_MEMBER_20160704 database table.
 * 
 */
@Entity
@Table(name="GENERAL_MEMBER_20160704")
@NamedQuery(name="GeneralMember20160704.findAll", query="SELECT g FROM GeneralMember20160704 g")
public class GeneralMember20160704 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MEMBER_ID")
	private long memberId;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_DATETIME")
	private Date expireDatetime;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	@Column(name="IS_LEADER")
	private BigDecimal isLeader;

	private String isdn;

	@Column(name="JOIN_MONEY")
	private BigDecimal joinMoney;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHARGING_DATETIME")
	private Date lastChargingDatetime;

	private String policy;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATETIME")
	private Date startDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	public GeneralMember20160704() {
	}

	public long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public Date getExpireDatetime() {
		return this.expireDatetime;
	}

	public void setExpireDatetime(Date expireDatetime) {
		this.expireDatetime = expireDatetime;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getIsLeader() {
		return this.isLeader;
	}

	public void setIsLeader(BigDecimal isLeader) {
		this.isLeader = isLeader;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getJoinMoney() {
		return this.joinMoney;
	}

	public void setJoinMoney(BigDecimal joinMoney) {
		this.joinMoney = joinMoney;
	}

	public Date getLastChargingDatetime() {
		return this.lastChargingDatetime;
	}

	public void setLastChargingDatetime(Date lastChargingDatetime) {
		this.lastChargingDatetime = lastChargingDatetime;
	}

	public String getPolicy() {
		return this.policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Date getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
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

}