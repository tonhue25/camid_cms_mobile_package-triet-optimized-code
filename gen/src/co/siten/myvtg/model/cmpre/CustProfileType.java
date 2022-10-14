package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUST_PROFILE_TYPE database table.
 * 
 */
@Entity
@Table(name="CUST_PROFILE_TYPE")
@NamedQuery(name="CustProfileType.findAll", query="SELECT c FROM CustProfileType c")
public class CustProfileType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CUST_PROFILE_TYPE_ID")
	private BigDecimal custProfileTypeId;

	@Column(name="END_DATE")
	private Object endDate;

	@Column(name="PROFILE_TYPE")
	private String profileType;

	private BigDecimal required;

	@Column(name="START_DATE")
	private Object startDate;

	private BigDecimal status;

	@Column(name="UPDATED_BY_USER")
	private String updatedByUser;

	@Column(name="UPDATED_TIME")
	private Object updatedTime;

	public CustProfileType() {
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public BigDecimal getCustProfileTypeId() {
		return this.custProfileTypeId;
	}

	public void setCustProfileTypeId(BigDecimal custProfileTypeId) {
		this.custProfileTypeId = custProfileTypeId;
	}

	public Object getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}

	public String getProfileType() {
		return this.profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public BigDecimal getRequired() {
		return this.required;
	}

	public void setRequired(BigDecimal required) {
		this.required = required;
	}

	public Object getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Object startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
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