package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TEMP_RP_HP_PRE_ACT_DEL database table.
 * 
 */
@Entity
@Table(name="TEMP_RP_HP_PRE_ACT_DEL")
@NamedQuery(name="TempRpHpPreActDel.findAll", query="SELECT t FROM TempRpHpPreActDel t")
public class TempRpHpPreActDel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="ID_NO")
	private String idNo;

	private String isdn;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	private String note;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private String province;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	private String reason;

	private String shop;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUB_NAME")
	private String subName;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="VALID_DATETIME")
	private Object validDatetime;

	public TempRpHpPreActDel() {
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

}