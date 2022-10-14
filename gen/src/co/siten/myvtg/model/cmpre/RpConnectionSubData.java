package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_CONNECTION_SUB_DATA database table.
 * 
 */
@Entity
@Table(name="RP_CONNECTION_SUB_DATA")
@NamedQuery(name="RpConnectionSubData.findAll", query="SELECT r FROM RpConnectionSubData r")
public class RpConnectionSubData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="BRANCH_NAME")
	private String branchName;

	@Column(name="BUS_NAME")
	private String busName;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="GET_MONEY")
	private BigDecimal getMoney;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	private String isdn;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SALE_SERVICE_STATUS")
	private String saleServiceStatus;

	@Column(name="SALE_TRANS_ID")
	private BigDecimal saleTransId;

	@Column(name="SERVICE_CODE")
	private String serviceCode;

	@Column(name="SERVICE_NAME")
	private String serviceName;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_NAME")
	private String shopName;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="STATUS_CUST")
	private String statusCust;

	@Column(name="STATUS_CUSTOMER")
	private String statusCustomer;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="USER_NAME")
	private String userName;

	public RpConnectionSubData() {
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

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public BigDecimal getGetMoney() {
		return this.getMoney;
	}

	public void setGetMoney(BigDecimal getMoney) {
		this.getMoney = getMoney;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public String getSaleServiceStatus() {
		return this.saleServiceStatus;
	}

	public void setSaleServiceStatus(String saleServiceStatus) {
		this.saleServiceStatus = saleServiceStatus;
	}

	public BigDecimal getSaleTransId() {
		return this.saleTransId;
	}

	public void setSaleTransId(BigDecimal saleTransId) {
		this.saleTransId = saleTransId;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public String getStatusCust() {
		return this.statusCust;
	}

	public void setStatusCust(String statusCust) {
		this.statusCust = statusCust;
	}

	public String getStatusCustomer() {
		return this.statusCustomer;
	}

	public void setStatusCustomer(String statusCustomer) {
		this.statusCustomer = statusCustomer;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}