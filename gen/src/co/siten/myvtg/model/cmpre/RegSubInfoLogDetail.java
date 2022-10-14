package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REG_SUB_INFO_LOG_DETAIL database table.
 * 
 */
@Entity
@Table(name="REG_SUB_INFO_LOG_DETAIL")
@NamedQuery(name="RegSubInfoLogDetail.findAll", query="SELECT r FROM RegSubInfoLogDetail r")
public class RegSubInfoLogDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BIRTH_DATE")
	private String birthDate;

	@Column(name="BIRTH_PLACE")
	private String birthPlace;

	private String description;

	private String district;

	@Column(name="HOME_NUMBER")
	private String homeNumber;

	private BigDecimal id;

	@Column(name="ID_EXPIRE_DATE")
	private String idExpireDate;

	@Column(name="ID_ISSUE_DATE")
	private String idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ID_TYPE")
	private String idType;

	private String isdn;

	@Column(name="NAME_OF_CUSTOMER")
	private String nameOfCustomer;

	private String precinct;

	private String province;

	@Column(name="REG_SUB_INFO_LOG_ID")
	private BigDecimal regSubInfoLogId;

	private String sex;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STAFF_CODE")
	private String staffCode;

	private String street;

	public RegSubInfoLogDetail() {
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getHomeNumber() {
		return this.homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIdExpireDate() {
		return this.idExpireDate;
	}

	public void setIdExpireDate(String idExpireDate) {
		this.idExpireDate = idExpireDate;
	}

	public String getIdIssueDate() {
		return this.idIssueDate;
	}

	public void setIdIssueDate(String idIssueDate) {
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

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getNameOfCustomer() {
		return this.nameOfCustomer;
	}

	public void setNameOfCustomer(String nameOfCustomer) {
		this.nameOfCustomer = nameOfCustomer;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public BigDecimal getRegSubInfoLogId() {
		return this.regSubInfoLogId;
	}

	public void setRegSubInfoLogId(BigDecimal regSubInfoLogId) {
		this.regSubInfoLogId = regSubInfoLogId;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}