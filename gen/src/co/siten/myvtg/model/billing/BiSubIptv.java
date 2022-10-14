package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_IPTV database table.
 * 
 */
@Entity
@Table(name="BI_SUB_IPTV")
@NamedQuery(name="BiSubIptv.findAll", query="SELECT b FROM BiSubIptv b")
public class BiSubIptv implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Column(name="ADSL_ACCOUNT")
	private String adslAccount;

	@Column(name="ADSL_SUB_ID")
	private BigDecimal adslSubId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

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

	@Column(name="IP_STATIC")
	private String ipStatic;

	private String isdn;

	@Column(name="KEEP_ALIVE")
	private BigDecimal keepAlive;

	@Temporal(TemporalType.DATE)
	@Column(name="LIMIT_DATE")
	private Date limitDate;

	@Column(name="NICK_DOMAIN")
	private String nickDomain;

	@Column(name="NICK_NAME")
	private String nickName;

	private String password;

	@Column(name="PASSWORD_ATM")
	private String passwordAtm;

	private String precinct;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	private String province;

	@Column(name="\"QUOTA\"")
	private BigDecimal quota;

	@Column(name="REASON_DEPOSIT_ID")
	private BigDecimal reasonDepositId;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SHOP_CODE")
	private String shopCode;

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

	public BiSubIptv() {
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

	public String getAdslAccount() {
		return this.adslAccount;
	}

	public void setAdslAccount(String adslAccount) {
		this.adslAccount = adslAccount;
	}

	public BigDecimal getAdslSubId() {
		return this.adslSubId;
	}

	public void setAdslSubId(BigDecimal adslSubId) {
		this.adslSubId = adslSubId;
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
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

	public Date getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
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

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
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