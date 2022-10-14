/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
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
@Table(name = "ALL_CUST_SUB_FOR_SELFCARE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AllCustSubForSelfcare.findAll", query = "SELECT a FROM AllCustSubForSelfcare a"),
    @NamedQuery(name = "AllCustSubForSelfcare.findBySubId", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.subId = :subId"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByIsdn", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.isdn = :isdn"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByCustId", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.custId = :custId"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByCustName", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.custName = :custName"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByCustStatus", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.custStatus = :custStatus"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByOfferId", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.offerId = :offerId"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByProductId", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.productId = :productId"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByStatus", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.status = :status"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByStatusId", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.statusId = :statusId"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByActStatus", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.actStatus = :actStatus"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByActStatusBits", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.actStatusBits = :actStatusBits"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByService", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.service = :service"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByServiceType", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.serviceType = :serviceType"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByLastNumber", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.lastNumber = :lastNumber"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByStaDatetime", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.staDatetime = :staDatetime"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByEndDatetime", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.endDatetime = :endDatetime"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByImsi", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.imsi = :imsi"),
    @NamedQuery(name = "AllCustSubForSelfcare.findBySerial", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.serial = :serial"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByRegType", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.regType = :regType"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByPromotionCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.promotionCode = :promotionCode"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByAddress", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.address = :address"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByIdNo", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.idNo = :idNo"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByIdIssueDate", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.idIssueDate = :idIssueDate"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByIdIssuePlace", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.idIssuePlace = :idIssuePlace"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByBirthDate", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.birthDate = :birthDate"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByGender", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.gender = :gender"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByUserCreated", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.userCreated = :userCreated"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByShopCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.shopCode = :shopCode"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByCreateDatetime", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.createDatetime = :createDatetime"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByCustAddress", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.custAddress = :custAddress"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByProductCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.productCode = :productCode"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByIdType", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.idType = :idType"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByAreaCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.areaCode = :areaCode"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByProvinceCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.provinceCode = :provinceCode"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByDistrictCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.districtCode = :districtCode"),
    @NamedQuery(name = "AllCustSubForSelfcare.findByPrecinctCode", query = "SELECT a FROM AllCustSubForSelfcare a WHERE a.precinctCode = :precinctCode")})
public class AllCustSubForSelfcare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SUB_ID")
    private Long subId;
    @Size(max = 10)
    @Column(name = "ISDN")
    private String isdn;
    @Column(name = "CUST_ID")
    private Long custId;
    @Size(max = 120)
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "CUST_STATUS")
    private Short custStatus;
    @Column(name = "OFFER_ID")
    private Long offerId;
    @Size(max = 30)
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Size(max = 19)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "STATUS_ID")
    private Short statusId;
    @Size(max = 36)
    @Column(name = "ACT_STATUS")
    private String actStatus;
    @Size(max = 2)
    @Column(name = "ACT_STATUS_BITS")
    private String actStatusBits;
    @Size(max = 9)
    @Column(name = "SERVICE")
    private String service;
    @Column(name = "SERVICE_TYPE")
    private Character serviceType;
    @Size(max = 1)
    @Column(name = "LAST_NUMBER")
    private String lastNumber;
    @Column(name = "STA_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date staDatetime;
    @Column(name = "END_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDatetime;
    @Size(max = 30)
    @Column(name = "IMSI")
    private String imsi;
    @Size(max = 30)
    @Column(name = "SERIAL")
    private String serial;
    @Size(max = 15)
    @Column(name = "REG_TYPE")
    private String regType;
    @Size(max = 3)
    @Column(name = "PROMOTION_CODE")
    private String promotionCode;
    @Size(max = 500)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 20)
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
    @Size(max = 50)
    @Column(name = "USER_CREATED")
    private String userCreated;
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
    @Size(max = 100)
    @Column(name = "ID_TYPE")
    private String idType;
    @Size(max = 27)
    @Column(name = "AREA_CODE")
    private String areaCode;
    @Size(max = 20)
    @Column(name = "PROVINCE_CODE")
    private String provinceCode;
    @Size(max = 3)
    @Column(name = "DISTRICT_CODE")
    private String districtCode;
    @Size(max = 4)
    @Column(name = "PRECINCT_CODE")
    private String precinctCode;

    public AllCustSubForSelfcare() {
    }
    
    

    public AllCustSubForSelfcare(Long subId, String custName, Date birthDate, String gender, String productCode) {
		super();
		this.custName = custName;
		this.birthDate = birthDate;
		this.subId= subId;
		this.gender = gender;
		this.productCode= productCode;
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

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Character getServiceType() {
        return serviceType;
    }

    public void setServiceType(Character serviceType) {
        this.serviceType = serviceType;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }

    public Date getStaDatetime() {
        return staDatetime;
    }

    public void setStaDatetime(Date staDatetime) {
        this.staDatetime = staDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
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

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
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
