package co.siten.myvtg.model.sm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the SHOP database table.
 * 
 */
@Entity
@Table(name="SHOP")
@NamedQuery(name="Shop.findAll", query="SELECT s FROM Shop s")
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SHOP_ID")
	private long shopId;

	private String account;

	private String address;

	@Column(name="AGENT_TYPE")
	private BigDecimal agentType;

	@Column(name="BANK_NAME")
	private String bankName;
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="BOARD_STATE")
	private BigDecimal boardState;

	@Column(name="CENTER_CODE")
	private String centerCode;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="CHECK_VAT")
	private BigDecimal checkVat;

	private String company;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_TITLE")
	private String contactTitle;
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CUS_TYPE")
	private String cusType;

	private String description;

	@Column(name="DISCOUNT_POLICY")
	private String discountPolicy;

	private String district;

	private String email;

	private String fax;

	private String home;
	@Temporal(TemporalType.DATE)
	@Column(name="ID_ISSUE_DATE")
	private Date idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="LAST_UPDATE_IP_ADDRESS")
	private String lastUpdateIpAddress;

	@Column(name="LAST_UPDATE_KEY")
	private String lastUpdateKey;
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_TIME")
	private Date lastUpdateTime;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	private String name;

	@Column(name="OLD_SHOP_CODE")
	private String oldShopCode;

	@Column(name="PAR_SHOP_CODE")
	private String parShopCode;

	@Column(name="PARENT_SHOP_ID")
	private BigDecimal parentShopId;

	@Column(name="PAY_COMM")
	private String payComm;

	private String precinct;

	@Column(name="PRICE_POLICY")
	private String pricePolicy;

	@Column(name="PROFILE_STATE")
	private BigDecimal profileState;

	private String province;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_SHOP_CODE")
	private String provinceShopCode;
	@Temporal(TemporalType.DATE)
	@Column(name="REGISTRY_DATE")
	private Date registryDate;

	private String shop;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_PATH")
	private String shopPath;

	@Column(name="SHOP_TYPE")
	private String shopType;

	private BigDecimal status;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="SURFACE_AREA")
	private String surfaceArea;

	@Column(name="SYNC_STATUS")
	private BigDecimal syncStatus;

	private String tel;

	@Column(name="TEL_NUMBER")
	private String telNumber;

	private String tin;

	@Column(name="TRADE_NAME")
	private String tradeName;

	@Column(name="USEFUL_WIDTH")
	private String usefulWidth;

	@Column(name="USER_SESSION_ID")
	private String userSessionId;

	public Shop() {
	}

	public long getShopId() {
		return this.shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAgentType() {
		return this.agentType;
	}

	public void setAgentType(BigDecimal agentType) {
		this.agentType = agentType;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public BigDecimal getBoardState() {
		return boardState;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public BigDecimal getChannelTypeId() {
		return channelTypeId;
	}

	public BigDecimal getCheckVat() {
		return checkVat;
	}

	public String getCompany() {
		return company;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getCusType() {
		return cusType;
	}

	public String getDescription() {
		return description;
	}

	public String getDiscountPolicy() {
		return discountPolicy;
	}

	public String getDistrict() {
		return district;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getHome() {
		return home;
	}

	public Date getIdIssueDate() {
		return idIssueDate;
	}

	public String getIdIssuePlace() {
		return idIssuePlace;
	}

	public String getIdNo() {
		return idNo;
	}

	public String getLastUpdateIpAddress() {
		return lastUpdateIpAddress;
	}

	public String getLastUpdateKey() {
		return lastUpdateKey;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public String getName() {
		return name;
	}

	public String getOldShopCode() {
		return oldShopCode;
	}

	public String getParShopCode() {
		return parShopCode;
	}

	public BigDecimal getParentShopId() {
		return parentShopId;
	}

	public String getPayComm() {
		return payComm;
	}

	public String getPrecinct() {
		return precinct;
	}

	public String getPricePolicy() {
		return pricePolicy;
	}

	public BigDecimal getProfileState() {
		return profileState;
	}

	public String getProvince() {
		return province;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public String getProvinceShopCode() {
		return provinceShopCode;
	}

	public Date getRegistryDate() {
		return registryDate;
	}

	public String getShop() {
		return shop;
	}

	public String getShopCode() {
		return shopCode;
	}

	public String getShopPath() {
		return shopPath;
	}

	public String getShopType() {
		return shopType;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public String getStreetBlockName() {
		return streetBlockName;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getSurfaceArea() {
		return surfaceArea;
	}

	public BigDecimal getSyncStatus() {
		return syncStatus;
	}

	public String getTel() {
		return tel;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public String getTin() {
		return tin;
	}

	public String getTradeName() {
		return tradeName;
	}

	public String getUsefulWidth() {
		return usefulWidth;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setBoardState(BigDecimal boardState) {
		this.boardState = boardState;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public void setCheckVat(BigDecimal checkVat) {
		this.checkVat = checkVat;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiscountPolicy(String discountPolicy) {
		this.discountPolicy = discountPolicy;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public void setIdIssueDate(Date idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public void setIdIssuePlace(String idIssuePlace) {
		this.idIssuePlace = idIssuePlace;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public void setLastUpdateIpAddress(String lastUpdateIpAddress) {
		this.lastUpdateIpAddress = lastUpdateIpAddress;
	}

	public void setLastUpdateKey(String lastUpdateKey) {
		this.lastUpdateKey = lastUpdateKey;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOldShopCode(String oldShopCode) {
		this.oldShopCode = oldShopCode;
	}

	public void setParShopCode(String parShopCode) {
		this.parShopCode = parShopCode;
	}

	public void setParentShopId(BigDecimal parentShopId) {
		this.parentShopId = parentShopId;
	}

	public void setPayComm(String payComm) {
		this.payComm = payComm;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
	}

	public void setProfileState(BigDecimal profileState) {
		this.profileState = profileState;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public void setProvinceShopCode(String provinceShopCode) {
		this.provinceShopCode = provinceShopCode;
	}

	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public void setShopPath(String shopPath) {
		this.shopPath = shopPath;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public void setStreetBlockName(String streetBlockName) {
		this.streetBlockName = streetBlockName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setSurfaceArea(String surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public void setSyncStatus(BigDecimal syncStatus) {
		this.syncStatus = syncStatus;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public void setUsefulWidth(String usefulWidth) {
		this.usefulWidth = usefulWidth;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}


}