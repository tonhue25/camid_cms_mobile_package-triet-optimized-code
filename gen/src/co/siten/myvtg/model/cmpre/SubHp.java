package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SUB_HP database table.
 * 
 */
@Entity
@Table(name="SUB_HP")
@NamedQuery(name="SubHp.findAll", query="SELECT s FROM SubHp s")
public class SubHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUB_ID")
	private long subId;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="ADDRESS_CODE")
	private String addressCode;

	@Column(name="BIRTH_DATE")
	private Timestamp birthDate;

	@Column(name="CHANGE_DATETIME")
	private Timestamp changeDatetime;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	private String district;

	@Column(name="END_DATETIME")
	private Timestamp endDatetime;

	@Column(name="FINISH_REASON_ID")
	private BigDecimal finishReasonId;

	@Column(name="FIRST_EXP_DATE")
	private Timestamp firstExpDate;

	@Column(name="FIRST_SHOP_CODE")
	private String firstShopCode;

	private String gender;

	private String home;

	private String imsi;

	@Column(name="IS_INFO_COMPLETED")
	private BigDecimal isInfoCompleted;

	private String isdn;

	@Column(name="LAST_NUMBER")
	private String lastNumber;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	@Column(name="LOCATION_NAME")
	private String locationName;

	private String notes;

	@Column(name="NUM_RESET_ZONE")
	private BigDecimal numResetZone;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="ORG_PRODUCT_CODE")
	private String orgProductCode;

	private String password;

	private String precinct;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROM_VALID_DATETIME")
	private Timestamp promValidDatetime;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="PROMOTION_ID")
	private BigDecimal promotionId;

	private String province;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SECOND_EXP_DATE")
	private Timestamp secondExpDate;

	private String serial;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STA_DATETIME")
	private Timestamp staDatetime;

	@Column(name="START_MONEY")
	private String startMoney;

	private BigDecimal status;

	private String street;

	@Column(name="STREET_BLOCK")
	private String streetBlock;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="SUB_NAME")
	private String subName;

	private String subcos;

	@Column(name="SUBCOS_HUAWEI")
	private String subcosHuawei;

	@Column(name="SUBCOS_ZTE")
	private String subcosZte;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="VALID_DATETIME")
	private Timestamp validDatetime;

	private String vip;

	public SubHp() {
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

	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public Timestamp getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Timestamp changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public BigDecimal getCustReqId() {
		return this.custReqId;
	}

	public void setCustReqId(BigDecimal custReqId) {
		this.custReqId = custReqId;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Timestamp getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getFinishReasonId() {
		return this.finishReasonId;
	}

	public void setFinishReasonId(BigDecimal finishReasonId) {
		this.finishReasonId = finishReasonId;
	}

	public Timestamp getFirstExpDate() {
		return this.firstExpDate;
	}

	public void setFirstExpDate(Timestamp firstExpDate) {
		this.firstExpDate = firstExpDate;
	}

	public String getFirstShopCode() {
		return this.firstShopCode;
	}

	public void setFirstShopCode(String firstShopCode) {
		this.firstShopCode = firstShopCode;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getLastNumber() {
		return this.lastNumber;
	}

	public void setLastNumber(String lastNumber) {
		this.lastNumber = lastNumber;
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

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getNumResetZone() {
		return this.numResetZone;
	}

	public void setNumResetZone(BigDecimal numResetZone) {
		this.numResetZone = numResetZone;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public String getOrgProductCode() {
		return this.orgProductCode;
	}

	public void setOrgProductCode(String orgProductCode) {
		this.orgProductCode = orgProductCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Timestamp getPromValidDatetime() {
		return this.promValidDatetime;
	}

	public void setPromValidDatetime(Timestamp promValidDatetime) {
		this.promValidDatetime = promValidDatetime;
	}

	public String getPromotionCode() {
		return this.promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public BigDecimal getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(BigDecimal promotionId) {
		this.promotionId = promotionId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public Timestamp getSecondExpDate() {
		return this.secondExpDate;
	}

	public void setSecondExpDate(Timestamp secondExpDate) {
		this.secondExpDate = secondExpDate;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public Timestamp getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Timestamp staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getStartMoney() {
		return this.startMoney;
	}

	public void setStartMoney(String startMoney) {
		this.startMoney = startMoney;
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

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubcos() {
		return this.subcos;
	}

	public void setSubcos(String subcos) {
		this.subcos = subcos;
	}

	public String getSubcosHuawei() {
		return this.subcosHuawei;
	}

	public void setSubcosHuawei(String subcosHuawei) {
		this.subcosHuawei = subcosHuawei;
	}

	public String getSubcosZte() {
		return this.subcosZte;
	}

	public void setSubcosZte(String subcosZte) {
		this.subcosZte = subcosZte;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Timestamp getValidDatetime() {
		return this.validDatetime;
	}

	public void setValidDatetime(Timestamp validDatetime) {
		this.validDatetime = validDatetime;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

}