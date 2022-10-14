package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_SUB_DEPOSIT_HP database table.
 * 
 */
@Entity
@Table(name="UD_SUB_DEPOSIT_HP")
@NamedQuery(name="UdSubDepositHp.findAll", query="SELECT u FROM UdSubDepositHp u")
public class UdSubDepositHp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal deposit;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdSubDepositHp() {
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