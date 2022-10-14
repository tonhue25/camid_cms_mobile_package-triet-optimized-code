package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATA_RP_CHANGE_SIM_MB database table.
 * 
 */
@Entity
@Table(name="DATA_RP_CHANGE_SIM_MB")
@NamedQuery(name="DataRpChangeSimMb.findAll", query="SELECT d FROM DataRpChangeSimMb d")
public class DataRpChangeSimMb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_AUDIT_ID")
	private long actionAuditId;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="BIRTH_DATE")
	private Object birthDate;

	@Column(name="CHANNEL_TYPE_ID_SHOP")
	private BigDecimal channelTypeIdShop;

	@Column(name="CHANNEL_TYPE_ID_STAFF")
	private BigDecimal channelTypeIdStaff;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="ID_NO")
	private String idNo;

	private String ip;

	private String isdn;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="NEW_SERIAL")
	private String newSerial;

	private String note;

	@Column(name="OLD_SERIAL")
	private String oldSerial;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private String province;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	private String reason;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private String shop;

	@Column(name="SHOP_CN_ID")
	private BigDecimal shopCnId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUB_NAME")
	private String subName;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="VALID_DATETIME")
	private Object validDatetime;

	public DataRpChangeSimMb() {
	}

	public long getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(long actionAuditId) {
		this.actionAuditId = actionAuditId;
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public BigDecimal getChannelTypeIdShop() {
		return this.channelTypeIdShop;
	}

	public void setChannelTypeIdShop(BigDecimal channelTypeIdShop) {
		this.channelTypeIdShop = channelTypeIdShop;
	}

	public BigDecimal getChannelTypeIdStaff() {
		return this.channelTypeIdStaff;
	}

	public void setChannelTypeIdStaff(BigDecimal channelTypeIdStaff) {
		this.channelTypeIdStaff = channelTypeIdStaff;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Object getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Object issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public String getNewSerial() {
		return this.newSerial;
	}

	public void setNewSerial(String newSerial) {
		this.newSerial = newSerial;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOldSerial() {
		return this.oldSerial;
	}

	public void setOldSerial(String oldSerial) {
		this.oldSerial = oldSerial;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public BigDecimal getShopCnId() {
		return this.shopCnId;
	}

	public void setShopCnId(BigDecimal shopCnId) {
		this.shopCnId = shopCnId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Object getValidDatetime() {
		return this.validDatetime;
	}

	public void setValidDatetime(Object validDatetime) {
		this.validDatetime = validDatetime;
	}

}