package co.siten.myvtg.bean;

public class ExperienceLinkBean {
	private String name;
	private String shortDes;
	private String sourceLink;
	private String iconUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public ExperienceLinkBean(String name, String shortDes, String sourceLink, String iconUrl) {
		super();
		this.name = name;
		this.shortDes = shortDes;
		this.sourceLink = sourceLink;
		this.iconUrl = iconUrl;
	}

}
