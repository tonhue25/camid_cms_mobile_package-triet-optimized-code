package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_CONGTIEN_PRICE_PLAN_CP database table.
 * 
 */
@Entity
@Table(name="UD_CONGTIEN_PRICE_PLAN_CP")
@NamedQuery(name="UdCongtienPricePlanCp.findAll", query="SELECT u FROM UdCongtienPricePlanCp u")
public class UdCongtienPricePlanCp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	private BigDecimal charge;

	public UdCongtienPricePlanCp() {
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

}