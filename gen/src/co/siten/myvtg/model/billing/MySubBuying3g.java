package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the MY_SUB_BUYING3G database table.
 * 
 */
@Entity
@Table(name="MY_SUB_BUYING3G")
@NamedQuery(name="MySubBuying3g.findAll", query="SELECT m FROM MySubBuying3g m")
public class MySubBuying3g implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="MONTH_REG")
	private Date monthReg;

	public MySubBuying3g() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getMonthReg() {
		return this.monthReg;
	}

	public void setMonthReg(Date monthReg) {
		this.monthReg = monthReg;
	}

}