package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the EXPERIENCE_LINK database table.
 * 
 */
@Entity
@Table(name = "EXPERIENCE_LINK")
@NamedQuery(name = "ExperienceLink.findAll", query = "SELECT e FROM ExperienceLink e")
public class ExperienceLink implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

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

	@Column(name = "\"LANGUAGE\"")
	private String language;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private String name;

	private BigDecimal price;

	@Column(name = "SHORT_DES")
	private String shortDes;

	@Column(name = "SOURCE_LINK")
	private String sourceLink;

	private Integer status;

	public ExperienceLink() {
	}

	public String getId() {
		return id;
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

	public BigDecimal getPrice() {
		return price;
	}

	public String getShortDes() {
		return shortDes;
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

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}