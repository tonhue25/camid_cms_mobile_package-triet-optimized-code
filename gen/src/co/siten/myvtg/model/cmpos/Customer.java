package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTIVITIES_FIELD")
	private String activitiesField;

	@Temporal(TemporalType.DATE)
	@Column(name="ADDED_DATE")
	private Date addedDate;

	@Column(name="ADDED_USER")
	private String addedUser;

	private String address;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	private Date birthDate;

	@Column(name="BIRTH_PLACE")
	private String birthPlace;

	@Column(name="BUS_PERMIT_NO")
	private String busPermitNo;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_TITLE")
	private String contactTitle;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_TYPE_ID")
	private BigDecimal custTypeId;

	private String district;

	private String email;

	@Column(name="FINANCIAL_POINT")
	private BigDecimal financialPoint;

	@Column(name="FULL_NAME")
	private String fullName;

	private String home;

	@Temporal(TemporalType.DATE)
	@Column(name="ID_EXPIRE_DATE")
	private Date idExpireDate;

	@Temporal(TemporalType.DATE)
	@Column(name="ID_ISSUE_DATE")
	private Date idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ID_TYPE")
	private BigDecimal idType;

	private String image;

	@Column(name="IMAGE_FILE")
	private String imageFile;

	@Column(name="IMAGE_NAME")
	private String imageName;

	@Column(name="IMAGE_PATH")
	private String imagePath;

	@Column(name="IMAGE_SIGNATURE_NAME")
	private String imageSignatureName;

	@Column(name="LANDLINE_NO")
	private String landlineNo;

	@Column(name="LAST_NAME_FATHER")
	private String lastNameFather;

	@Column(name="LAST_NAME_MOTHER")
	private String lastNameMother;

	@Column(name="MARITAL_STATUS")
	private String maritalStatus;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	private String name;

	private String nationality;

	private String notes;

	@Column(name="PATH_NAME")
	private String pathName;

	@Temporal(TemporalType.DATE)
	@Column(name="POP_ISSUE_DATE")
	private Date popIssueDate;

	@Column(name="POP_ISSUE_PLACE")
	private String popIssuePlace;

	@Column(name="POP_NO")
	private String popNo;

	@Column(name="POS_DNI")
	private String posDni;

	@Column(name="POS_DNI_FILE")
	private String posDniFile;

	@Column(name="PRE_DNI")
	private String preDni;

	@Column(name="PRE_DNI_FILE")
	private String preDniFile;

	private String precinct;

	private String province;

	@Temporal(TemporalType.DATE)
	@Column(name="REPRE_CUST_BIRTH_DATE")
	private Date repreCustBirthDate;

	@Temporal(TemporalType.DATE)
	@Column(name="REPRE_CUST_ID_EXPIRE_DATE")
	private Date repreCustIdExpireDate;

	@Temporal(TemporalType.DATE)
	@Column(name="REPRE_CUST_ID_ISSUE_DATE")
	private Date repreCustIdIssueDate;

	@Column(name="REPRE_CUST_ID_ISSUE_PLACE")
	private String repreCustIdIssuePlace;

	@Column(name="REPRE_CUST_ID_NO")
	private String repreCustIdNo;

	@Column(name="REPRE_CUST_ID_TYPE")
	private BigDecimal repreCustIdType;

	@Column(name="REPRE_CUST_NAME")
	private String repreCustName;

	@Column(name="REPRE_CUST_TEL_FAX")
	private String repreCustTelFax;

	private String sex;

	private BigDecimal status;

	private String street;

	@Column(name="STREET_BLOCK")
	private String streetBlock;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="TAX_TYPE")
	private String taxType;

	@Column(name="TEL_FAX")
	private String telFax;

	private String tin;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_TIME")
	private Date updatedTime;

	@Column(name="UPDATED_USER")
	private String updatedUser;

	private String vip;

	public Customer() {
	}

	public String getActivitiesField() {
		return this.activitiesField;
	}

	public void setActivitiesField(String activitiesField) {
		this.activitiesField = activitiesField;
	}

	public Date getAddedDate() {
		return this.addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getAddedUser() {
		return this.addedUser;
	}

	public void setAddedUser(String addedUser) {
		this.addedUser = addedUser;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBusPermitNo() {
		return this.busPermitNo;
	}

	public void setBusPermitNo(String busPermitNo) {
		this.busPermitNo = busPermitNo;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return this.contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public BigDecimal getCustTypeId() {
		return this.custTypeId;
	}

	public void setCustTypeId(BigDecimal custTypeId) {
		this.custTypeId = custTypeId;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getFinancialPoint() {
		return this.financialPoint;
	}

	public void setFinancialPoint(BigDecimal financialPoint) {
		this.financialPoint = financialPoint;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Date getIdExpireDate() {
		return this.idExpireDate;
	}

	public void setIdExpireDate(Date idExpireDate) {
		this.idExpireDate = idExpireDate;
	}

	public Date getIdIssueDate() {
		return this.idIssueDate;
	}

	public void setIdIssueDate(Date idIssueDate) {
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

	public BigDecimal getIdType() {
		return this.idType;
	}

	public void setIdType(BigDecimal idType) {
		this.idType = idType;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageFile() {
		return this.imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageSignatureName() {
		return this.imageSignatureName;
	}

	public void setImageSignatureName(String imageSignatureName) {
		this.imageSignatureName = imageSignatureName;
	}

	public String getLandlineNo() {
		return this.landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getLastNameFather() {
		return this.lastNameFather;
	}

	public void setLastNameFather(String lastNameFather) {
		this.lastNameFather = lastNameFather;
	}

	public String getLastNameMother() {
		return this.lastNameMother;
	}

	public void setLastNameMother(String lastNameMother) {
		this.lastNameMother = lastNameMother;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public Date getPopIssueDate() {
		return this.popIssueDate;
	}

	public void setPopIssueDate(Date popIssueDate) {
		this.popIssueDate = popIssueDate;
	}

	public String getPopIssuePlace() {
		return this.popIssuePlace;
	}

	public void setPopIssuePlace(String popIssuePlace) {
		this.popIssuePlace = popIssuePlace;
	}

	public String getPopNo() {
		return this.popNo;
	}

	public void setPopNo(String popNo) {
		this.popNo = popNo;
	}

	public String getPosDni() {
		return this.posDni;
	}

	public void setPosDni(String posDni) {
		this.posDni = posDni;
	}

	public String getPosDniFile() {
		return this.posDniFile;
	}

	public void setPosDniFile(String posDniFile) {
		this.posDniFile = posDniFile;
	}

	public String getPreDni() {
		return this.preDni;
	}

	public void setPreDni(String preDni) {
		this.preDni = preDni;
	}

	public String getPreDniFile() {
		return this.preDniFile;
	}

	public void setPreDniFile(String preDniFile) {
		this.preDniFile = preDniFile;
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

	public Date getRepreCustBirthDate() {
		return this.repreCustBirthDate;
	}

	public void setRepreCustBirthDate(Date repreCustBirthDate) {
		this.repreCustBirthDate = repreCustBirthDate;
	}

	public Date getRepreCustIdExpireDate() {
		return this.repreCustIdExpireDate;
	}

	public void setRepreCustIdExpireDate(Date repreCustIdExpireDate) {
		this.repreCustIdExpireDate = repreCustIdExpireDate;
	}

	public Date getRepreCustIdIssueDate() {
		return this.repreCustIdIssueDate;
	}

	public void setRepreCustIdIssueDate(Date repreCustIdIssueDate) {
		this.repreCustIdIssueDate = repreCustIdIssueDate;
	}

	public String getRepreCustIdIssuePlace() {
		return this.repreCustIdIssuePlace;
	}

	public void setRepreCustIdIssuePlace(String repreCustIdIssuePlace) {
		this.repreCustIdIssuePlace = repreCustIdIssuePlace;
	}

	public String getRepreCustIdNo() {
		return this.repreCustIdNo;
	}

	public void setRepreCustIdNo(String repreCustIdNo) {
		this.repreCustIdNo = repreCustIdNo;
	}

	public BigDecimal getRepreCustIdType() {
		return this.repreCustIdType;
	}

	public void setRepreCustIdType(BigDecimal repreCustIdType) {
		this.repreCustIdType = repreCustIdType;
	}

	public String getRepreCustName() {
		return this.repreCustName;
	}

	public void setRepreCustName(String repreCustName) {
		this.repreCustName = repreCustName;
	}

	public String getRepreCustTelFax() {
		return this.repreCustTelFax;
	}

	public void setRepreCustTelFax(String repreCustTelFax) {
		this.repreCustTelFax = repreCustTelFax;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetBlock() {
		return this.streetBlock;
	}

	public void setStreetBlock(String streetBlock) {
		this.streetBlock = streetBlock;
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

	public String getTaxType() {
		return this.taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

}