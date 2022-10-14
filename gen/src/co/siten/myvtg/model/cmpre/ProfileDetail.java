package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROFILE_DETAIL database table.
 * 
 */
@Entity
@Table(name="PROFILE_DETAIL")
@NamedQuery(name="ProfileDetail.findAll", query="SELECT p FROM ProfileDetail p")
public class ProfileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHECKED_BY_USER")
	private String checkedByUser;

	@Column(name="CHECKED_DATE")
	private Object checkedDate;

	private String description;

	@Column(name="MORE_INFO")
	private String moreInfo;

	@Column(name="PROFILE_ID")
	private BigDecimal profileId;

	@Column(name="PROFILE_TYPE")
	private String profileType;

	@Column(name="RECEIVED_BY_USER")
	private String receivedByUser;

	@Column(name="RECEIVED_DATE")
	private Object receivedDate;

	private String status;

	@Column(name="SUB_PROFILE_ID")
	private BigDecimal subProfileId;

	@Column(name="UPDATED_BY_USER")
	private String updatedByUser;

	@Column(name="UPDATED_TIME")
	private Object updatedTime;

	public ProfileDetail() {
	}

	public String getCheckedByUser() {
		return this.checkedByUser;
	}

	public void setCheckedByUser(String checkedByUser) {
		this.checkedByUser = checkedByUser;
	}

	public Object getCheckedDate() {
		return this.checkedDate;
	}

	public void setCheckedDate(Object checkedDate) {
		this.checkedDate = checkedDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMoreInfo() {
		return this.moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public BigDecimal getProfileId() {
		return this.profileId;
	}

	public void setProfileId(BigDecimal profileId) {
		this.profileId = profileId;
	}

	public String getProfileType() {
		return this.profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getReceivedByUser() {
		return this.receivedByUser;
	}

	public void setReceivedByUser(String receivedByUser) {
		this.receivedByUser = receivedByUser;
	}

	public Object getReceivedDate() {
		return this.receivedDate;
	}

	public void setReceivedDate(Object receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubProfileId() {
		return this.subProfileId;
	}

	public void setSubProfileId(BigDecimal subProfileId) {
		this.subProfileId = subProfileId;
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