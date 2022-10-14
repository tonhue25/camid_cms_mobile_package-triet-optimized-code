package co.siten.myvtg.bean;

public class HotNewsBean {
	private String newsId;
	private String advImgUrl;
	private Integer type;
	
	
	public HotNewsBean(String newsId, String advImgUrl, Integer type) {
		super();
		this.newsId = newsId;
		this.advImgUrl = advImgUrl;
		this.type = type;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getAdvImgUrl() {
		return advImgUrl;
	}
	public void setAdvImgUrl(String advImgUrl) {
		this.advImgUrl = advImgUrl;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
