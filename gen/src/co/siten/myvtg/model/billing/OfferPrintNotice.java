package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OFFER_PRINT_NOTICE database table.
 * 
 */
@Entity
@Table(name="OFFER_PRINT_NOTICE")
@NamedQuery(name="OfferPrintNotice.findAll", query="SELECT o FROM OfferPrintNotice o")
public class OfferPrintNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OFFER_ID")
	private long offerId;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String isdn;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="MAIN_SUB")
	private BigDecimal mainSub;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tax;

	public OfferPrintNotice() {
	}

	public long getOfferId() {
		return this.offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getMainSub() {
		return this.mainSub;
	}

	public void setMainSub(BigDecimal mainSub) {
		this.mainSub = mainSub;
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

}