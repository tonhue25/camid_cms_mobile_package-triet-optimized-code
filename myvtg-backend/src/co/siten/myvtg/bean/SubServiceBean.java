package co.siten.myvtg.bean;

public class SubServiceBean {
	private String name;
	private String code;
	private String des;
	private String iconUrl;
	

	public SubServiceBean(String name, String code, String des, String iconUrl) {
		super();
		this.name = name;
		this.code = code;
		this.des = des;
		this.iconUrl= iconUrl;
	}

	
	public String getIconUrl() {
		return iconUrl;
	}


	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
