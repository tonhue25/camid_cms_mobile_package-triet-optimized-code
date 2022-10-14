package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the B1_HNS_GPRS database table.
 * 
 */
@Entity
@Table(name="B1_HNS_GPRS")
@NamedQuery(name="B1HnsGpr.findAll", query="SELECT b FROM B1HnsGpr b")
public class B1HnsGpr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public B1HnsGpr() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}