package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MB_SUBSCRIBER database table.
 * 
 */
@Entity
@Table(name="MB_SUBSCRIBER")
@NamedQuery(name="MbSubscriber.findAll", query="SELECT m FROM MbSubscriber m")
public class MbSubscriber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="BIRTH_DATE")
	private Object birthDate;

	@Column(name="CHANGE_DATETIME")
	private Object changeDatetime;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	private String district;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="FINISH_REASON_ID")
	private BigDecimal finishReasonId;

	@Column(name="FIRST_EXP_DATE")
	private Object firstExpDate;

	@Column(name="FIRST_SHOP_CODE")
	private String firstShopCode;

	private String gender;

	private String imsi;

	private String isdn;

	@Column(name="LAST_NUMBER")
	private String lastNumber;

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
	private Object promValidDatetime;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	private String province;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SECOND_EXP_DATE")
	private Object secondExpDate;

	private String serial;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="START_MONEY")
	private String startMoney;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_NAME")
	private String subName;

	@Column(name="SUBCOS_HUAWEI")
	private String subcosHuawei;

	@Column(name="SUBCOS_ZTE")
	private String subcosZte;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="VALID_DATETIME")
	private Object validDatetime;

	private String vip;

	public MbSubscriber() {
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

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public Object getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Object changeDatetime) {
		this.changeDatetime = changeDatetime;
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

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getFinishReasonId() {
		return this.finishReasonId;
	}

	public void setFinishReasonId(BigDecimal finishReasonId) {
		this.finishReasonId = finishReasonId;
	}

	public Object getFirstExpDate() {
		return this.firstExpDate;
	}

	public void setFirstExpDate(Object firstExpDate) {
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

	public String getLastNumber() {
		return this.lastNumber;
	}

	public void setLastNumber(String lastNumber) {
		this.lastNumber = lastNumber;
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

	public Object getPromValidDatetime() {
		return this.promValidDatetime;
	}

	public void setPromValidDatetime(Object promValidDatetime) {
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

	public Object getSecondExpDate() {
		return this.secondExpDate;
	}

	public void setSecondExpDate(Object secondExpDate) {
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

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
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

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
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

	public Object getValidDatetime() {
		return this.validDatetime;
	}

	public void setValidDatetime(Object validDatetime) {
		this.validDatetime = validDatetime;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

}