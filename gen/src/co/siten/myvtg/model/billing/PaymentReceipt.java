package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PAYMENT_RECEIPT database table.
 * 
 */
@Entity
@Table(name="PAYMENT_RECEIPT")
@NamedQuery(name="PaymentReceipt.findAll", query="SELECT p FROM PaymentReceipt p")
public class PaymentReceipt implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="AMOUNT_NEED_PAY")
	private BigDecimal amountNeedPay;

	@Column(name="AMOUNT_NOT_TAX")
	private BigDecimal amountNotTax;

	@Column(name="AMOUNT_TAX")
	private BigDecimal amountTax;

	@Column(name="ATTACH_FILE")
	private String attachFile;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	@Column(name="COLLECTION_STAFF_ID")
	private BigDecimal collectionStaffId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="NUM_PRINT")
	private BigDecimal numPrint;

	@Column(name="PAYMENT_RECEIPT_ID")
	private BigDecimal paymentReceiptId;

	@Column(name="PRINT_REASON_ID")
	private BigDecimal printReasonId;

	@Column(name="RECEIPT_NO")
	private String receiptNo;

	private String status;

	@Column(name="TRANSACTION_ID")
	private BigDecimal transactionId;

	public PaymentReceipt() {
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

	public BigDecimal getAmountNotTax() {
		return this.amountNotTax;
	}

	public void setAmountNotTax(BigDecimal amountNotTax) {
		this.amountNotTax = amountNotTax;
	}

	public BigDecimal getAmountTax() {
		return this.amountTax;
	}

	public void setAmountTax(BigDecimal amountTax) {
		this.amountTax = amountTax;
	}

	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public BigDecimal getCollectionStaffId() {
		return this.collectionStaffId;
	}

	public void setCollectionStaffId(BigDecimal collectionStaffId) {
		this.collectionStaffId = collectionStaffId;
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

	public BigDecimal getNumPrint() {
		return this.numPrint;
	}

	public void setNumPrint(BigDecimal numPrint) {
		this.numPrint = numPrint;
	}

	public BigDecimal getPaymentReceiptId() {
		return this.paymentReceiptId;
	}

	public void setPaymentReceiptId(BigDecimal paymentReceiptId) {
		this.paymentReceiptId = paymentReceiptId;
	}

	public BigDecimal getPrintReasonId() {
		return this.printReasonId;
	}

	public void setPrintReasonId(BigDecimal printReasonId) {
		this.printReasonId = printReasonId;
	}

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(BigDecimal transactionId) {
		this.transactionId = transactionId;
	}

}