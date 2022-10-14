package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_USAGE_CHARGE database table.
 * 
 */
@Entity
@Table(name="BI_SUB_USAGE_CHARGE")
@NamedQuery(name="BiSubUsageCharge.findAll", query="SELECT b FROM BiSubUsageCharge b")
public class BiSubUsageCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BILL_CYCLE_ID")
	private BigDecimal billCycleId;

	private BigDecimal charge;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"MONTH\"")
	private Date month;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="SERVICE_TYPE")
	private BigDecimal serviceType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tax;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	public BiSubUsageCharge() {
	}

	public BigDecimal getBillCycleId() {
		return this.billCycleId;
	}

	public void setBillCycleId(BigDecimal billCycleId) {
		this.billCycleId = billCycleId;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
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

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public BigDecimal getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(BigDecimal serviceType) {
		this.serviceType = serviceType;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
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

}