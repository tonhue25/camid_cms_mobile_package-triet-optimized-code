package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the VIEW_CC_SUB_CUST_MB database table.
 * 
 */
@Entity
@Table(name="VIEW_CC_SUB_CUST_MB")
@NamedQuery(name="ViewCcSubCustMb.findAll", query="SELECT v FROM ViewCcSubCustMb v")
public class ViewCcSubCustMb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS_STRING")
	private String actStatusString;

	@Column(name="BIRTH_PLACE")
	private String birthPlace;

	@Column(name="BUS_PERMIT")
	private String busPermit;

	@Column(name="CRE_DATE")
	private String creDate;

	@Column(name="CUS_VIP_CODE")
	private String cusVipCode;

	@Column(name="CUST_ADDRESS")
	private String custAddress;

	@Temporal(TemporalType.DATE)
	@Column(name="CUST_BIRTH")
	private Date custBirth;

	@Column(name="CUST_BIRTHDAY")
	private String custBirthday;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_ID_NO")
	private String custIdNo;

	@Column(name="CUST_ID_TYPE")
	private String custIdType;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="CUST_NATIONALITY")
	private String custNationality;

	@Column(name="CUST_POP_NO")
	private String custPopNo;

	@Column(name="CUST_SEX")
	private String custSex;

	@Column(name="CUST_TYPE")
	private String custType;

	@Column(name="CUST_VIP")
	private String custVip;

	@Column(name="HLR_ACT_STATUS")
	private String hlrActStatus;

	@Column(name="HLR_IMSI")
	private String hlrImsi;

	@Column(name="HLR_ISDN")
	private String hlrIsdn;

	@Column(name="HLR_SERIAL")
	private String hlrSerial;

	@Column(name="HLR_STATUS")
	private BigDecimal hlrStatus;

	@Column(name="HLR_STATUS_NAME")
	private BigDecimal hlrStatusName;

	private String hour1;

	@Column(name="ID_EXPIRE_DATE")
	private String idExpireDate;

	@Column(name="ID_ISSUE_DATE")
	private String idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	private String imsi;

	@Column(name="IN_ACT_STATUS")
	private String inActStatus;

	@Column(name="IN_SERIAL")
	private String inSerial;

	@Column(name="IN_STATUS")
	private BigDecimal inStatus;

	@Column(name="IN_STATUS_NAME")
	private BigDecimal inStatusName;

	private String isdn;

	@Column(name="LAST_NUMBER")
	private String lastNumber;

	@Column(name="MA_SO_THUE")
	private String maSoThue;

	@Column(name="NUM_RESET_ZONE")
	private String numResetZone;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="POP_ISSUE_DATE")
	private String popIssueDate;

	@Column(name="POP_ISSUE_PLACE")
	private String popIssuePlace;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private String promotion;

	@Column(name="PROMOTION_NAME")
	private String promotionName;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="REG_TYPE_CODE")
	private String regTypeCode;

	private String serial;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STA_DATE")
	private String staDate;

	private BigDecimal status;

	@Column(name="STATUS_NAME")
	private BigDecimal statusName;

	@Column(name="SUB_ADDRESS")
	private String subAddress;

	@Column(name="SUB_BIRTHDAY")
	private String subBirthday;

	@Column(name="SUB_GENDER")
	private String subGender;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_NAME")
	private String subName;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="SUB_VIP")
	private String subVip;

	@Column(name="SUB_VIP_CODE")
	private String subVipCode;

	private BigDecimal subcategory;

	@Column(name="TEL_FAX")
	private String telFax;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="ZONE_CURRENT")
	private String zoneCurrent;

	@Column(name="ZONE_REGISTER")
	private String zoneRegister;

	public ViewCcSubCustMb() {
	}

	public String getActStatusString() {
		return this.actStatusString;
	}

	public void setActStatusString(String actStatusString) {
		this.actStatusString = actStatusString;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBusPermit() {
		return this.busPermit;
	}

	public void setBusPermit(String busPermit) {
		this.busPermit = busPermit;
	}

	public String getCreDate() {
		return this.creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}

	public String getCusVipCode() {
		return this.cusVipCode;
	}

	public void setCusVipCode(String cusVipCode) {
		this.cusVipCode = cusVipCode;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public Date getCustBirth() {
		return this.custBirth;
	}

	public void setCustBirth(Date custBirth) {
		this.custBirth = custBirth;
	}

	public String getCustBirthday() {
		return this.custBirthday;
	}

	public void setCustBirthday(String custBirthday) {
		this.custBirthday = custBirthday;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}

	public String getCustIdType() {
		return this.custIdType;
	}

	public void setCustIdType(String custIdType) {
		this.custIdType = custIdType;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNationality() {
		return this.custNationality;
	}

	public void setCustNationality(String custNationality) {
		this.custNationality = custNationality;
	}

	public String getCustPopNo() {
		return this.custPopNo;
	}

	public void setCustPopNo(String custPopNo) {
		this.custPopNo = custPopNo;
	}

	public String getCustSex() {
		return this.custSex;
	}

	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}

	public String getCustType() {
		return this.custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustVip() {
		return this.custVip;
	}

	public void setCustVip(String custVip) {
		this.custVip = custVip;
	}

	public String getHlrActStatus() {
		return this.hlrActStatus;
	}

	public void setHlrActStatus(String hlrActStatus) {
		this.hlrActStatus = hlrActStatus;
	}

	public String getHlrImsi() {
		return this.hlrImsi;
	}

	public void setHlrImsi(String hlrImsi) {
		this.hlrImsi = hlrImsi;
	}

	public String getHlrIsdn() {
		return this.hlrIsdn;
	}

	public void setHlrIsdn(String hlrIsdn) {
		this.hlrIsdn = hlrIsdn;
	}

	public String getHlrSerial() {
		return this.hlrSerial;
	}

	public void setHlrSerial(String hlrSerial) {
		this.hlrSerial = hlrSerial;
	}

	public BigDecimal getHlrStatus() {
		return this.hlrStatus;
	}

	public void setHlrStatus(BigDecimal hlrStatus) {
		this.hlrStatus = hlrStatus;
	}

	public BigDecimal getHlrStatusName() {
		return this.hlrStatusName;
	}

	public void setHlrStatusName(BigDecimal hlrStatusName) {
		this.hlrStatusName = hlrStatusName;
	}

	public String getHour1() {
		return this.hour1;
	}

	public void setHour1(String hour1) {
		this.hour1 = hour1;
	}

	public String getIdExpireDate() {
		return this.idExpireDate;
	}

	public void setIdExpireDate(String idExpireDate) {
		this.idExpireDate = idExpireDate;
	}

	public String getIdIssueDate() {
		return this.idIssueDate;
	}

	public void setIdIssueDate(String idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdIssuePlace() {
		return this.idIssuePlace;
	}

	public void setIdIssuePlace(String idIssuePlace) {
		this.idIssuePlace = idIssuePlace;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getInActStatus() {
		return this.inActStatus;
	}

	public void setInActStatus(String inActStatus) {
		this.inActStatus = inActStatus;
	}

	public String getInSerial() {
		return this.inSerial;
	}

	public void setInSerial(String inSerial) {
		this.inSerial = inSerial;
	}

	public BigDecimal getInStatus() {
		return this.inStatus;
	}

	public void setInStatus(BigDecimal inStatus) {
		this.inStatus = inStatus;
	}

	public BigDecimal getInStatusName() {
		return this.inStatusName;
	}

	public void setInStatusName(BigDecimal inStatusName) {
		this.inStatusName = inStatusName;
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

	public String getMaSoThue() {
		return this.maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public String getNumResetZone() {
		return this.numResetZone;
	}

	public void setNumResetZone(String numResetZone) {
		this.numResetZone = numResetZone;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public String getPopIssueDate() {
		return this.popIssueDate;
	}

	public void setPopIssueDate(String popIssueDate) {
		this.popIssueDate = popIssueDate;
	}

	public String getPopIssuePlace() {
		return this.popIssuePlace;
	}

	public void setPopIssuePlace(String popIssuePlace) {
		this.popIssuePlace = popIssuePlace;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPromotion() {
		return this.promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getPromotionName() {
		return this.promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getRegTypeCode() {
		return this.regTypeCode;
	}

	public void setRegTypeCode(String regTypeCode) {
		this.regTypeCode = regTypeCode;
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

	public String getStaDate() {
		return this.staDate;
	}

	public void setStaDate(String staDate) {
		this.staDate = staDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStatusName() {
		return this.statusName;
	}

	public void setStatusName(BigDecimal statusName) {
		this.statusName = statusName;
	}

	public String getSubAddress() {
		return this.subAddress;
	}

	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}

	public String getSubBirthday() {
		return this.subBirthday;
	}

	public void setSubBirthday(String subBirthday) {
		this.subBirthday = subBirthday;
	}

	public String getSubGender() {
		return this.subGender;
	}

	public void setSubGender(String subGender) {
		this.subGender = subGender;
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

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getSubVip() {
		return this.subVip;
	}

	public void setSubVip(String subVip) {
		this.subVip = subVip;
	}

	public String getSubVipCode() {
		return this.subVipCode;
	}

	public void setSubVipCode(String subVipCode) {
		this.subVipCode = subVipCode;
	}

	public BigDecimal getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(BigDecimal subcategory) {
		this.subcategory = subcategory;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getZoneCurrent() {
		return this.zoneCurrent;
	}

	public void setZoneCurrent(String zoneCurrent) {
		this.zoneCurrent = zoneCurrent;
	}

	public String getZoneRegister() {
		return this.zoneRegister;
	}

	public void setZoneRegister(String zoneRegister) {
		this.zoneRegister = zoneRegister;
	}

}