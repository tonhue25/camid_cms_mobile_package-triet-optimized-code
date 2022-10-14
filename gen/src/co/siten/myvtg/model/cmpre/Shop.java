package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SHOP database table.
 * 
 */
@Entity
@NamedQuery(name="Shop.findAll", query="SELECT s FROM Shop s")
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	private String address;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="CENTER_CODE")
	private String centerCode;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	private String company;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_TITLE")
	private String contactTitle;

	@Column(name="CREATE_DATE")
	private Object createDate;

	private String description;

	@Column(name="DISCOUNT_POLICY")
	private String discountPolicy;

	private String email;

	private String fax;

	private String name;

	@Column(name="OLD_SHOP_CODE")
	private String oldShopCode;

	@Column(name="PAR_SHOP_CODE")
	private String parShopCode;

	@Column(name="PARENT_SHOP_ID")
	private BigDecimal parentShopId;

	@Column(name="PAY_COMM")
	private String payComm;

	@Column(name="PRICE_POLICY")
	private String pricePolicy;

	private String province;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	private String shop;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_ID")
	private BigDecimal shopId;

	@Column(name="SHOP_PATH")
	private String shopPath;

	@Column(name="SHOP_TYPE")
	private String shopType;

	private BigDecimal status;

	private String tel;

	@Column(name="TEL_NUMBER")
	private String telNumber;

	private String tin;

	public Shop() {
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

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCenterCode() {
		return this.centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return this.contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiscountPolicy() {
		return this.discountPolicy;
	}

	public void setDiscountPolicy(String discountPolicy) {
		this.discountPolicy = discountPolicy;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldShopCode() {
		return this.oldShopCode;
	}

	public void setOldShopCode(String oldShopCode) {
		this.oldShopCode = oldShopCode;
	}

	public String getParShopCode() {
		return this.parShopCode;
	}

	public void setParShopCode(String parShopCode) {
		this.parShopCode = parShopCode;
	}

	public BigDecimal getParentShopId() {
		return this.parentShopId;
	}

	public void setParentShopId(BigDecimal parentShopId) {
		this.parentShopId = parentShopId;
	}

	public String getPayComm() {
		return this.payComm;
	}

	public void setPayComm(String payComm) {
		this.payComm = payComm;
	}

	public String getPricePolicy() {
		return this.pricePolicy;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
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

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
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

	public String getShopPath() {
		return this.shopPath;
	}

	public void setShopPath(String shopPath) {
		this.shopPath = shopPath;
	}

	public String getShopType() {
		return this.shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTelNumber() {
		return this.telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

}