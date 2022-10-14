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
 * The persistent class for the ADVERT_BANNER database table.
 * 
 */
@Entity
@Table(name = "ADVERT_BANNER")
@NamedQuery(name = "AdvertBanner.findAll", query = "SELECT a FROM AdvertBanner a")
public class AdvertBanner implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	@Column(name = "ADV_IMG_URL")
	private String advImgUrl;

	private Integer approved;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	private String des;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	@Column(name = "SHOW_PAGE")
	private Integer showPage;

	@Column(name = "SOURCE_LINK")
	private String sourceLink;

	private Integer status;

	@Column(name = "\"TYPE\"")
	private Integer type;

	public AdvertBanner() {
	}

	public String getId() {
		return id;
	}

	public String getAdvImgUrl() {
		return advImgUrl;
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

	public String getDes() {
		return des;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public Integer getShowPage() {
		return showPage;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAdvImgUrl(String advImgUrl) {
		this.advImgUrl = advImgUrl;
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

	public void setDes(String des) {
		this.des = des;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public void setShowPage(Integer showPage) {
		this.showPage = showPage;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
