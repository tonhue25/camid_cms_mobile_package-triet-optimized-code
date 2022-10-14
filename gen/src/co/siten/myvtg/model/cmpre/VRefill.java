package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the V_REFILL database table.
 * 
 */
@Entity
@Table(name="V_REFILL")
@NamedQuery(name="VRefill.findAll", query="SELECT v FROM VRefill v")
public class VRefill implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="REFILL_DATE")
	private Date refillDate;

	@Column(name="REFILL_ID")
	private String refillId;

	public VRefill() {
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

	public String getRefillId() {
		return this.refillId;
	}

	public void setRefillId(String refillId) {
		this.refillId = refillId;
	}

}