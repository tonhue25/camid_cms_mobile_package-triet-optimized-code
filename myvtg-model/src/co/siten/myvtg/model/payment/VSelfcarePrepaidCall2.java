package co.siten.myvtg.model.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the V_SELFCARE_PREPAID_CALL_2 database table.
 * 
 */
@Entity
@Table(name = "V_SELFCARE_PREPAID_CALL_2")
@NamedQuery(name = "VSelfcarePrepaidCall2.findAll", query = "SELECT v FROM VSelfcarePrepaidCall2 v")
public class VSelfcarePrepaidCall2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLIED_CYCLE")
	private Date appliedCycle;

	@Column(name = "DEBIT_AMOUNT_TAX")
	private BigDecimal debitAmountTax;
	@Id
	private String isdn;
	@Column(name = "SUB_ID")
	private String subId;

	public VSelfcarePrepaidCall2() {
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getAppliedCycle() {
		return appliedCycle;
	}

	public void setAppliedCycle(Date appliedCycle) {
		this.appliedCycle = appliedCycle;
	}

	public BigDecimal getDebitAmountTax() {
		return debitAmountTax;
	}

	public void setDebitAmountTax(BigDecimal debitAmountTax) {
		this.debitAmountTax = debitAmountTax;
	}

	public String getIsdn() {
		return isdn;
	}

	

}