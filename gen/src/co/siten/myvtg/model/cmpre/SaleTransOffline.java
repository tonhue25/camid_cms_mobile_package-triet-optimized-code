package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SALE_TRANS_OFFLINE database table.
 * 
 */
@Entity
@Table(name="SALE_TRANS_OFFLINE")
@NamedQuery(name="SaleTransOffline.findAll", query="SELECT s FROM SaleTransOffline s")
public class SaleTransOffline implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	@Column(name="AMOUNT_MODEL")
	private BigDecimal amountModel;

	@Column(name="AMOUNT_NOT_TAX")
	private BigDecimal amountNotTax;

	@Column(name="AMOUNT_SERVICE")
	private BigDecimal amountService;

	@Column(name="AMOUNT_TAX")
	private BigDecimal amountTax;

	@Column(name="APPROVER_DATE")
	private Object approverDate;

	@Column(name="APPROVER_USER")
	private String approverUser;

	@Column(name="CHECK_STOCK")
	private String checkStock;

	private String company;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CREATE_STAFF_ID")
	private BigDecimal createStaffId;

	@Column(name="CUST_NAME")
	private String custName;

	@Column(name="DESTROY_DATE")
	private Object destroyDate;

	@Column(name="DESTROY_USER")
	private String destroyUser;

	private BigDecimal discount;

	@Column(name="INVOICE_CREATE_DATE")
	private Object invoiceCreateDate;

	@Column(name="INVOICE_USED_ID")
	private BigDecimal invoiceUsedId;

	private String isdn;

	private String note;

	@Column(name="PAY_METHOD")
	private String payMethod;

	private BigDecimal promotion;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SALE_SERVICE_ID")
	private BigDecimal saleServiceId;

	@Column(name="SALE_SERVICE_PRICE_ID")
	private BigDecimal saleServicePriceId;

	@Column(name="SALE_TRANS_CODE")
	private String saleTransCode;

	@Column(name="SALE_TRANS_DATE")
	private Object saleTransDate;

	@Column(name="SALE_TRANS_OFFLINE_ID")
	private BigDecimal saleTransOfflineId;

	@Column(name="SALE_TRANS_TYPE")
	private String saleTransType;

	private String serial;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_ID")
	private BigDecimal shopId;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	private BigDecimal status;

	@Column(name="STOCK_TRANS_ID")
	private BigDecimal stockTransId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tax;

	@Column(name="TEL_NUMBER")
	private String telNumber;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	private String tin;

	@Column(name="TRANS_RESULT")
	private BigDecimal transResult;

	@Column(name="TRANSFER_GOODS")
	private String transferGoods;

	private BigDecimal vat;

	public SaleTransOffline() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAmountModel() {
		return this.amountModel;
	}

	public void setAmountModel(BigDecimal amountModel) {
		this.amountModel = amountModel;
	}

	public BigDecimal getAmountNotTax() {
		return this.amountNotTax;
	}

	public void setAmountNotTax(BigDecimal amountNotTax) {
		this.amountNotTax = amountNotTax;
	}

	public BigDecimal getAmountService() {
		return this.amountService;
	}

	public void setAmountService(BigDecimal amountService) {
		this.amountService = amountService;
	}

	public BigDecimal getAmountTax() {
		return this.amountTax;
	}

	public void setAmountTax(BigDecimal amountTax) {
		this.amountTax = amountTax;
	}

	public Object getApproverDate() {
		return this.approverDate;
	}

	public void setApproverDate(Object approverDate) {
		this.approverDate = approverDate;
	}

	public String getApproverUser() {
		return this.approverUser;
	}

	public void setApproverUser(String approverUser) {
		this.approverUser = approverUser;
	}

	public String getCheckStock() {
		return this.checkStock;
	}

	public void setCheckStock(String checkStock) {
		this.checkStock = checkStock;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getCreateStaffId() {
		return this.createStaffId;
	}

	public void setCreateStaffId(BigDecimal createStaffId) {
		this.createStaffId = createStaffId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Object getDestroyDate() {
		return this.destroyDate;
	}

	public void setDestroyDate(Object destroyDate) {
		this.destroyDate = destroyDate;
	}

	public String getDestroyUser() {
		return this.destroyUser;
	}

	public void setDestroyUser(String destroyUser) {
		this.destroyUser = destroyUser;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Object getInvoiceCreateDate() {
		return this.invoiceCreateDate;
	}

	public void setInvoiceCreateDate(Object invoiceCreateDate) {
		this.invoiceCreateDate = invoiceCreateDate;
	}

	public BigDecimal getInvoiceUsedId() {
		return this.invoiceUsedId;
	}

	public void setInvoiceUsedId(BigDecimal invoiceUsedId) {
		this.invoiceUsedId = invoiceUsedId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public BigDecimal getPromotion() {
		return this.promotion;
	}

	public void setPromotion(BigDecimal promotion) {
		this.promotion = promotion;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public BigDecimal getSaleServiceId() {
		return this.saleServiceId;
	}

	public void setSaleServiceId(BigDecimal saleServiceId) {
		this.saleServiceId = saleServiceId;
	}

	public BigDecimal getSaleServicePriceId() {
		return this.saleServicePriceId;
	}

	public void setSaleServicePriceId(BigDecimal saleServicePriceId) {
		this.saleServicePriceId = saleServicePriceId;
	}

	public String getSaleTransCode() {
		return this.saleTransCode;
	}

	public void setSaleTransCode(String saleTransCode) {
		this.saleTransCode = saleTransCode;
	}

	public Object getSaleTransDate() {
		return this.saleTransDate;
	}

	public void setSaleTransDate(Object saleTransDate) {
		this.saleTransDate = saleTransDate;
	}

	public BigDecimal getSaleTransOfflineId() {
		return this.saleTransOfflineId;
	}

	public void setSaleTransOfflineId(BigDecimal saleTransOfflineId) {
		this.saleTransOfflineId = saleTransOfflineId;
	}

	public String getSaleTransType() {
		return this.saleTransType;
	}

	public void setSaleTransType(String saleTransType) {
		this.saleTransType = saleTransType;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getShopId() {
		return this.shopId;
	}

	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStockTransId() {
		return this.stockTransId;
	}

	public void setStockTransId(BigDecimal stockTransId) {
		this.stockTransId = stockTransId;
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

	public String getTelNumber() {
		return this.telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public BigDecimal getTransResult() {
		return this.transResult;
	}

	public void setTransResult(BigDecimal transResult) {
		this.transResult = transResult;
	}

	public String getTransferGoods() {
		return this.transferGoods;
	}

	public void setTransferGoods(String transferGoods) {
		this.transferGoods = transferGoods;
	}

	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

}