package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SUB database table.
 * 
 */
@Entity
@NamedQuery(name="Sub.findAll", query="SELECT s FROM Sub s")
public class Sub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="AVATAR_URL")
	private String avatarUrl;

	@Column(name="BIRTH_DATE")
	private Timestamp birthDate;

	@Column(name="DEVICE_ID")
	private Object deviceId;

	@Column(name="DEVICE_NAME")
	private String deviceName;

	private String email;

	private BigDecimal gender;

	@Column(name="HOBBY_ID")
	private BigDecimal hobbyId;

	private String isdn;

	@Column(name="JOB_ID")
	private BigDecimal jobId;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_ACTIVE_TIME")
	private Timestamp lastActiveTime;

	@Column(name="LAST_SYNC_TIME")
	private Timestamp lastSyncTime;

	private String name;

	@Column(name="OS_TYPE")
	private BigDecimal osType;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="REGISTER_TIME")
	private Timestamp registerTime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private BigDecimal subType;

	public Sub() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public Object getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Object deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getGender() {
		return this.gender;
	}

	public void setGender(BigDecimal gender) {
		this.gender = gender;
	}

	public BigDecimal getHobbyId() {
		return this.hobbyId;
	}

	public void setHobbyId(BigDecimal hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getJobId() {
		return this.jobId;
	}

	public void setJobId(BigDecimal jobId) {
		this.jobId = jobId;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Timestamp getLastActiveTime() {
		return this.lastActiveTime;
	}

	public void setLastActiveTime(Timestamp lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public Timestamp getLastSyncTime() {
		return this.lastSyncTime;
	}

	public void setLastSyncTime(Timestamp lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOsType() {
		return this.osType;
	}

	public void setOsType(BigDecimal osType) {
		this.osType = osType;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
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

	public BigDecimal getSubType() {
		return this.subType;
	}

	public void setSubType(BigDecimal subType) {
		this.subType = subType;
	}

}