package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_CHANGE_SIM_HP database table.
 * 
 */
@Entity
@Table(name="RP_CHANGE_SIM_HP")
@NamedQuery(name="RpChangeSimHp.findAll", query="SELECT r FROM RpChangeSimHp r")
public class RpChangeSimHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BIRTH_DATE")
	private Object birthDate;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="ID_NO")
	private String idNo;

	private String isdn;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="NEW_IMSI")
	private String newImsi;

	private String note;

	@Column(name="OLD_IMSI")
	private String oldImsi;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private String province;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	private String reason;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private String shop;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUB_NAME")
	private String subName;

	@Column(name="VALID_DATETIME")
	private Object validDatetime;

	public RpChangeSimHp() {
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
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

	public String getNewImsi() {
		return this.newImsi;
	}

	public void setNewImsi(String newImsi) {
		this.newImsi = newImsi;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOldImsi() {
		return this.oldImsi;
	}

	public void setOldImsi(String oldImsi) {
		this.oldImsi = oldImsi;
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

	public Object getValidDatetime() {
		return this.validDatetime;
	}

	public void setValidDatetime(Object validDatetime) {
		this.validDatetime = validDatetime;
	}

}