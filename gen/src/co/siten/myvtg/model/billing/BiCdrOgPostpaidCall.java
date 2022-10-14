package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_CDR_OG_POSTPAID_CALL database table.
 * 
 */
@Entity
@Table(name="BI_CDR_OG_POSTPAID_CALL")
@NamedQuery(name="BiCdrOgPostpaidCall.findAll", query="SELECT b FROM BiCdrOgPostpaidCall b")
public class BiCdrOgPostpaidCall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="CALL_TYPE")
	private String callType;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CELL_ID")
	private String cellId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private BigDecimal duration;

	@Column(name="FILE_ID")
	private BigDecimal fileId;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="OPEAK_TYPE")
	private String opeakType;

	private String paid;

	@Column(name="PAY_DOM_CHARGE")
	private BigDecimal payDomCharge;

	@Column(name="PO_CODE")
	private String poCode;

	@Column(name="PRODUCT_ID")
	private String productId;

	@Column(name="REC_TYPE")
	private String recType;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TEL_SERVICE_ID")
	private BigDecimal telServiceId;

	@Column(name="\"TYPE\"")
	private String type;

	public BiCdrOgPostpaidCall() {
	}

	public Date getBillDatetime() {
		return this.billDatetime;
	}

	public void setBillDatetime(Date billDatetime) {
		this.billDatetime = billDatetime;
	}

	public String getCallType() {
		return this.callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public String getCellId() {
		return this.cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public BigDecimal getIntCharge() {
		return this.intCharge;
	}

	public void setIntCharge(BigDecimal intCharge) {
		this.intCharge = intCharge;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public String getOpeakType() {
		return this.opeakType;
	}

	public void setOpeakType(String opeakType) {
		this.opeakType = opeakType;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public BigDecimal getPayDomCharge() {
		return this.payDomCharge;
	}

	public void setPayDomCharge(BigDecimal payDomCharge) {
		this.payDomCharge = payDomCharge;
	}

	public String getPoCode() {
		return this.poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRecType() {
		return this.recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
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

	public BigDecimal getTelServiceId() {
		return this.telServiceId;
	}

	public void setTelServiceId(BigDecimal telServiceId) {
		this.telServiceId = telServiceId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}