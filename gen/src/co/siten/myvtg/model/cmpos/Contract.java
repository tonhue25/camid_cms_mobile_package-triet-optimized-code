package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CONTRACT database table.
 * 
 */
@Entity
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CONTRACT_ID")
	private long contractId;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	private String address;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="BILL_CYCLE_FROM_CHARGING")
	private String billCycleFromCharging;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_TITLE")
	private String contactTitle;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CONTRACT_TYPE_CODE")
	private String contractTypeCode;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATE")
	private Date dateCreate;

	private BigDecimal deporsit;

	private String district;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_DATE")
	private Date effectDate;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="FROM_PRICE")
	private BigDecimal fromPrice;

	private String home;

	@Column(name="ID_NO")
	private String idNo;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_TIME")
	private Date lastUpdateTime;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="NOTICE_CHARGE")
	private String noticeCharge;

	@Column(name="NUM_OF_SUBSCRIBERS")
	private BigDecimal numOfSubscribers;

	@Column(name="OLD_CUST_ID")
	private BigDecimal oldCustId;

	@Column(name="PAY_AREA_CODE")
	private String payAreaCode;

	@Column(name="PAY_METHOD_CODE")
	private String payMethodCode;

	private String payer;

	private String precinct;

	@Column(name="PRINT_METHOD_CODE")
	private String printMethodCode;

	private String province;

	private String reason;

	@Column(name="REG_TYPE")
	private BigDecimal regType;

	@Column(name="SERVICE_TYPES")
	private String serviceTypes;

	@Temporal(TemporalType.DATE)
	@Column(name="SIGN_DATE")
	private Date signDate;

	private BigDecimal status;

	private String street;

	@Column(name="STREET_BLOCK")
	private String streetBlock;

	@Column(name="STREET_BLOCK_NAME")
	private String streetBlockName;

	@Column(name="STREET_NAME")
	private String streetName;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TEL_FAX")
	private String telFax;

	@Column(name="TO_PRICE")
	private BigDecimal toPrice;

	@Column(name="USER_CREATE")
	private String userCreate;

	public Contract() {
	}

	public long getContractId() {
		return this.contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public String getBillCycleFromCharging() {
		return this.billCycleFromCharging;
	}

	public void setBillCycleFromCharging(String billCycleFromCharging) {
		this.billCycleFromCharging = billCycleFromCharging;
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

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractTypeCode() {
		return this.contractTypeCode;
	}

	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public Date getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public BigDecimal getDeporsit() {
		return this.deporsit;
	}

	public void setDeporsit(BigDecimal deporsit) {
		this.deporsit = deporsit;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Date getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getFromPrice() {
		return this.fromPrice;
	}

	public void setFromPrice(BigDecimal fromPrice) {
		this.fromPrice = fromPrice;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getNoticeCharge() {
		return this.noticeCharge;
	}

	public void setNoticeCharge(String noticeCharge) {
		this.noticeCharge = noticeCharge;
	}

	public BigDecimal getNumOfSubscribers() {
		return this.numOfSubscribers;
	}

	public void setNumOfSubscribers(BigDecimal numOfSubscribers) {
		this.numOfSubscribers = numOfSubscribers;
	}

	public BigDecimal getOldCustId() {
		return this.oldCustId;
	}

	public void setOldCustId(BigDecimal oldCustId) {
		this.oldCustId = oldCustId;
	}

	public String getPayAreaCode() {
		return this.payAreaCode;
	}

	public void setPayAreaCode(String payAreaCode) {
		this.payAreaCode = payAreaCode;
	}

	public String getPayMethodCode() {
		return this.payMethodCode;
	}

	public void setPayMethodCode(String payMethodCode) {
		this.payMethodCode = payMethodCode;
	}

	public String getPayer() {
		return this.payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	public String getPrintMethodCode() {
		return this.printMethodCode;
	}

	public void setPrintMethodCode(String printMethodCode) {
		this.printMethodCode = printMethodCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getRegType() {
		return this.regType;
	}

	public void setRegType(BigDecimal regType) {
		this.regType = regType;
	}

	public String getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(String serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
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

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

	public BigDecimal getToPrice() {
		return this.toPrice;
	}

	public void setToPrice(BigDecimal toPrice) {
		this.toPrice = toPrice;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}