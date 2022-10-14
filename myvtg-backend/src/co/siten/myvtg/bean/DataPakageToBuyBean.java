package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class DataPakageToBuyBean {
	private BigDecimal volume;
	private BigDecimal price;
	
	

	public DataPakageToBuyBean() {
		super();
	}
	
	

	public DataPakageToBuyBean(BigDecimal volume, BigDecimal price) {
		super();
		this.volume = volume;
		this.price = price;
	}



	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}
