package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUBSCRIBER_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_SUBSCRIBER_TEMP")
@NamedQuery(name="BiSubscriberTemp.findAll", query="SELECT b FROM BiSubscriberTemp b")
public class BiSubscriberTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUB_ID")
	private long subId;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="ADDRESS_CODE")
	private String addressCode;

	@Column(name="ADSL_ISDN")
	private String adslIsdn;

	@Column(name="ADSL_SUB_ID")
	private BigDecimal adslSubId;

	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	private Date birthDate;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	@Column(name="\"DATA\"")
	private String data;

	@Column(name="DEPLOY_ADDRESS")
	private String deployAddress;

	@Column(name="DEPLOY_ADDRESS_END")
	private String deployAddressEnd;

	@Column(name="DEPLOY_AREA_CODE")
	private String deployAreaCode;

	private BigDecimal deposit;

	private String district;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FINISH_REASON_ID")
	private BigDecimal finishReasonId;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_CONNECT")
	private Date firstConnect;

	private String home;

	private String imsi;

	@Column(name="IS_INFO_COMPLETED")
	private BigDecimal isInfoCompleted;

	private String isdn;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	@Column(name="LOCATION_NAME")
	private String locationName;

	private String nationality;

	private String password;

	@Column(name="PASSWORD_ATM")
	private String passwordAtm;

	@Column(name="PAYMENT_ORDER")
	private BigDecimal paymentOrder;

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

	@Column(name="REG_REASON_ID")
	private BigDecimal regReasonId;

	@Column(name="REG_TYPE")
	private String regType;

	private String serial;

	private String sex;

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

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_TITLE")
	private String userTitle;

	@Column(name="USER_USING")
	private String userUsing;

	private String vip;

	public BiSubscriberTemp() {
	}

	public long getSubId() {
		return this.subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
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

	public String getAdslIsdn() {
		return this.adslIsdn;
	}

	public void setAdslIsdn(String adslIsdn) {
		this.adslIsdn = adslIsdn;
	}

	public BigDecimal getAdslSubId() {
		return this.adslSubId;
	}

	public void setAdslSubId(BigDecimal adslSubId) {
		this.adslSubId = adslSubId;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
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

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
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

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public BigDecimal getIsInfoCompleted() {
		return this.isInfoCompleted;
	}

	public void setIsInfoCompleted(BigDecimal isInfoCompleted) {
		this.isInfoCompleted = isInfoCompleted;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public BigDecimal getPaymentOrder() {
		return this.paymentOrder;
	}

	public void setPaymentOrder(BigDecimal paymentOrder) {
		this.paymentOrder = paymentOrder;
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

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
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