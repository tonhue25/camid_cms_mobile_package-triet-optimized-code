package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_RE_PAY_SUSPEND_CONTRACT database table.
 * 
 */
@Entity
@Table(name="UD_RE_PAY_SUSPEND_CONTRACT")
@NamedQuery(name="UdRePaySuspendContract.findAll", query="SELECT u FROM UdRePaySuspendContract u")
public class UdRePaySuspendContract implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

	public UdRePaySuspendContract() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

}