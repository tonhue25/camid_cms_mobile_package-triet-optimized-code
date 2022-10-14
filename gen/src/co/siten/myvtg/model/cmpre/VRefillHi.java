package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the V_REFILL_HIS database table.
 * 
 */
@Entity
@Table(name="V_REFILL_HIS")
@NamedQuery(name="VRefillHi.findAll", query="SELECT v FROM VRefillHi v")
public class VRefillHi implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="REFILL_DATE")
	private Date refillDate;

	@Column(name="REFILL_TIME")
	private String refillTime;

	public VRefillHi() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getRefillDate() {
		return this.refillDate;
	}

	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	public String getRefillTime() {
		return this.refillTime;
	}

	public void setRefillTime(String refillTime) {
		this.refillTime = refillTime;
	}

}