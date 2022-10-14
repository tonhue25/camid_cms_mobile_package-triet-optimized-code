/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thomc
 */
@Entity
@Table(name = "ALL_TEL_SERVICE_SUB_SELFCARE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AllTelServiceSubSelfcare.findAll", query = "SELECT a FROM AllTelServiceSubSelfcare a"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByContractId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.contractId = :contractId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findBySubId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.subId = :subId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByIsdn", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.isdn = :isdn"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByOldContractId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.oldContractId = :oldContractId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByAdslSubId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.adslSubId = :adslSubId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByContractNo", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.contractNo = :contractNo"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByContractStatus", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.contractStatus = :contractStatus"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByCustId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.custId = :custId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByCustName", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.custName = :custName"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByCustStatus", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.custStatus = :custStatus"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByAccount", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.account = :account"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByActStatus", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.actStatus = :actStatus"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByActStatusBits", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.actStatusBits = :actStatusBits"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByStatus", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.status = :status"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByStatusId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.statusId = :statusId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByServiceType", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.serviceType = :serviceType"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByService", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.service = :service"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByProductId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.productId = :productId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByEndDatetime", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.endDatetime = :endDatetime"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByStaDatetime", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.staDatetime = :staDatetime"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findBySubType", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.subType = :subType"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByUserUsing", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.userUsing = :userUsing"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByImsi", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.imsi = :imsi"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findBySerial", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.serial = :serial"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByRegType", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.regType = :regType"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByPromotionCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.promotionCode = :promotionCode"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByAddress", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.address = :address"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByIdNo", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.idNo = :idNo"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByIdIssueDate", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.idIssueDate = :idIssueDate"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByIdIssuePlace", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.idIssuePlace = :idIssuePlace"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByBirthDate", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.birthDate = :birthDate"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByGender", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.gender = :gender"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByStaffId", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.staffId = :staffId"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByShopCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.shopCode = :shopCode"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByCreateDatetime", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.createDatetime = :createDatetime"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByCustAddress", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.custAddress = :custAddress"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByProductCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.productCode = :productCode"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByIdType", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.idType = :idType"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByAreaCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.areaCode = :areaCode"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByProvinceCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.provinceCode = :provinceCode"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByDistrictCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.districtCode = :districtCode"),
		@NamedQuery(name = "AllTelServiceSubSelfcare.findByPrecinctCode", query = "SELECT a FROM AllTelServiceSubSelfcare a WHERE a.precinctCode = :precinctCode") })
public class AllTelServiceSubSelfcare implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CONTRACT_ID")
	private BigInteger contractId;
	@Column(name = "SUB_ID")
	private Long subId;
	@Size(max = 20)
	@Column(name = "ISDN")
	private String isdn;
	@Size(max = 0)
	@Column(name = "OLD_CONTRACT_ID")
	private String oldContractId;
	@Size(max = 0)
	@Column(name = "ADSL_SUB_ID")
	private String adslSubId;
	@Size(max = 50)
	@Column(name = "CONTRACT_NO")
	private String contractNo;
	@Column(name = "CONTRACT_STATUS")
	private Long contractStatus;
	@Column(name = "CUST_ID")
	private Long custId;
	@Size(max = 120)
	@Column(name = "CUST_NAME")
	private String custName;
	@Column(name = "CUST_STATUS")
	private Short custStatus;
	@Size(max = 100)
	@Column(name = "ACCOUNT")
	private String account;
	@Size(max = 91)
	@Column(name = "ACT_STATUS")
	private String actStatus;
	@Size(max = 3)
	@Column(name = "ACT_STATUS_BITS")
	private String actStatusBits;
	@Size(max = 11)
	@Column(name = "STATUS")
	private String status;
	@Column(name = "STATUS_ID")
	private Short statusId;
	@Size(max = 10)
	@Column(name = "SERVICE_TYPE")
	private String serviceType;
	@Size(max = 16)
	@Column(name = "SERVICE")
	private String service;
	@Size(max = 30)
	@Column(name = "PRODUCT_ID")
	private String productId;
	@Column(name = "END_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDatetime;
	@Column(name = "STA_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date staDatetime;
	@Size(max = 10)
	@Column(name = "SUB_TYPE")
	private String subType;
	@Size(max = 200)
	@Column(name = "USER_USING")
	private String userUsing;
	@Size(max = 30)
	@Column(name = "IMSI")
	private String imsi;
	@Size(max = 30)
	@Column(name = "SERIAL")
	private String serial;
	@Size(max = 15)
	@Column(name = "REG_TYPE")
	private String regType;
	@Size(max = 4)
	@Column(name = "PROMOTION_CODE")
	private String promotionCode;
	@Size(max = 500)
	@Column(name = "ADDRESS")
	private String address;
	@Size(max = 40)
	@Column(name = "ID_NO")
	private String idNo;
	@Column(name = "ID_ISSUE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date idIssueDate;
	@Size(max = 50)
	@Column(name = "ID_ISSUE_PLACE")
	private String idIssuePlace;
	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@Size(max = 6)
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "STAFF_ID")
	private Long staffId;
	@Size(max = 50)
	@Column(name = "SHOP_CODE")
	private String shopCode;
	@Column(name = "CREATE_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDatetime;
	@Size(max = 500)
	@Column(name = "CUST_ADDRESS")
	private String custAddress;
	@Size(max = 30)
	@Column(name = "PRODUCT_CODE")
	private String productCode;
	@Size(max = 255)
	@Column(name = "ID_TYPE")
	private String idType;
	@Size(max = 27)
	@Column(name = "AREA_CODE")
	private String areaCode;
	@Size(max = 5)
	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	@Size(max = 5)
	@Column(name = "DISTRICT_CODE")
	private String districtCode;
	@Size(max = 5)
	@Column(name = "PRECINCT_CODE")
	private String precinctCode;

	public AllTelServiceSubSelfcare() {
	}

	public AllTelServiceSubSelfcare(long subId, String custName, Date birthDate, String gender, String productCode) {
		super();
		this.custName = custName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.subId = subId;
		this.productCode = productCode;
	}

	public BigInteger getContractId() {
		return contractId;
	}

	public void setContractId(BigInteger contractId) {
		this.contractId = contractId;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getIsdn() {
		return isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getOldContractId() {
		return oldContractId;
	}

	public void setOldContractId(String oldContractId) {
		this.oldContractId = oldContractId;
	}

	public String getAdslSubId() {
		return adslSubId;
	}

	public void setAdslSubId(String adslSubId) {
		this.adslSubId = adslSubId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Long getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Long contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Short getCustStatus() {
		return custStatus;
	}

	public void setCustStatus(Short custStatus) {
		this.custStatus = custStatus;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getActStatus() {
		return actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getActStatusBits() {
		return actStatusBits;
	}

	public void setActStatusBits(String actStatusBits) {
		this.actStatusBits = actStatusBits;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Short getStatusId() {
		return statusId;
	}

	public void setStatusId(Short statusId) {
		this.statusId = statusId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Date getStaDatetime() {
		return staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getUserUsing() {
		return userUsing;
	}

	public void setUserUsing(String userUsing) {
		this.userUsing = userUsing;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Date getIdIssueDate() {
		return idIssueDate;
	}

	public void setIdIssueDate(Date idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdIssuePlace() {
		return idIssuePlace;
	}

	public void setIdIssuePlace(String idIssuePlace) {
		this.idIssuePlace = idIssuePlace;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getPrecinctCode() {
		return precinctCode;
	}

	public void setPrecinctCode(String precinctCode) {
		this.precinctCode = precinctCode;
	}

}
