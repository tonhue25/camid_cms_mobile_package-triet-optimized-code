package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_PROM_MAI_LINH_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_PROM_MAI_LINH_TEMP")
@NamedQuery(name="BiPromMaiLinhTemp.findAll", query="SELECT b FROM BiPromMaiLinhTemp b")
public class BiPromMaiLinhTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	@Column(name="MONTH_FEE")
	private BigDecimal monthFee;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public BiPromMaiLinhTemp() {
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

	public BigDecimal getMonthFee() {
		return this.monthFee;
	}

	public void setMonthFee(BigDecimal monthFee) {
		this.monthFee = monthFee;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}