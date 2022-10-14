package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the STAFF database table.
 * 
 */
@Entity
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	@Column(name="AGENT_TYPE")
	private BigDecimal agentType;

	private Object birthday;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="DISCOUNT_POLICY")
	private String discountPolicy;

	private String email;

	@Column(name="ID_ISSUE_DATE")
	private Object idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	@Column(name="ID_NO")
	private String idNo;

	private String isdn;

	private String name;

	private String pin;

	@Column(name="PRICE_POLICY")
	private String pricePolicy;

	private String province;

	private String serial;

	@Column(name="SHOP_ID")
	private BigDecimal shopId;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	@Column(name="STAFF_OWN_TYPE")
	private String staffOwnType;

	@Column(name="STAFF_OWNER_ID")
	private BigDecimal staffOwnerId;

	private BigDecimal status;

	private String tel;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public Staff() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAgentType() {
		return this.agentType;
	}

	public void setAgentType(BigDecimal agentType) {
		this.agentType = agentType;
	}

	public Object getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Object birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getDiscountPolicy() {
		return this.discountPolicy;
	}

	public void setDiscountPolicy(String discountPolicy) {
		this.discountPolicy = discountPolicy;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPricePolicy() {
		return this.pricePolicy;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public BigDecimal getShopId() {
		return this.shopId;
	}

	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getStaffOwnType() {
		return this.staffOwnType;
	}

	public void setStaffOwnType(String staffOwnType) {
		this.staffOwnType = staffOwnType;
	}

	public BigDecimal getStaffOwnerId() {
		return this.staffOwnerId;
	}

	public void setStaffOwnerId(BigDecimal staffOwnerId) {
		this.staffOwnerId = staffOwnerId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}