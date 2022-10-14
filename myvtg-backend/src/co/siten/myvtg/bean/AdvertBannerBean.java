package co.siten.myvtg.bean;

public class AdvertBannerBean {
	private String adImgUrl;
	private String sourceLink;

	public AdvertBannerBean(String adImgUrl, String sourceLink) {
		super();
		this.adImgUrl = adImgUrl;
		this.sourceLink = sourceLink;
	}

	public String getAdImgUrl() {
		return adImgUrl;
	}

	public void setAdImgUrl(String adImgUrl) {
		this.adImgUrl = adImgUrl;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

}
