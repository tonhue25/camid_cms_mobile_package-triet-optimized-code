package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PARENT_CHILD database table.
 * 
 */
@Entity
@Table(name="PARENT_CHILD")
@NamedQuery(name="ParentChild.findAll", query="SELECT p FROM ParentChild p")
public class ParentChild implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BIRTH_DATE")
	private Object birthDate;

	@Column(name="CHILD_NICKNAME")
	private String childNickname;

	@Column(name="CHILD_SUB_ID")
	private BigDecimal childSubId;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="PARENT_NICKNAME")
	private String parentNickname;

	@Column(name="PARENT_SUB_ID")
	private BigDecimal parentSubId;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="USER_NAME")
	private String userName;

	public ParentChild() {
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public String getChildNickname() {
		return this.childNickname;
	}

	public void setChildNickname(String childNickname) {
		this.childNickname = childNickname;
	}

	public BigDecimal getChildSubId() {
		return this.childSubId;
	}

	public void setChildSubId(BigDecimal childSubId) {
		this.childSubId = childSubId;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getParentNickname() {
		return this.parentNickname;
	}

	public void setParentNickname(String parentNickname) {
		this.parentNickname = parentNickname;
	}

	public BigDecimal getParentSubId() {
		return this.parentSubId;
	}

	public void setParentSubId(BigDecimal parentSubId) {
		this.parentSubId = parentSubId;
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

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}