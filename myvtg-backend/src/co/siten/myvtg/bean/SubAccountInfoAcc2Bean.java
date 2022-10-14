package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class SubAccountInfoAcc2Bean {
	private String name;
	private BigDecimal prePost;
	private BigDecimal debPost;
	private String dataPkgName;
	private BigDecimal dataVolume;

	public String getDataPkgName() {
		return dataPkgName;
	}

	public void setDataPkgName(String dataPkgName) {
		this.dataPkgName = dataPkgName;
	}

	public BigDecimal getDataVolume() {
		return dataVolume;
	}

	public void setDataVolume(BigDecimal dataVolume) {
		this.dataVolume = dataVolume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrePost() {
		return prePost;
	}

	public void setPrePost(BigDecimal prePost) {
		this.prePost = prePost;
	}

	public BigDecimal getDebPost() {
		return debPost;
	}

	public void setDebPost(BigDecimal debPost) {
		this.debPost = debPost;
	}

}
