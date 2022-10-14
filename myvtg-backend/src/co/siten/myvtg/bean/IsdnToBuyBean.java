package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class IsdnToBuyBean {
	private String isdn;
	private BigDecimal price;
	
	

	public IsdnToBuyBean(String isdn, BigDecimal price) {
		super();
		this.isdn = isdn;
		this.price = price;
	}

	public String getIsdn() {
		return isdn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
