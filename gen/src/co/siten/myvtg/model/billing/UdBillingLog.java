package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_BILLING_LOG database table.
 * 
 */
@Entity
@Table(name="UD_BILLING_LOG")
@NamedQuery(name="UdBillingLog.findAll", query="SELECT u FROM UdBillingLog u")
public class UdBillingLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CURR_DATE")
	private Date currDate;

	private String description;

	@Column(name="UD_NAME")
	private String udName;

	public UdBillingLog() {
	}

	public Date getCurrDate() {
		return this.currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUdName() {
		return this.udName;
	}

	public void setUdName(String udName) {
		this.udName = udName;
	}

}