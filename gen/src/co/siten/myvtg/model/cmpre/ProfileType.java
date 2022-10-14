package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROFILE_TYPE database table.
 * 
 */
@Entity
@Table(name="PROFILE_TYPE")
@NamedQuery(name="ProfileType.findAll", query="SELECT p FROM ProfileType p")
public class ProfileType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	@Column(name="PROFILE_TYPE")
	private String profileType;

	@Column(name="SHORT_NAME")
	private String shortName;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="UPDATED_BY_USER")
	private String updatedByUser;

	@Column(name="UPDATED_TIME")
	private Object updatedTime;

	public ProfileType() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileType() {
		return this.profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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