package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the HOT_NEWS database table.
 * 
 */
@Entity
@Table(name = "HOT_NEWS")
@NamedQuery(name = "HotNew.findAll", query = "SELECT h FROM HotNew h")
public class HotNew implements Serializable {
	
	@Transient
	public String oldId;
	
	@Transient
	public int oldType;
	
	private static final long serialVersionUID = 1L;
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
//	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	@Column(name = "AD_IMG_URL")
	private String adImgUrl;

	private Integer approved;

	@Column(name = "CREATED_BY")
	private String createdBy;

	
	@Temporal(TemporalType.DATE)
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME",columnDefinition = "DATETIME")
	private Date createdTime;

//	@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECT_TIME")
	private Date effectTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRED_TIME")
	private Date expiredTime;

	@Column(name = "\"LANGUAGE\"")
	private String language;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private Integer priority;

	@Column(name = "\"STATE\"")
	private Integer state;

	@Column(name = "\"STATUS\"")
	private Integer status;

	@Column(name = "\"TYPE\"")
	private Integer type;

	public HotNew() {
	}

	public String getOldId() {
		return this.oldId;
	}
	
	public void setOldId(String id) {
		this.oldId = id;
	}
	
	public int getOldType() {
		return this.oldType;
	}
	
	public void setOldType(int type) {
		this.oldType = type;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdImgUrl() {
		return this.adImgUrl;
	}

	public void setAdImgUrl(String adImgUrl) {
		this.adImgUrl = adImgUrl;
	}

	public Integer getApproved() {
		return approved;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public String getLanguage() {
		return language;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public Integer getPriority() {
		return priority;
	}

	public Integer getState() {
		return state;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getType() {
		return type;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}