package co.siten.myvtg.bean;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PromotionBean {
	private String name;
	private String code;
	private String shortDes;
	private String iconUrl;
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;
	private Integer actionType;
	public PromotionBean(String name, String code, String shortDes, String iconUrl, Date publishDate,
			Integer actionType) {
		super();
		this.name = name;
		this.code = code;
		this.shortDes = shortDes;
		this.iconUrl = iconUrl;
		this.publishDate = publishDate;
		this.actionType = actionType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShortDes() {
		return shortDes;
	}
	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
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
	public Integer getActionType() {
		return actionType;
	}
	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}
	
}
