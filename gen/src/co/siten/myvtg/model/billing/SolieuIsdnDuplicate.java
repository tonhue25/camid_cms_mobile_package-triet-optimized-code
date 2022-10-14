package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SOLIEU_ISDN_DUPLICATE database table.
 * 
 */
@Entity
@Table(name="SOLIEU_ISDN_DUPLICATE")
@NamedQuery(name="SolieuIsdnDuplicate.findAll", query="SELECT s FROM SolieuIsdnDuplicate s")
public class SolieuIsdnDuplicate implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	private BigDecimal sl;

	public SolieuIsdnDuplicate() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getSl() {
		return this.sl;
	}

	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}

}