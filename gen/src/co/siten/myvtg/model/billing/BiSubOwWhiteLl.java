package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_OW_WHITE_LL database table.
 * 
 */
@Entity
@Table(name="BI_SUB_OW_WHITE_LL")
@NamedQuery(name="BiSubOwWhiteLl.findAll", query="SELECT b FROM BiSubOwWhiteLl b")
public class BiSubOwWhiteLl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

	@Column(name="CHANNEL_TYPE")
	private String channelType;

	private String contact;

	@Column(name="CONTACT_END")
	private String contactEnd;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	@Temporal(TemporalType.DATE)
	@Column(name="DEPLOY_ACCEPT_DATE")
	private Date deployAcceptDate;

	@Column(name="DEPLOY_ADDRESS")
	private String deployAddress;

	@Column(name="DEPLOY_ADDRESS_END")
	private String deployAddressEnd;

	@Column(name="DEPLOY_AREA_CODE")
	private String deployAreaCode;

	@Column(name="DEPLOY_AREA_CODE_END")
	private String deployAreaCodeEnd;

	private BigDecimal deposit;

	private String district;

	@Column(name="DISTRICT_END")
	private String districtEnd;

	@Column(name="DSLAM_ID")
	private BigDecimal dslamId;

	@Column(name="DSLAM_ID_END")
	private BigDecimal dslamIdEnd;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FINISH_REASON_ID")
	private BigDecimal finishReasonId;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_CONNECT")
	private Date firstConnect;

	private String home;

	@Column(name="HOME_END")
	private String homeEnd;

	@Column(name="IP_STATIC")
	private String ipStatic;

	@Column(name="IP_VIEW")
	private String ipView;

	private String isdn;

	@Column(name="KEEP_ALIVE")
	private BigDecimal keepAlive;

	@Column(name="LINE_PHONE")
	private String linePhone;

	@Column(name="LINE_TYPE")
	private String lineType;

	private String password;

	@Column(name="PASSWORD_ATM")
	private String passwordAtm;

	private String precinct;

	@Column(name="PRECINCT_END")
	private String precinctEnd;

	@Column(name="PRICE_PLAN")
	private BigDecimal pricePlan;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	private String province;

	@Column(name="PROVINCE_END")
	private String provinceEnd;

	@Column(name="\"QUOTA\"")
	private BigDecimal quota;

	@Column(name="REASON_DEPOSIT_ID")
	private BigDecimal reasonDepositId;

	@Column(name="REG_REASON_ID")
	private BigDecimal regReasonId;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SHOP_CODE")
	private String shopCode;

	private BigDecimal speed;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	private BigDecimal status;

	private String street;

	@Column(name="STREET_BLOCK")
	private String streetBlock;

	@Column(name="STREET_BLOCK_END")
	private String streetBlockEnd;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_BLOCK_NAME_END")
	private String streetBlockNameEnd;

	@Column(name="STREET_END")
	private String streetEnd;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="STREET_NAME_END")
	private String streetNameEnd;

	@Temporal(TemporalType.DATE)
	@Column(name="SUB_BIRTH")
	private Date subBirth;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="TASK_ID")
	private BigDecimal taskId;

	@Column(name="TEAM_ID")
	private BigDecimal teamId;

	@Column(name="TEAM_ID_END")
	private BigDecimal teamIdEnd;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_TITLE")
	private String userTitle;

	@Column(name="USER_USING")
	private String userUsing;

	private String vip;

	public BiSubOwWhiteLl() {
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getChannelType() {
		return this.channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactEnd() {
		return this.contactEnd;
	}

	public void setContactEnd(String contactEnd) {
		this.contactEnd = contactEnd;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCustReqId() {
		return this.custReqId;
	}

	public void setCustReqId(BigDecimal custReqId) {
		this.custReqId = custReqId;
	}

	public Date getDeployAcceptDate() {
		return this.deployAcceptDate;
	}

	public void setDeployAcceptDate(Date deployAcceptDate) {
		this.deployAcceptDate = deployAcceptDate;
	}

	public String getDeployAddress() {
		return this.deployAddress;
	}

	public void setDeployAddress(String deployAddress) {
		this.deployAddress = deployAddress;
	}

	public String getDeployAddressEnd() {
		return this.deployAddressEnd;
	}

	public void setDeployAddressEnd(String deployAddressEnd) {
		this.deployAddressEnd = deployAddressEnd;
	}

	public String getDeployAreaCode() {
		return this.deployAreaCode;
	}

	public void setDeployAreaCode(String deployAreaCode) {
		this.deployAreaCode = deployAreaCode;
	}

	public String getDeployAreaCodeEnd() {
		return this.deployAreaCodeEnd;
	}

	public void setDeployAreaCodeEnd(String deployAreaCodeEnd) {
		this.deployAreaCodeEnd = deployAreaCodeEnd;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrictEnd() {
		return this.districtEnd;
	}

	public void setDistrictEnd(String districtEnd) {
		this.districtEnd = districtEnd;
	}

	public BigDecimal getDslamId() {
		return this.dslamId;
	}

	public void setDslamId(BigDecimal dslamId) {
		this.dslamId = dslamId;
	}

	public BigDecimal getDslamIdEnd() {
		return this.dslamIdEnd;
	}

	public void setDslamIdEnd(BigDecimal dslamIdEnd) {
		this.dslamIdEnd = dslamIdEnd;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getFinishReasonId() {
		return this.finishReasonId;
	}

	public void setFinishReasonId(BigDecimal finishReasonId) {
		this.finishReasonId = finishReasonId;
	}

	public Date getFirstConnect() {
		return this.firstConnect;
	}

	public void setFirstConnect(Date firstConnect) {
		this.firstConnect = firstConnect;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getHomeEnd() {
		return this.homeEnd;
	}

	public void setHomeEnd(String homeEnd) {
		this.homeEnd = homeEnd;
	}

	public String getIpStatic() {
		return this.ipStatic;
	}

	public void setIpStatic(String ipStatic) {
		this.ipStatic = ipStatic;
	}

	public String getIpView() {
		return this.ipView;
	}

	public void setIpView(String ipView) {
		this.ipView = ipView;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getKeepAlive() {
		return this.keepAlive;
	}

	public void setKeepAlive(BigDecimal keepAlive) {
		this.keepAlive = keepAlive;
	}

	public String getLinePhone() {
		return this.linePhone;
	}

	public void setLinePhone(String linePhone) {
		this.linePhone = linePhone;
	}

	public String getLineType() {
		return this.lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAtm() {
		return this.passwordAtm;
	}

	public void setPasswordAtm(String passwordAtm) {
		this.passwordAtm = passwordAtm;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	public String getPrecinctEnd() {
		return this.precinctEnd;
	}

	public void setPrecinctEnd(String precinctEnd) {
		this.precinctEnd = precinctEnd;
	}

	public BigDecimal getPricePlan() {
		return this.pricePlan;
	}

	public void setPricePlan(BigDecimal pricePlan) {
		this.pricePlan = pricePlan;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPromotionCode() {
		return this.promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceEnd() {
		return this.provinceEnd;
	}

	public void setProvinceEnd(String provinceEnd) {
		this.provinceEnd = provinceEnd;
	}

	public BigDecimal getQuota() {
		return this.quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public BigDecimal getReasonDepositId() {
		return this.reasonDepositId;
	}

	public void setReasonDepositId(BigDecimal reasonDepositId) {
		this.reasonDepositId = reasonDepositId;
	}

	public BigDecimal getRegReasonId() {
		return this.regReasonId;
	}

	public void setRegReasonId(BigDecimal regReasonId) {
		this.regReasonId = regReasonId;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getSpeed() {
		return this.speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetBlock() {
		return this.streetBlock;
	}

	public void setStreetBlock(String streetBlock) {
		this.streetBlock = streetBlock;
	}

	public String getStreetBlockEnd() {
		return this.streetBlockEnd;
	}

	public void setStreetBlockEnd(String streetBlockEnd) {
		this.streetBlockEnd = streetBlockEnd;
	}

	public String getStreetBlockName() {
		return this.streetBlockName;
	}

	public void setStreetBlockName(String streetBlockName) {
		this.streetBlockName = streetBlockName;
	}

	public String getStreetBlockNameEnd() {
		return this.streetBlockNameEnd;
	}

	public void setStreetBlockNameEnd(String streetBlockNameEnd) {
		this.streetBlockNameEnd = streetBlockNameEnd;
	}

	public String getStreetEnd() {
		return this.streetEnd;
	}

	public void setStreetEnd(String streetEnd) {
		this.streetEnd = streetEnd;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNameEnd() {
		return this.streetNameEnd;
	}

	public void setStreetNameEnd(String streetNameEnd) {
		this.streetNameEnd = streetNameEnd;
	}

	public Date getSubBirth() {
		return this.subBirth;
	}

	public void setSubBirth(Date subBirth) {
		this.subBirth = subBirth;
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

	public BigDecimal getTaskId() {
		return this.taskId;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	public BigDecimal getTeamId() {
		return this.teamId;
	}

	public void setTeamId(BigDecimal teamId) {
		this.teamId = teamId;
	}

	public BigDecimal getTeamIdEnd() {
		return this.teamIdEnd;
	}

	public void setTeamIdEnd(BigDecimal teamIdEnd) {
		this.teamIdEnd = teamIdEnd;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getUserTitle() {
		return this.userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserUsing() {
		return this.userUsing;
	}

	public void setUserUsing(String userUsing) {
		this.userUsing = userUsing;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

}