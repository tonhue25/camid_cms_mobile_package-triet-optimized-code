package co.siten.myvtg.model.sm;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the V_SELFCARE_STOCK_ISDN_PREP database table.
 * 
 */
@Entity
@Table(name = "V_SELFCARE_STOCK_ISDN_PREP")
@NamedQuery(name = "VSelfcareStockIsdnPrep.findAll", query = "SELECT v FROM VSelfcareStockIsdnPrep v")
public class VSelfcareStockIsdnPrep implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String isdn;

	private BigDecimal price;

	public VSelfcareStockIsdnPrep() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}