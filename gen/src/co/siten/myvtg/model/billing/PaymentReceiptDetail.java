package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PAYMENT_RECEIPT_DETAIL database table.
 * 
 */
@Entity
@Table(name="PAYMENT_RECEIPT_DETAIL")
@NamedQuery(name="PaymentReceiptDetail.findAll", query="SELECT p FROM PaymentReceiptDetail p")
public class PaymentReceiptDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="AMOUNT_NEED_PAY")
	private BigDecimal amountNeedPay;

	@Column(name="BEFORE_MONTH")
	private String beforeMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="PAYMENT_ID")
	private BigDecimal paymentId;

	@Column(name="PAYMENT_RECEIPT_ID")
	private BigDecimal paymentReceiptId;

	private String status;

	public PaymentReceiptDetail() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountNeedPay() {
		return this.amountNeedPay;
	}

	public void setAmountNeedPay(BigDecimal amountNeedPay) {
		this.amountNeedPay = amountNeedPay;
	}

	public String getBeforeMonth() {
		return this.beforeMonth;
	}

	public void setBeforeMonth(String beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(BigDecimal paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getPaymentReceiptId() {
		return this.paymentReceiptId;
	}

	public void setPaymentReceiptId(BigDecimal paymentReceiptId) {
		this.paymentReceiptId = paymentReceiptId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}