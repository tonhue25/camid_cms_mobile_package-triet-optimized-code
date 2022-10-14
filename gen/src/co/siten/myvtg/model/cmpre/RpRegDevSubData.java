package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_REG_DEV_SUB_DATA database table.
 * 
 */
@Entity
@Table(name="RP_REG_DEV_SUB_DATA")
@NamedQuery(name="RpRegDevSubData.findAll", query="SELECT r FROM RpRegDevSubData r")
public class RpRegDevSubData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUS_NAME")
	private String busName;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	private String isdn;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SALE_TRANS_ID")
	private BigDecimal saleTransId;

	@Column(name="SALE_TRANS_STATUS")
	private String saleTransStatus;

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

	@Column(name="USER_CREATED")
	private String userCreated;

	public RpRegDevSubData() {
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

	public BigDecimal getSaleTransId() {
		return this.saleTransId;
	}

	public void setSaleTransId(BigDecimal saleTransId) {
		this.saleTransId = saleTransId;
	}

	public String getSaleTransStatus() {
		return this.saleTransStatus;
	}

	public void setSaleTransStatus(String saleTransStatus) {
		this.saleTransStatus = saleTransStatus;
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

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

}