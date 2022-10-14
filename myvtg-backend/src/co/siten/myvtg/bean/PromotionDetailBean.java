package co.siten.myvtg.bean;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PromotionDetailBean {
	private String name;
	private String code;
	private String fullDes;
	private String imgDesUrl;
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;
	private Integer actionType;
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
	public String getFullDes() {
		return fullDes;
	}
	public void setFullDes(String fullDes) {
		this.fullDes = fullDes;
	}
	public String getImgDesUrl() {
		return imgDesUrl;
	}
	public void setImgDesUrl(String imgDesUrl) {
		this.imgDesUrl = imgDesUrl;
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
	public PromotionDetailBean(String name, String code, String fullDes, String imgDesUrl, Date publishDate,
			Integer actionType) {
		super();
		this.name = name;
		this.code = code;
		this.fullDes = fullDes;
		this.imgDesUrl = imgDesUrl;
		this.publishDate = publishDate;
		this.actionType = actionType;
	}
	
	
}
