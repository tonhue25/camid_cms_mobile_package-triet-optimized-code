package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ADVERT_BANNER database table.
 * 
 */
@Entity
@Table(name="ADVERT_BANNER")
@NamedQuery(name="AdvertBanner.findAll", query="SELECT a FROM AdvertBanner a")
public class AdvertBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ADV_IMG_URL")
	private String advImgUrl;

	private BigDecimal approved;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_TIME")
	private Timestamp createdTime;

	private String des;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="LAST_UPDATED_TIME")
	private Timestamp lastUpdatedTime;

	@Column(name="SHOW_PAGE")
	private BigDecimal showPage;

	@Column(name="SOURCE_LINK")
	private String sourceLink;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public AdvertBanner() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdvImgUrl() {
		return this.advImgUrl;
	}

	public void setAdvImgUrl(String advImgUrl) {
		this.advImgUrl = advImgUrl;
	}

	public BigDecimal getApproved() {
		return this.approved;
	}

	public void setApproved(BigDecimal approved) {
		this.approved = approved;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public BigDecimal getShowPage() {
		return this.showPage;
	}

	public void setShowPage(BigDecimal showPage) {
		this.showPage = showPage;
	}

	public String getSourceLink() {
		return this.sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}