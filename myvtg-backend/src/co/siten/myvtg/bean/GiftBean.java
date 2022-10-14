package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class GiftBean {
	private String name;
	private String code;
	private BigDecimal score;
	private String iconUrl;

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

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public GiftBean(String name, String code, BigDecimal score, String iconUrl) {
		super();
		this.name = name;
		this.code = code;
		this.score = score;
		this.iconUrl = iconUrl;
	}

}
