package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubServiceDetailBean {
	private String name;
	private String code;
	private String shortDes;
	private String fullDes;
	private String iconUrl;
	private String price;
	@JsonIgnore
	private String unit;
	private String regisState;
	private Integer autoRenew;

	public Integer getAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(Integer autoRenew) {
		this.autoRenew = autoRenew;
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

	public String getFullDes() {
		return fullDes;
	}

	public void setFullDes(String fullDes) {
		this.fullDes = fullDes;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRegisState() {
		return regisState;
	}

	public void setRegisState(String regisState) {
		this.regisState = regisState;
	}

	public SubServiceDetailBean(String name, String code, String shortDes, String fullDes, String iconUrl, String price,
								String unit, String regisState) {
		super();
		this.name = name;
		this.code = code;
		this.shortDes = shortDes;
		this.fullDes = fullDes;
		this.iconUrl = iconUrl;
		this.price = price;
		this.unit = unit;
		this.regisState = regisState;
	}

	public SubServiceDetailBean(String name, String code, String shortDes, String fullDes, String iconUrl, String price,
								String unit, String regisState, Integer autoRenew) {
		super();
		this.name = name;
		this.code = code;
		this.shortDes = shortDes;
		this.fullDes = fullDes;
		this.iconUrl = iconUrl;
		this.price = price;
		this.unit = unit;
		this.regisState = regisState;
		this.autoRenew = autoRenew;
	}

}
