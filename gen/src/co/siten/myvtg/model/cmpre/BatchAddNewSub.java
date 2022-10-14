package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BATCH_ADD_NEW_SUB database table.
 * 
 */
@Entity
@Table(name="BATCH_ADD_NEW_SUB")
@NamedQuery(name="BatchAddNewSub.findAll", query="SELECT b FROM BatchAddNewSub b")
public class BatchAddNewSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTIVE_DATE")
	private Object activeDate;

	private String address;

	@Column(name="BIRTH_DATE")
	private Object birthDate;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_NAME")
	private String custName;

	private String district;

	private String gender;

	private String home;

	private BigDecimal id;

	@Column(name="ID_ISSUE_DATE")
	private Object idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	@Column(name="ID_NO")
	private String idNo;

	private String imsi;

	@Column(name="IS_OLD_CUST")
	private String isOldCust;

	private String isdn;

	private String notes;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	private String precinct;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private String province;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="\"RESULT\"")
	private String result;

	@Column(name="SALE_SERVICE_CODE")
	private String saleServiceCode;

	private String serial;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="START_MONEY")
	private String startMoney;

	private BigDecimal status;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal valid;

	public BatchAddNewSub() {
	}

	public Object getActiveDate() {
		return this.activeDate;
	}

	public void setActiveDate(Object activeDate) {
		this.activeDate = activeDate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
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

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getIdIssueDate() {
		return this.idIssueDate;
	}

	public void setIdIssueDate(Object idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdIssuePlace() {
		return this.idIssuePlace;
	}

	public void setIdIssuePlace(String idIssuePlace) {
		this.idIssuePlace = idIssuePlace;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsOldCust() {
		return this.isOldCust;
	}

	public void setIsOldCust(String isOldCust) {
		this.isOldCust = isOldCust;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
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

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSaleServiceCode() {
		return this.saleServiceCode;
	}

	public void setSaleServiceCode(String saleServiceCode) {
		this.saleServiceCode = saleServiceCode;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStartMoney() {
		return this.startMoney;
	}

	public void setStartMoney(String startMoney) {
		this.startMoney = startMoney;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getStreetBlockName() {
		return this.streetBlockName;
	}

	public void setStreetBlockName(String streetBlockName) {
		this.streetBlockName = streetBlockName;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getValid() {
		return this.valid;
	}

	public void setValid(BigDecimal valid) {
		this.valid = valid;
	}

}