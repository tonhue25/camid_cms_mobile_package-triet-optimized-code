package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_SELFCARE_PREPAID_CALL_1 database table.
 * 
 */
@Entity
@Table(name="V_SELFCARE_PREPAID_CALL_1")
@NamedQuery(name="VSelfcarePrepaidCall1.findAll", query="SELECT v FROM VSelfcarePrepaidCall1 v")
public class VSelfcarePrepaidCall1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	private String isdn;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public VSelfcarePrepaidCall1() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}