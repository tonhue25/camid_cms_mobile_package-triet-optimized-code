package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_CHANGE_PRODUCT_DATA database table.
 * 
 */
@Entity
@Table(name="RP_CHANGE_PRODUCT_DATA")
@NamedQuery(name="RpChangeProductData.findAll", query="SELECT r FROM RpChangeProductData r")
public class RpChangeProductData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="BUS_NAME")
	private String busName;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CURR_OFFER_ID")
	private BigDecimal currOfferId;

	@Column(name="CURR_OFFER_NAME")
	private String currOfferName;

	@Column(name="CURR_PRODUCT_CODE")
	private String currProductCode;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	private String isdn;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="PRE_OFFER_ID")
	private BigDecimal preOfferId;

	@Column(name="PRE_OFFER_NAME")
	private String preOfferName;

	@Column(name="PRE_PRODUCT_CODE")
	private String preProductCode;

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

	@Column(name="USER_NAME")
	private String userName;

	public RpChangeProductData() {
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
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

	public BigDecimal getCurrOfferId() {
		return this.currOfferId;
	}

	public void setCurrOfferId(BigDecimal currOfferId) {
		this.currOfferId = currOfferId;
	}

	public String getCurrOfferName() {
		return this.currOfferName;
	}

	public void setCurrOfferName(String currOfferName) {
		this.currOfferName = currOfferName;
	}

	public String getCurrProductCode() {
		return this.currProductCode;
	}

	public void setCurrProductCode(String currProductCode) {
		this.currProductCode = currProductCode;
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

	public BigDecimal getPreOfferId() {
		return this.preOfferId;
	}

	public void setPreOfferId(BigDecimal preOfferId) {
		this.preOfferId = preOfferId;
	}

	public String getPreOfferName() {
		return this.preOfferName;
	}

	public void setPreOfferName(String preOfferName) {
		this.preOfferName = preOfferName;
	}

	public String getPreProductCode() {
		return this.preProductCode;
	}

	public void setPreProductCode(String preProductCode) {
		this.preProductCode = preProductCode;
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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}