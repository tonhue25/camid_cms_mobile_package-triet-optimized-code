package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_DIFFERENCE database table.
 * 
 */
@Entity
@Table(name="MY_DIFFERENCE")
@NamedQuery(name="MyDifference.findAll", query="SELECT m FROM MyDifference m")
public class MyDifference implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal difference;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public MyDifference() {
	}

	public BigDecimal getDifference() {
		return this.difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}