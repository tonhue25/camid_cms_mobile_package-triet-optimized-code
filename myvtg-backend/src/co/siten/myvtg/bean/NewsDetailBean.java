package co.siten.myvtg.bean;

public class NewsDetailBean {
	private String id;
	private String name;
	private String imgDesUrl;
	private String content;

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

	public String getImgDesUrl() {
		return imgDesUrl;
	}

	public void setImgDesUrl(String imgDesUrl) {
		this.imgDesUrl = imgDesUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NewsDetailBean(String id, String name, String imgDesUrl, String content) {
		super();
		this.id = id;
		this.name = name;
		this.imgDesUrl = imgDesUrl;
		this.content = content;
	}

}
