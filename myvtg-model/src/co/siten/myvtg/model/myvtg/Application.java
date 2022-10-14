package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the APPLICATION database table.
 * 
 */
@Entity
@NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	@Column(name = "ANDROID_LINK")
	private String androidLink;

	private Integer approved;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "FULL_DES")
	private String fullDes;

	@Column(name = "ICON_URL")
	private String iconUrl;

	@Column(name = "IOS_LINK")
	private String iosLink;

	@Column(name = "\"LANGUAGE\"")
	private String language;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private String name;

	@Column(name = "SHORT_DES")
	private String shortDes;

	private Integer status;

	public Application() {
	}

	public String getId() {
		return id;
	}

	public String getAndroidLink() {
		return androidLink;
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

	public String getFullDes() {
		return fullDes;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getIosLink() {
		return iosLink;
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

	public String getName() {
		return name;
	}

	public String getShortDes() {
		return shortDes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAndroidLink(String androidLink) {
		this.androidLink = androidLink;
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

	public void setFullDes(String fullDes) {
		this.fullDes = fullDes;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public void setIosLink(String iosLink) {
		this.iosLink = iosLink;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}