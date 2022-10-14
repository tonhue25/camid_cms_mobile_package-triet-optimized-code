package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_HP_SUB_DEV_DATA database table.
 * 
 */
@Entity
@Table(name="RP_HP_SUB_DEV_DATA")
@NamedQuery(name="RpHpSubDevData.findAll", query="SELECT r FROM RpHpSubDevData r")
public class RpHpSubDevData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="CUST_ADDRESS")
	private String custAddress;

	@Column(name="CUST_BIRTH_DATE")
	private Object custBirthDate;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_ID_NO")
	private String custIdNo;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="CUST_SEX")
	private String custSex;

	@Column(name="CUST_TYPE")
	private String custType;

	@Column(name="CUST_TYPE_NAME")
	private String custTypeName;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	@Column(name="LOCATION_NAME")
	private String locationName;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	@Column(name="REASON_CODE")
	private String reasonCode;

	@Column(name="REASON_DESCRIPTION")
	private String reasonDescription;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REASON_NAME")
	private String reasonName;

	@Column(name="SALE_TRANS_ID")
	private String saleTransId;

	private String serial;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_ID")
	private BigDecimal shopId;

	@Column(name="SHOP_NAME")
	private String shopName;

	@Column(name="STAFF_BIRTHDAY")
	private Object staffBirthday;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	@Column(name="STAFF_ISDN")
	private String staffIsdn;

	@Column(name="STAFF_NAME")
	private String staffName;

	@Column(name="STOCK_MODEL_ID")
	private String stockModelId;

	@Column(name="SUB_ADDRESS")
	private String subAddress;

	@Column(name="SUB_ADDRESS_CODE")
	private String subAddressCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_ISDN")
	private String subIsdn;

	@Column(name="SUB_PRODUCT_CODE")
	private String subProductCode;

	@Column(name="SUB_PROMOTION_CODE")
	private String subPromotionCode;

	@Column(name="SUB_STA_DATETIME")
	private Object subStaDatetime;

	@Column(name="SUB_USER_CREATE")
	private String subUserCreate;

	@Column(name="SUB_USER_NAME")
	private String subUserName;

	@Column(name="SUB_VALID_DATETIME")
	private Object subValidDatetime;

	@Column(name="ZONE_CODE")
	private String zoneCode;

	@Column(name="ZONE_ID")
	private BigDecimal zoneId;

	@Column(name="ZONE_NAME")
	private String zoneName;

	public RpHpSubDevData() {
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public Object getCustBirthDate() {
		return this.custBirthDate;
	}

	public void setCustBirthDate(Object custBirthDate) {
		this.custBirthDate = custBirthDate;
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

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getCustTypeName() {
		return this.custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	public BigDecimal getHisId() {
		return this.hisId;
	}

	public void setHisId(BigDecimal hisId) {
		this.hisId = hisId;
	}

	public Object getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Object issueDatetime) {
		this.issueDatetime = issueDatetime;
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

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonDescription() {
		return this.reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getSaleTransId() {
		return this.saleTransId;
	}

	public void setSaleTransId(String saleTransId) {
		this.saleTransId = saleTransId;
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

	public BigDecimal getShopId() {
		return this.shopId;
	}

	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Object getStaffBirthday() {
		return this.staffBirthday;
	}

	public void setStaffBirthday(Object staffBirthday) {
		this.staffBirthday = staffBirthday;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getStaffIsdn() {
		return this.staffIsdn;
	}

	public void setStaffIsdn(String staffIsdn) {
		this.staffIsdn = staffIsdn;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStockModelId() {
		return this.stockModelId;
	}

	public void setStockModelId(String stockModelId) {
		this.stockModelId = stockModelId;
	}

	public String getSubAddress() {
		return this.subAddress;
	}

	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}

	public String getSubAddressCode() {
		return this.subAddressCode;
	}

	public void setSubAddressCode(String subAddressCode) {
		this.subAddressCode = subAddressCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubIsdn() {
		return this.subIsdn;
	}

	public void setSubIsdn(String subIsdn) {
		this.subIsdn = subIsdn;
	}

	public String getSubProductCode() {
		return this.subProductCode;
	}

	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}

	public String getSubPromotionCode() {
		return this.subPromotionCode;
	}

	public void setSubPromotionCode(String subPromotionCode) {
		this.subPromotionCode = subPromotionCode;
	}

	public Object getSubStaDatetime() {
		return this.subStaDatetime;
	}

	public void setSubStaDatetime(Object subStaDatetime) {
		this.subStaDatetime = subStaDatetime;
	}

	public String getSubUserCreate() {
		return this.subUserCreate;
	}

	public void setSubUserCreate(String subUserCreate) {
		this.subUserCreate = subUserCreate;
	}

	public String getSubUserName() {
		return this.subUserName;
	}

	public void setSubUserName(String subUserName) {
		this.subUserName = subUserName;
	}

	public Object getSubValidDatetime() {
		return this.subValidDatetime;
	}

	public void setSubValidDatetime(Object subValidDatetime) {
		this.subValidDatetime = subValidDatetime;
	}

	public String getZoneCode() {
		return this.zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public BigDecimal getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(BigDecimal zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

}