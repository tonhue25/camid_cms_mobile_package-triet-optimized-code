package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_5VAS_MONTHLY_FEE database table.
 * 
 */
@Entity
@Table(name="MY_5VAS_MONTHLY_FEE")
@NamedQuery(name="My5vasMonthlyFee.findAll", query="SELECT m FROM My5vasMonthlyFee m")
public class My5vasMonthlyFee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public My5vasMonthlyFee() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}