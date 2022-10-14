package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class SubAccountInfoAccBean {
	private String name;
	private BigDecimal mainAcc;
	private BigDecimal proAcc;
	private String dataPkgName;
	private BigDecimal dataVolume;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMainAcc() {
		return mainAcc;
	}

	public void setMainAcc(BigDecimal mainAcc) {
		this.mainAcc = mainAcc;
	}

	public BigDecimal getProAcc() {
		return proAcc;
	}

	public void setProAcc(BigDecimal proAcc) {
		this.proAcc = proAcc;
	}

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
	
	

}
