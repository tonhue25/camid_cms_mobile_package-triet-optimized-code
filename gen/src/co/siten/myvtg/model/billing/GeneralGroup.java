package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GENERAL_GROUP database table.
 * 
 */
@Entity
@Table(name="GENERAL_GROUP")
@NamedQuery(name="GeneralGroup.findAll", query="SELECT g FROM GeneralGroup g")
public class GeneralGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String address;

	@Column(name="BRAND_CODE")
	private String brandCode;

	@Column(name="BUS_PERMIT_NO")
	private String busPermitNo;

	@Column(name="BUSINESS_CENTER_CODE")
	private String businessCenterCode;

	@Column(name="BUSINESS_TYPE")
	private String businessType;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

	@Column(name="CHANNEL_CODE")
	private String channelCode;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CREATED_USER")
	private String createdUser;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="ESTABLISHED_DATETIME")
	private Date establishedDatetime;

	private String gender;

	@Column(name="GROUP_CODE")
	private String groupCode;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	@Column(name="GROUP_NAME")
	private String groupName;

	@Column(name="GROUP_TYPE")
	private String groupType;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATETIME")
	private Date issueDatetime;

	@Column(name="JOIN_MONEY")
	private String joinMoney;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHARGING_DATETIME")
	private Date lastChargingDatetime;

	@Column(name="LEADER_ISDN")
	private String leaderIsdn;

	@Column(name="REPRE_CUST_CONTACT")
	private String repreCustContact;

	@Column(name="REPRE_CUST_ID_NO")
	private String repreCustIdNo;

	@Column(name="REPRE_CUST_NAME")
	private String repreCustName;

	@Column(name="SHOP_CODE")
	private String shopCode;

	private BigDecimal status;

	@Column(name="TEL_FAX")
	private String telFax;

	private String tin;

	@Column(name="USER_TITLE")
	private String userTitle;

	public GeneralGroup() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrandCode() {
		return this.brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBusPermitNo() {
		return this.busPermitNo;
	}

	public void setBusPermitNo(String busPermitNo) {
		this.busPermitNo = busPermitNo;
	}

	public String getBusinessCenterCode() {
		return this.businessCenterCode;
	}

	public void setBusinessCenterCode(String businessCenterCode) {
		this.businessCenterCode = businessCenterCode;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getChannelCode() {
		return this.channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEstablishedDatetime() {
		return this.establishedDatetime;
	}

	public void setEstablishedDatetime(Date establishedDatetime) {
		this.establishedDatetime = establishedDatetime;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Date getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Date issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public String getJoinMoney() {
		return this.joinMoney;
	}

	public void setJoinMoney(String joinMoney) {
		this.joinMoney = joinMoney;
	}

	public Date getLastChargingDatetime() {
		return this.lastChargingDatetime;
	}

	public void setLastChargingDatetime(Date lastChargingDatetime) {
		this.lastChargingDatetime = lastChargingDatetime;
	}

	public String getLeaderIsdn() {
		return this.leaderIsdn;
	}

	public void setLeaderIsdn(String leaderIsdn) {
		this.leaderIsdn = leaderIsdn;
	}

	public String getRepreCustContact() {
		return this.repreCustContact;
	}

	public void setRepreCustContact(String repreCustContact) {
		this.repreCustContact = repreCustContact;
	}

	public String getRepreCustIdNo() {
		return this.repreCustIdNo;
	}

	public void setRepreCustIdNo(String repreCustIdNo) {
		this.repreCustIdNo = repreCustIdNo;
	}

	public String getRepreCustName() {
		return this.repreCustName;
	}

	public void setRepreCustName(String repreCustName) {
		this.repreCustName = repreCustName;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getUserTitle() {
		return this.userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

}