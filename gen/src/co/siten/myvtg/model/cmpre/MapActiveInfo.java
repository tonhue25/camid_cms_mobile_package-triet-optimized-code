package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAP_ACTIVE_INFO database table.
 * 
 */
@Entity
@Table(name="MAP_ACTIVE_INFO")
@NamedQuery(name="MapActiveInfo.findAll", query="SELECT m FROM MapActiveInfo m")
public class MapActiveInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BRAND_ID")
	private BigDecimal brandId;

	@Column(name="BRAND_NAME")
	private String brandName;

	@Column(name="CHANGE_DATETIME")
	private Object changeDatetime;

	@Column(name="CHANNEL_NAME")
	private String channelName;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="EFFECT_DATETIME")
	private Object effectDatetime;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	private BigDecimal id;

	private String ip;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="OFFER_NAME")
	private String offerName;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PROM_CODE")
	private String promCode;

	@Column(name="PROM_NAME")
	private String promName;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	@Column(name="REASON_CODE")
	private String reasonCode;

	@Column(name="REASON_NAME")
	private String reasonName;

	@Column(name="SHOP_CODE")
	private String shopCode;

	private BigDecimal status;

	@Column(name="TEL_SERVICE")
	private BigDecimal telService;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_UPDATED")
	private String userUpdated;

	public MapActiveInfo() {
	}

	public BigDecimal getBrandId() {
		return this.brandId;
	}

	public void setBrandId(BigDecimal brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Object getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Object changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public Object getEffectDatetime() {
		return this.effectDatetime;
	}

	public void setEffectDatetime(Object effectDatetime) {
		this.effectDatetime = effectDatetime;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return this.offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPromCode() {
		return this.promCode;
	}

	public void setPromCode(String promCode) {
		this.promCode = promCode;
	}

	public String getPromName() {
		return this.promName;
	}

	public void setPromName(String promName) {
		this.promName = promName;
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

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTelService() {
		return this.telService;
	}

	public void setTelService(BigDecimal telService) {
		this.telService = telService;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getUserUpdated() {
		return this.userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

}