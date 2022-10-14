package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CHARGE_DETAIL database table.
 * 
 */
@Entity
@Table(name="CHARGE_DETAIL")
@NamedQuery(name="ChargeDetail.findAll", query="SELECT c FROM ChargeDetail c")
public class ChargeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BILL_CYCLE")
	private BigDecimal billCycle;

	@Column(name="CHARGE_REPORT_ID")
	private BigDecimal chargeReportId;

	@Column(name="DIS_AMOUNT")
	private BigDecimal disAmount;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"MONTH\"")
	private Date month;

	@Column(name="NUM_CALL")
	private BigDecimal numCall;

	@Column(name="SERVICE_TYPE")
	private BigDecimal serviceType;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public ChargeDetail() {
	}

	public BigDecimal getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(BigDecimal billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getChargeReportId() {
		return this.chargeReportId;
	}

	public void setChargeReportId(BigDecimal chargeReportId) {
		this.chargeReportId = chargeReportId;
	}

	public BigDecimal getDisAmount() {
		return this.disAmount;
	}

	public void setDisAmount(BigDecimal disAmount) {
		this.disAmount = disAmount;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public BigDecimal getNumCall() {
		return this.numCall;
	}

	public void setNumCall(BigDecimal numCall) {
		this.numCall = numCall;
	}

	public BigDecimal getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(BigDecimal serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}