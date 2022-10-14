package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the V_ACTIVE_REFILL_200 database table.
 * 
 */
@Entity
@Table(name="V_ACTIVE_REFILL_200")
@NamedQuery(name="VActiveRefill200.findAll", query="SELECT v FROM VActiveRefill200 v")
public class VActiveRefill200 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"Channel name\"")
	private String channel_name;

	@Column(name="\"Total activated(Refill >=200)\"")
	private BigDecimal total_activated_Refill___200_;

	public VActiveRefill200() {
	}

	public String getChannel_name() {
		return this.channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public BigDecimal getTotal_activated_Refill___200_() {
		return this.total_activated_Refill___200_;
	}

	public void setTotal_activated_Refill___200_(BigDecimal total_activated_Refill___200_) {
		this.total_activated_Refill___200_ = total_activated_Refill___200_;
	}

}