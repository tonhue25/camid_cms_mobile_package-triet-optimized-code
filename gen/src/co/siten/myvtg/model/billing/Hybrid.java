package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the HYBRID database table.
 * 
 */
@Entity
@NamedQuery(name="Hybrid.findAll", query="SELECT h FROM Hybrid h")
public class Hybrid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="AUDIT_ID")
	private BigDecimal auditId;

	private BigDecimal charge;

	private BigDecimal consumption;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	@Column(name="IS_LEADER")
	private BigDecimal isLeader;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	@Column(name="JOIN_MONEY")
	private BigDecimal joinMoney;

	@Column(name="MEMBER_ID")
	private BigDecimal memberId;

	private String policy;

	private String province;

	private BigDecimal rank;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public Hybrid() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAuditId() {
		return this.auditId;
	}

	public void setAuditId(BigDecimal auditId) {
		this.auditId = auditId;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getConsumption() {
		return this.consumption;
	}

	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getJoinMoney() {
		return this.joinMoney;
	}

	public void setJoinMoney(BigDecimal joinMoney) {
		this.joinMoney = joinMoney;
	}

	public BigDecimal getMemberId() {
		return this.memberId;
	}

	public void setMemberId(BigDecimal memberId) {
		this.memberId = memberId;
	}

	public String getPolicy() {
		return this.policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public BigDecimal getRank() {
		return this.rank;
	}

	public void setRank(BigDecimal rank) {
		this.rank = rank;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}