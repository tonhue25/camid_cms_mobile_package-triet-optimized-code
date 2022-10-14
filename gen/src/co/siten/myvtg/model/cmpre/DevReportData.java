package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DEV_REPORT_DATA database table.
 * 
 */
@Entity
@Table(name="DEV_REPORT_DATA")
@NamedQuery(name="DevReportData.findAll", query="SELECT d FROM DevReportData d")
public class DevReportData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUS_NAME")
	private String busName;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="FULL_NAME")
	private String fullName;

	private String isdn;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="PROMOTION_NAME")
	private String promotionName;

	private String province;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_NAME")
	private String shopName;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_NAME")
	private String userName;

	public DevReportData() {
	}

	public String getBusName() {
		return this.busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
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

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getPromotionName() {
		return this.promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
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

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}