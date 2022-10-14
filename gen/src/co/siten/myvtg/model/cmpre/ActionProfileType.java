package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTION_PROFILE_TYPE database table.
 * 
 */
@Entity
@Table(name="ACTION_PROFILE_TYPE")
@NamedQuery(name="ActionProfileType.findAll", query="SELECT a FROM ActionProfileType a")
public class ActionProfileType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_ID")
	private BigDecimal actionId;

	@Column(name="ACTION_PROFILE_TYPE_ID")
	private BigDecimal actionProfileTypeId;

	@Column(name="END_DATE")
	private Object endDate;

	@Column(name="PROFILE_TYPE")
	private String profileType;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	private String required;

	@Column(name="START_DATE")
	private Object startDate;

	private BigDecimal status;

	@Column(name="UPDATED_BY_USER")
	private String updatedByUser;

	@Column(name="UPDATED_TIME")
	private Object updatedTime;

	public ActionProfileType() {
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

	public BigDecimal getActionProfileTypeId() {
		return this.actionProfileTypeId;
	}

	public void setActionProfileTypeId(BigDecimal actionProfileTypeId) {
		this.actionProfileTypeId = actionProfileTypeId;
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

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
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