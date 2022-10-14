package co.siten.myvtg.bean;

import java.math.BigDecimal;

import co.siten.myvtg.model.myvtg.Subscriber;

public class ServiceInfoBean {
	private String imgDesUrl;
	private String name;
	private String fullDes;
	private BigDecimal price;
	private Integer registerState = 1;
	private Integer checkCondition = 1;
	private Integer lockState = null;

	public Integer getLockState() {
		return lockState;
	}

	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}

	public ServiceInfoBean(String imgDesUrl, String name, String fullDes, BigDecimal price) {
		super();
		this.imgDesUrl = imgDesUrl;
		this.name = name;
		this.fullDes = fullDes;
		this.price = price;
	}

	public ServiceInfoBean(String imgDesUrl, String name, String fullDes, BigDecimal price, Subscriber registerState) {
		super();
		this.imgDesUrl = imgDesUrl;
		this.name = name;
		this.fullDes = fullDes;
		this.price = price;
		if (registerState != null)
			this.registerState = registerState.getState();
		else {
			this.registerState = 0;
		}
	}

	public String getImgDesUrl() {
		return imgDesUrl;
	}

	public void setImgDesUrl(String imgDesUrl) {
		this.imgDesUrl = imgDesUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullDes() {
		return fullDes;
	}

	public void setFullDes(String fullDes) {
		this.fullDes = fullDes;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getRegisterState() {
		return registerState;
	}

	public void setRegisterState(Integer registerState) {
		this.registerState = registerState;
	}

	public Integer getCheckCondition() {
		return checkCondition;
	}

	public void setCheckCondition(Integer checkCondition) {
		this.checkCondition = checkCondition;
	}

}
