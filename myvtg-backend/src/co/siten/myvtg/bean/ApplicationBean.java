package co.siten.myvtg.bean;

public class ApplicationBean {
	private String id;
	private String name;
	private String shortDes;
        private String fullDes;
	private String iconUrl;
	private String iosLink;
	private String androidLink;

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

	public String getIosLink() {
		return iosLink;
	}

	public void setIosLink(String iosLink) {
		this.iosLink = iosLink;
	}

	public String getAndroidLink() {
		return androidLink;
	}

	public void setAndroidLink(String androidLink) {
		this.androidLink = androidLink;
	}

        public String getFullDes() {
                return fullDes;
        }

        public void setFullDes(String fullDes) {
                this.fullDes = fullDes;
        }

	public ApplicationBean(String id, String name, String shortDes, String fullDes, String iconUrl, String iosLink,
			String androidLink) {
		super();
		this.id = id;
		this.name = name;
		this.shortDes = shortDes;
                this.fullDes = fullDes;
		this.iconUrl = iconUrl;
		this.iosLink = iosLink;
		this.androidLink = androidLink;
	}

}
