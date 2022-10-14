package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_SUB_VPN database table.
 * 
 */
@Entity
@Table(name="MY_SUB_VPN")
@NamedQuery(name="MySubVpn.findAll", query="SELECT m FROM MySubVpn m")
public class MySubVpn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal id;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public MySubVpn() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}