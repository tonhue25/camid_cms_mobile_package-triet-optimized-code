package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_PROM_BIRTHDAY_VANG_BAC_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_PROM_BIRTHDAY_VANG_BAC_TEMP")
@NamedQuery(name="BiPromBirthdayVangBacTemp.findAll", query="SELECT b FROM BiPromBirthdayVangBacTemp b")
public class BiPromBirthdayVangBacTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public BiPromBirthdayVangBacTemp() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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