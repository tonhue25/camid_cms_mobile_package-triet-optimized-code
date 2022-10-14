package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_SUB_DEPOSIT database table.
 * 
 */
@Entity
@Table(name="UD_SUB_DEPOSIT")
@NamedQuery(name="UdSubDeposit.findAll", query="SELECT u FROM UdSubDeposit u")
public class UdSubDeposit implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal deposit;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdSubDeposit() {
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}