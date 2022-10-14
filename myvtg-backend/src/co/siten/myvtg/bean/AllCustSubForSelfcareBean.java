/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author thomc
 */
public class AllCustSubForSelfcareBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long subId;
    private String isdn;
    private Long custId;
    private String custName;
    private Short custStatus;
    private Long offerId;
    private String productId;
    private String status;
    private Short statusId;
    private String actStatus;
    private String actStatusBits;
    private String service;
    private Character serviceType;
    private String lastNumber;
    private Date staDatetime;
    private Date endDatetime;
    private String imsi;
    private String serial;
    private String regType;
    private String promotionCode;
    private String address;
    private String idNo;
    private Date idIssueDate;
    private String idIssuePlace;
    private Date birthDate;
    private String gender;
    private String userCreated;
    private String shopCode;
    private Date createDatetime;
    private String custAddress;
    private String productCode;
    private String idType;
    private String areaCode;
    private String provinceCode;
    private String districtCode;
    private String precinctCode;

    public AllCustSubForSelfcareBean() {
    }
    
    
    
    

    public AllCustSubForSelfcareBean(Long subId, String isdn, Long custId, String custName, Short custStatus,
			Long offerId, String productId, String status, Short statusId, String actStatus, String actStatusBits,
			String service, Character serviceType, String lastNumber, Date staDatetime, Date endDatetime, String imsi,
			String serial, String regType, String promotionCode, String address, String idNo, Date idIssueDate,
			String idIssuePlace, Date birthDate, String gender, String userCreated, String shopCode,
			Date createDatetime, String custAddress, String productCode, String idType, String areaCode,
			String provinceCode, String districtCode, String precinctCode) {
		super();
		this.subId = subId;
		this.isdn = isdn;
		this.custId = custId;
		this.custName = custName;
		this.custStatus = custStatus;
		this.offerId = offerId;
		this.productId = productId;
		this.status = status;
		this.statusId = statusId;
		this.actStatus = actStatus;
		this.actStatusBits = actStatusBits;
		this.service = service;
		this.serviceType = serviceType;
		this.lastNumber = lastNumber;
		this.staDatetime = staDatetime;
		this.endDatetime = endDatetime;
		this.imsi = imsi;
		this.serial = serial;
		this.regType = regType;
		this.promotionCode = promotionCode;
		this.address = address;
		this.idNo = idNo;
		this.idIssueDate = idIssueDate;
		this.idIssuePlace = idIssuePlace;
		this.birthDate = birthDate;
		this.gender = gender;
		this.userCreated = userCreated;
		this.shopCode = shopCode;
		this.createDatetime = createDatetime;
		this.custAddress = custAddress;
		this.productCode = productCode;
		this.idType = idType;
		this.areaCode = areaCode;
		this.provinceCode = provinceCode;
		this.districtCode = districtCode;
		this.precinctCode = precinctCode;
	}





	public AllCustSubForSelfcareBean(Long subId, String custName, Date birthDate, String gender, String productCode) {
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
