package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_CTV database table.
 * 
 */
@Entity
@Table(name="SUB_CTV")
@NamedQuery(name="SubCtv.findAll", query="SELECT s FROM SubCtv s")
public class SubCtv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	@Column(name="ACTIVE_DATETIME")
	private Object activeDatetime;

	private String app;

	@Column(name="COMM_STATUS")
	private String commStatus;

	@Column(name="INSERT_DATE")
	private Object insertDate;

	private String isdn;

	@Column(name="ISDN_CTV")
	private String isdnCtv;

	@Column(name="ISDN_PRICE")
	private BigDecimal isdnPrice;

	@Column(name="ISDN_TYPE")
	private String isdnType;

	@Column(name="MODIFY_DATE")
	private Object modifyDate;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	private String username;

	public SubCtv() {
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Object getActiveDatetime() {
		return this.activeDatetime;
	}

	public void setActiveDatetime(Object activeDatetime) {
		this.activeDatetime = activeDatetime;
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getCommStatus() {
		return this.commStatus;
	}

	public void setCommStatus(String commStatus) {
		this.commStatus = commStatus;
	}

	public Object getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Object insertDate) {
		this.insertDate = insertDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getIsdnCtv() {
		return this.isdnCtv;
	}

	public void setIsdnCtv(String isdnCtv) {
		this.isdnCtv = isdnCtv;
	}

	public BigDecimal getIsdnPrice() {
		return this.isdnPrice;
	}

	public void setIsdnPrice(BigDecimal isdnPrice) {
		this.isdnPrice = isdnPrice;
	}

	public String getIsdnType() {
		return this.isdnType;
	}

	public void setIsdnType(String isdnType) {
		this.isdnType = isdnType;
	}

	public Object getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Object modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}