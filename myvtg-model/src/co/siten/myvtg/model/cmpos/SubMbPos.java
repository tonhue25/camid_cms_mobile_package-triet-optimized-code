package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;

import co.siten.myvtg.config.Config;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_MB database table.
 * 
 */
@Entity
@Table(name="SUB_MB")
public class SubMbPos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_CM_POS_SUB_MB, allocationSize = 1)
	@Column(name = "SUB_ID")
	private long subId;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="ADDRESS_CODE")
	private String addressCode;

	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

	@Column(name="CONTRACT_ID")
	private Long contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	private BigDecimal deposit;

	private String district;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FINISH_REASON_ID")
	private BigDecimal finishReasonId;

	private String home;

	private String imsi;

	private String isdn;
	private String nationality;

	@Column(name="NUM_RESET_ZONE")
	private BigDecimal numResetZone;

	private String password;

	@Column(name="PASSWORD_ATM")
	private String passwordAtm;

	private String precinct;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	private String province;

	@Column(name="\"QUOTA\"")
	private BigDecimal quota;

	@Column(name="REASON_DEPOSIT_ID")
	private BigDecimal reasonDepositId;

	@Column(name="REG_REASON_ID")
	private BigDecimal regReasonId;

	@Column(name="REG_TYPE")
	private String regType;

//	@Column(name="REMAIN_MONEY")
//	private String remainMoney;

	private String serial;

	private String sex;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	private Integer status;

	private String street;

	@Column(name="STREET_BLOCK")
	private String streetBlock;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_TITLE")
	private String userTitle;

	@Column(name="USER_USING")
	private String userUsing;

	private String vip;

	public SubMbPos() {
	}
	
	
	

	public SubMbPos(SubMbPos subMb) {
		super();
		this.subId = subMb.subId;
		this.actStatus = subMb.actStatus;
		this.address = subMb.address;
		this.addressCode = subMb.addressCode;
		this.birthDate = subMb.birthDate;
		this.changeDatetime = subMb.changeDatetime;
		this.contractId = subMb.contractId;
		this.createDate = subMb.createDate;
		this.custReqId = subMb.custReqId;
		this.deposit = subMb.deposit;
		this.district = subMb.district;
		this.endDatetime = subMb.endDatetime;
		this.finishReasonId = subMb.finishReasonId;
		this.home = subMb.home;
		this.imsi = subMb.imsi;
		this.isdn = subMb.isdn;
//		this.language = subMb.language;
		this.nationality = subMb.nationality;
		this.numResetZone = subMb.numResetZone;
		this.password = subMb.password;
		this.passwordAtm = subMb.passwordAtm;
		this.precinct = subMb.precinct;
		this.productCode = subMb.productCode;
		this.promotionCode = subMb.promotionCode;
		this.province = subMb.province;
		this.quota = subMb.quota;
		this.reasonDepositId = subMb.reasonDepositId;
		this.regReasonId = subMb.regReasonId;
		this.regType = subMb.regType;
//		this.remainMoney = subMb.remainMoney;
		this.serial = subMb.serial;
		this.sex = subMb.sex;
		this.shopCode = subMb.shopCode;
		this.staDatetime = subMb.staDatetime;
		this.staffId = subMb.staffId;
		this.status = subMb.status;
		this.street = subMb.street;
		this.streetBlock = subMb.streetBlock;
		this.streetBlockName = subMb.streetBlockName;
		this.streetName = subMb.streetName;
		this.subType = subMb.subType;
		this.userCreated = subMb.userCreated;
		this.userTitle = subMb.userTitle;
		this.userUsing = subMb.userUsing;
		this.vip = subMb.vip;
	}



	public long getSubId() {
		return this.subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressCode() {
		return this.addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCustReqId() {
		return this.custReqId;
	}

	public void setCustReqId(BigDecimal custReqId) {
		this.custReqId = custReqId;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getFinishReasonId() {
		return this.finishReasonId;
	}

	public void setFinishReasonId(BigDecimal finishReasonId) {
		this.finishReasonId = finishReasonId;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public BigDecimal getNumResetZone() {
		return this.numResetZone;
	}

	public void setNumResetZone(BigDecimal numResetZone) {
		this.numResetZone = numResetZone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAtm() {
		return this.passwordAtm;
	}

	public void setPasswordAtm(String passwordAtm) {
		this.passwordAtm = passwordAtm;
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

	public String getPromotionCode() {
		return this.promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public BigDecimal getQuota() {
		return this.quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public BigDecimal getReasonDepositId() {
		return this.reasonDepositId;
	}

	public void setReasonDepositId(BigDecimal reasonDepositId) {
		this.reasonDepositId = reasonDepositId;
	}

	public BigDecimal getRegReasonId() {
		return this.regReasonId;
	}

	public void setRegReasonId(BigDecimal regReasonId) {
		this.regReasonId = regReasonId;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

//	public String getRemainMoney() {
//		return this.remainMoney;
//	}
//
//	public void setRemainMoney(String remainMoney) {
//		this.remainMoney = remainMoney;
//	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
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

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
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

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getUserTitle() {
		return this.userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserUsing() {
		return this.userUsing;
	}

	public void setUserUsing(String userUsing) {
		this.userUsing = userUsing;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

}