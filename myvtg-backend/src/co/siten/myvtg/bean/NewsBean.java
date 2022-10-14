package co.siten.myvtg.bean;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NewsBean {
	private String id;
	private String name;
	private String iconUrl;
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;
	public NewsBean(String id, String name, String iconUrl, Date publishDate) {
		super();
		this.id = id;
		this.name = name;
		this.iconUrl = iconUrl;
		this.publishDate = publishDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
