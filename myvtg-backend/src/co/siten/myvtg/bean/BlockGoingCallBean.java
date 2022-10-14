package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class BlockGoingCallBean {
	private String imgDesUrl;
	private String name;
	private String fullDes;
	private BigDecimal price;
	private Integer registerState = 1;
	private Integer checkCondition = 1;
	private Integer lockState = 0;

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

	public Integer getLockState() {
		return lockState;
	}

	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}

	public BlockGoingCallBean(String imgDesUrl, String name, String fullDes, BigDecimal price) {
		super();
		this.imgDesUrl = imgDesUrl;
		this.name = name;
		this.fullDes = fullDes;
		this.price = price;
	}
	
}
