package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SUB_MB database table.
 * 
 */
@Entity
@Table(name="SUB_MB")
@NamedQuery(name="SubMb.findAll", query="SELECT s FROM SubMb s")
public class SubMb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUB_ID")
	private long subId;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="BIRTH_DATE")
	private Timestamp birthDate;

	@Column(name="BIRTH_PLACE")
	private String birthPlace;

	@Column(name="CHANGE_DATETIME")
	private Timestamp changeDatetime;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CUST_ID")
	private BigDecimal custId;

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

	private String imsi;

	private String isdn;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_NUMBER")
	private String lastNumber;

	private String nationality;

	private String notes;

	@Column(name="NUM_RESET_ZONE")
	private BigDecimal numResetZone;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="ORG_PRODUCT_CODE")
	private String orgProductCode;

	private String password;

	@Column(name="\"PATH\"")
	private String path;

	private String precinct;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROFILE_NAME")
	private String profileName;

	@Column(name="PROM_VALID_DATETIME")
	private Timestamp promValidDatetime;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

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

	@Column(name="SUB_NAME")
	private String subName;

	@Column(name="SUBCOS_HUAWEI")
	private String subcosHuawei;

	@Column(name="SUBCOS_ZTE")
	private String subcosZte;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="VALID_DATETIME")
	private Timestamp validDatetime;

	private String vip;

	public SubMb() {
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

	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Timestamp getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Timestamp changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
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

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLastNumber() {
		return this.lastNumber;
	}

	public void setLastNumber(String lastNumber) {
		this.lastNumber = lastNumber;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getProfileName() {
		return this.profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
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

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
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