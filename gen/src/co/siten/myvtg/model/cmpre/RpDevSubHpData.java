package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_DEV_SUB_HP_DATA database table.
 * 
 */
@Entity
@Table(name="RP_DEV_SUB_HP_DATA")
@NamedQuery(name="RpDevSubHpData.findAll", query="SELECT r FROM RpDevSubHpData r")
public class RpDevSubHpData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="BUS_TYPE_NAME")
	private String busTypeName;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="CHANNEL_TYPE_NAME")
	private String channelTypeName;

	@Column(name="CHANNEL_TYPE_REQ_ID")
	private BigDecimal channelTypeReqId;

	@Column(name="CHANNEL_TYPE_REQ_NAME")
	private String channelTypeReqName;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="IS_INFO_COMPLETED")
	private String isInfoCompleted;

	private String isdn;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="PROMOTION_TYPE_NAME")
	private String promotionTypeName;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_NAME")
	private String shopName;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="\"ZONE\"")
	private String zone;

	public RpDevSubHpData() {
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusTypeName() {
		return this.busTypeName;
	}

	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getChannelTypeName() {
		return this.channelTypeName;
	}

	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}

	public BigDecimal getChannelTypeReqId() {
		return this.channelTypeReqId;
	}

	public void setChannelTypeReqId(BigDecimal channelTypeReqId) {
		this.channelTypeReqId = channelTypeReqId;
	}

	public String getChannelTypeReqName() {
		return this.channelTypeReqName;
	}

	public void setChannelTypeReqName(String channelTypeReqName) {
		this.channelTypeReqName = channelTypeReqName;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigDecimal getHisId() {
		return this.hisId;
	}

	public void setHisId(BigDecimal hisId) {
		this.hisId = hisId;
	}

	public String getIsInfoCompleted() {
		return this.isInfoCompleted;
	}

	public void setIsInfoCompleted(String isInfoCompleted) {
		this.isInfoCompleted = isInfoCompleted;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
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

	public String getPromotionTypeName() {
		return this.promotionTypeName;
	}

	public void setPromotionTypeName(String promotionTypeName) {
		this.promotionTypeName = promotionTypeName;
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

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

}