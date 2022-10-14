package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the NEWS database table.
 * 
 */
@Entity
@Table(name = "NEWS")
@NamedQuery(name = "New.findAll", query = "SELECT n FROM New n")
public class New implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	private Integer approved;

	private String content;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECTIVE_TIME")
	private Date effectiveTime;
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRED_TIME")
	private Date expiredTime;

	@Column(name = "ICON_URL")
	private String iconUrl;

	@Column(name = "IMG_DES_URL")
	private String imgDesUrl;

	@Column(name = "\"LANGUAGE\"")
	private String language;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PUBLISHED_TIME")
	private Date publishedTime;

	@Column(name = "SOURCE_LINK")
	private String sourceLink;

	private Integer status;

	public New() {
	}

	public String getId() {
		return id;
	}

	public Integer getApproved() {
		return approved;
	}

	public String getContent() {
		return content;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getImgDesUrl() {
		return imgDesUrl;
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

	public Date getPublishedTime() {
		return publishedTime;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public void setImgDesUrl(String imgDesUrl) {
		this.imgDesUrl = imgDesUrl;
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

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}