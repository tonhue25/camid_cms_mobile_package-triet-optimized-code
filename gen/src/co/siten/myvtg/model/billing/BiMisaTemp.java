package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_MISA_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_MISA_TEMP")
@NamedQuery(name="BiMisaTemp.findAll", query="SELECT b FROM BiMisaTemp b")
public class BiMisaTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"MAIN\"")
	private String main;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public BiMisaTemp() {
	}

	public String getMain() {
		return this.main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}