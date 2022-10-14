package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_CHARGE_REPORT_DETAIL_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_CHARGE_REPORT_DETAIL_TEMP")
@NamedQuery(name="BiChargeReportDetailTemp.findAll", query="SELECT b FROM BiChargeReportDetailTemp b")
public class BiChargeReportDetailTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CHARGE_REPORT_ID")
	private BigDecimal chargeReportId;

	@Column(name="COM_CHARGE")
	private BigDecimal comCharge;

	@Column(name="FEE_CHARGE")
	private BigDecimal feeCharge;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="NUM_CALL")
	private BigDecimal numCall;

	private BigDecimal tax;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public BiChargeReportDetailTemp() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public BigDecimal getChargeReportId() {
		return this.chargeReportId;
	}

	public void setChargeReportId(BigDecimal chargeReportId) {
		this.chargeReportId = chargeReportId;
	}

	public BigDecimal getComCharge() {
		return this.comCharge;
	}

	public void setComCharge(BigDecimal comCharge) {
		this.comCharge = comCharge;
	}

	public BigDecimal getFeeCharge() {
		return this.feeCharge;
	}

	public void setFeeCharge(BigDecimal feeCharge) {
		this.feeCharge = feeCharge;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getNumCall() {
		return this.numCall;
	}

	public void setNumCall(BigDecimal numCall) {
		this.numCall = numCall;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}