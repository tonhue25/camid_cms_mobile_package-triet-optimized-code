package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_ADSL_LL database table.
 * 
 */
@Entity
@Table(name="BI_SUB_ADSL_LL")
@NamedQuery(name="BiSubAdslLl.findAll", query="SELECT b FROM BiSubAdslLl b")
public class BiSubAdslLl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="ADDRESS_CODE")
	private String addressCode;

	@Column(name="BOARD_ID")
	private BigDecimal boardId;

	@Column(name="CAB_LEN")
	private String cabLen;

	@Column(name="CABLE_BOX_ID")
	private BigDecimal cableBoxId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

	private String charsic;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	@Column(name="DEPLOY_ADDRESS")
	private String deployAddress;

	@Column(name="DEPLOY_AREA_CODE")
	private String deployAreaCode;

	private BigDecimal deposit;

	private String district;

	@Column(name="DSLAM_ID")
	private BigDecimal dslamId;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FINISH_REASON_ID")
	private BigDecimal finishReasonId;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_CONNECT")
	private Date firstConnect;

	private String home;

	private String ib;

	@Column(name="IP_STATIC")
	private String ipStatic;

	private String isdn;

	@Column(name="KEEP_ALIVE")
	private BigDecimal keepAlive;

	@Column(name="LINE_PHONE")
	private String linePhone;

	@Column(name="LINE_TYPE")
	private String lineType;

	@Column(name="LOCAL_PRICE_PLAN")
	private BigDecimal localPricePlan;

	@Column(name="NICK_DOMAIN")
	private String nickDomain;

	@Column(name="NICK_NAME")
	private String nickName;

	private String password;

	@Column(name="PASSWORD_ATM")
	private String passwordAtm;

	@Column(name="PORT_NO")
	private String portNo;

	private String precinct;

	@Column(name="PRICE_PLAN")
	private BigDecimal pricePlan;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private BigDecimal project;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	private String province;

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

	@Column(name="SLOT_CARD")
	private String slotCard;

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

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

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

	@Column(name="TEL_FAX")
	private String telFax;

	@Column(name="TEL_MOBILE")
	private String telMobile;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_TITLE")
	private String userTitle;

	@Column(name="USER_USING")
	private String userUsing;

	private String vip;

	public BiSubAdslLl() {
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressCode() {
		return this.addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public BigDecimal getBoardId() {
		return this.boardId;
	}

	public void setBoardId(BigDecimal boardId) {
		this.boardId = boardId;
	}

	public String getCabLen() {
		return this.cabLen;
	}

	public void setCabLen(String cabLen) {
		this.cabLen = cabLen;
	}

	public BigDecimal getCableBoxId() {
		return this.cableBoxId;
	}

	public void setCableBoxId(BigDecimal cableBoxId) {
		this.cableBoxId = cableBoxId;
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getCharsic() {
		return this.charsic;
	}

	public void setCharsic(String charsic) {
		this.charsic = charsic;
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

	public String getDeployAddress() {
		return this.deployAddress;
	}

	public void setDeployAddress(String deployAddress) {
		this.deployAddress = deployAddress;
	}

	public String getDeployAreaCode() {
		return this.deployAreaCode;
	}

	public void setDeployAreaCode(String deployAreaCode) {
		this.deployAreaCode = deployAreaCode;
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

	public BigDecimal getDslamId() {
		return this.dslamId;
	}

	public void setDslamId(BigDecimal dslamId) {
		this.dslamId = dslamId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getIb() {
		return this.ib;
	}

	public void setIb(String ib) {
		this.ib = ib;
	}

	public String getIpStatic() {
		return this.ipStatic;
	}

	public void setIpStatic(String ipStatic) {
		this.ipStatic = ipStatic;
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

	public BigDecimal getLocalPricePlan() {
		return this.localPricePlan;
	}

	public void setLocalPricePlan(BigDecimal localPricePlan) {
		this.localPricePlan = localPricePlan;
	}

	public String getNickDomain() {
		return this.nickDomain;
	}

	public void setNickDomain(String nickDomain) {
		this.nickDomain = nickDomain;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getPortNo() {
		return this.portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
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

	public BigDecimal getProject() {
		return this.project;
	}

	public void setProject(BigDecimal project) {
		this.project = project;
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

	public String getSlotCard() {
		return this.slotCard;
	}

	public void setSlotCard(String slotCard) {
		this.slotCard = slotCard;
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

	public String getStreetBlockName() {
		return this.streetBlockName;
	}

	public void setStreetBlockName(String streetBlockName) {
		this.streetBlockName = streetBlockName;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
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

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

	public String getTelMobile() {
		return this.telMobile;
	}

	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
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