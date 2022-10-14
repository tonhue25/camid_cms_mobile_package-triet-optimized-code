package co.siten.myvtg.bean;

import java.util.Date;

import co.siten.myvtg.model.myvtg.DataPricePlanConfig;

public class ProvisionPricePlanBean {
	private Date expireDate;
	private String expiredDateStr;
	private DataPricePlanConfig config;

	public String getExpiredDateStr() {
		return expiredDateStr;
	}

	public void setExpiredDateStr(String expiredDateStr) {
		this.expiredDateStr = expiredDateStr;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public DataPricePlanConfig getConfig() {
		return config;
	}

	public void setConfig(DataPricePlanConfig config) {
		this.config = config;
	}

}
