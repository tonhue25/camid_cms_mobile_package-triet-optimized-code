package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CURR_BILL_CYCLE database table.
 * 
 */
@Entity
@Table(name="CURR_BILL_CYCLE")
@NamedQuery(name="CurrBillCycle.findAll", query="SELECT c FROM CurrBillCycle c")
public class CurrBillCycle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CHARGING_CODE")
	private String chargingCode;

	@Column(name="CURR_BILL_CYCLE")
	private Object currBillCycle;

	@Column(name="DATE_BARRING_ADDITION1")
	private BigDecimal dateBarringAddition1;

	@Column(name="DATE_BARRING_ADDITION2")
	private BigDecimal dateBarringAddition2;

	@Column(name="DATE_BARRING1")
	private BigDecimal dateBarring1;

	@Column(name="DATE_BARRING2")
	private BigDecimal dateBarring2;

	public CurrBillCycle() {
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public String getChargingCode() {
		return this.chargingCode;
	}

	public void setChargingCode(String chargingCode) {
		this.chargingCode = chargingCode;
	}

	public Object getCurrBillCycle() {
		return this.currBillCycle;
	}

	public void setCurrBillCycle(Object currBillCycle) {
		this.currBillCycle = currBillCycle;
	}

	public BigDecimal getDateBarringAddition1() {
		return this.dateBarringAddition1;
	}

	public void setDateBarringAddition1(BigDecimal dateBarringAddition1) {
		this.dateBarringAddition1 = dateBarringAddition1;
	}

	public BigDecimal getDateBarringAddition2() {
		return this.dateBarringAddition2;
	}

	public void setDateBarringAddition2(BigDecimal dateBarringAddition2) {
		this.dateBarringAddition2 = dateBarringAddition2;
	}

	public BigDecimal getDateBarring1() {
		return this.dateBarring1;
	}

	public void setDateBarring1(BigDecimal dateBarring1) {
		this.dateBarring1 = dateBarring1;
	}

	public BigDecimal getDateBarring2() {
		return this.dateBarring2;
	}

	public void setDateBarring2(BigDecimal dateBarring2) {
		this.dateBarring2 = dateBarring2;
	}

}