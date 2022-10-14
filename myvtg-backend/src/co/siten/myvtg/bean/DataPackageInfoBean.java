package co.siten.myvtg.bean;

import co.siten.myvtg.model.myvtg.Subscriber;

public class DataPackageInfoBean {
	private String name;
	private String code;
	private String fullDes;
	private String imgDesUrl;
	private Integer regState;
        private Integer enableAct;
	
	
	public DataPackageInfoBean(String name, String code, String fullDes, String imgDesUrl, Integer regState, Integer enableAct) {
		super();
		this.name = name;
		this.code = code;
		this.fullDes = fullDes;
		this.imgDesUrl = imgDesUrl;
		this.regState = regState;
                this.enableAct = enableAct;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getFullDes() {
		return fullDes;
	}
	public String getImgDesUrl() {
		return imgDesUrl;
	}
	public Integer getRegState() {
		return regState;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setFullDes(String fullDes) {
		this.fullDes = fullDes;
	}
	public void setImgDesUrl(String imgDesUrl) {
		this.imgDesUrl = imgDesUrl;
	}
	public void setRegState(Integer regState) {
		this.regState = regState;
	}

        public Integer getEnableAct() {
                return enableAct;
        }

        public void setEnableAct(Integer enableAct) {
                this.enableAct = enableAct;
        }
}
