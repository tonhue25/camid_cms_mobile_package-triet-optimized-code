package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the B1_SMS_COST database table.
 * 
 */
@Entity
@Table(name="B1_SMS_COST")
@NamedQuery(name="B1SmsCost.findAll", query="SELECT b FROM B1SmsCost b")
public class B1SmsCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public B1SmsCost() {
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}