package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "PROFILE" database table.
 * 
 */
@Entity
@Table(name="\"PROFILE\"")
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_ID")
	private BigDecimal actionId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CREATED_DATE")
	private Object createdDate;

	@Column(name="CREATED_USER")
	private String createdUser;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="OBJECT_TYPE")
	private BigDecimal objectType;

	@Column(name="PROFILE_ID")
	private BigDecimal profileId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="UPDATED_BY_USER")
	private String updatedByUser;

	@Column(name="UPDATED_TIME")
	private Object updatedTime;

	public Profile() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public BigDecimal getActionId() {
		return this.actionId;
	}

	public void setActionId(BigDecimal actionId) {
		this.actionId = actionId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Object getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Object createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public BigDecimal getObjectType() {
		return this.objectType;
	}

	public void setObjectType(BigDecimal objectType) {
		this.objectType = objectType;
	}

	public BigDecimal getProfileId() {
		return this.profileId;
	}

	public void setProfileId(BigDecimal profileId) {
		this.profileId = profileId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUpdatedByUser() {
		return this.updatedByUser;
	}

	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public Object getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Object updatedTime) {
		this.updatedTime = updatedTime;
	}

}