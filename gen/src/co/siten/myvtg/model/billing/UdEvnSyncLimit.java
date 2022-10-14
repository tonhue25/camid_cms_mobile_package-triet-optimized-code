package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_EVN_SYNC_LIMIT database table.
 * 
 */
@Entity
@Table(name="UD_EVN_SYNC_LIMIT")
@NamedQuery(name="UdEvnSyncLimit.findAll", query="SELECT u FROM UdEvnSyncLimit u")
public class UdEvnSyncLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJ_STATUS")
	private String adjStatus;

	@Column(name="CM_STP_STATUS")
	private BigDecimal cmStpStatus;

	@Column(name="CM_VT_ACT_STATUS")
	private String cmVtActStatus;

	@Column(name="CM_VT_STATUS")
	private BigDecimal cmVtStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="CONVERT_DATE")
	private Date convertDate;

	@Column(name="CONVERT_ID")
	private BigDecimal convertId;

	@Column(name="DBIN_STP_STATUS")
	private BigDecimal dbinStpStatus;

	@Column(name="DBIN_VT_ACT_STATUS")
	private BigDecimal dbinVtActStatus;

	@Column(name="EVN_CUST_ID")
	private String evnCustId;

	@Column(name="EVN_DEBIT_CHARGE")
	private BigDecimal evnDebitCharge;

	@Column(name="EVN_HOT_CHARGE")
	private BigDecimal evnHotCharge;

	@Column(name="EVN_ISDN")
	private String evnIsdn;

	@Column(name="EVN_POSTPAID_TYPE")
	private String evnPostpaidType;

	@Column(name="EVN_PRODUCT_CODE")
	private String evnProductCode;

	@Column(name="EVN_STATUS")
	private BigDecimal evnStatus;

	@Column(name="EVN_SUB_ID")
	private String evnSubId;

	@Column(name="EXPORT_MI_MAX")
	private BigDecimal exportMiMax;

	@Temporal(TemporalType.DATE)
	@Column(name="INSERT_DATE")
	private Date insertDate;

	private String msg;

	@Column(name="STATUS_CONVERT")
	private BigDecimal statusConvert;

	@Column(name="STATUS_DEBIT")
	private BigDecimal statusDebit;

	@Column(name="STATUS_PAYMENT")
	private BigDecimal statusPayment;

	@Column(name="STP_EXPORT_STATUS")
	private BigDecimal stpExportStatus;

	@Column(name="VT_CONTRACT_ID")
	private BigDecimal vtContractId;

	@Column(name="VT_CUST_ID")
	private BigDecimal vtCustId;

	@Column(name="VT_PRODUCT_CODE")
	private String vtProductCode;

	@Column(name="VT_SERIAL")
	private String vtSerial;

	@Column(name="VT_SUB_ID")
	private BigDecimal vtSubId;

	public UdEvnSyncLimit() {
	}

	public String getAdjStatus() {
		return this.adjStatus;
	}

	public void setAdjStatus(String adjStatus) {
		this.adjStatus = adjStatus;
	}

	public BigDecimal getCmStpStatus() {
		return this.cmStpStatus;
	}

	public void setCmStpStatus(BigDecimal cmStpStatus) {
		this.cmStpStatus = cmStpStatus;
	}

	public String getCmVtActStatus() {
		return this.cmVtActStatus;
	}

	public void setCmVtActStatus(String cmVtActStatus) {
		this.cmVtActStatus = cmVtActStatus;
	}

	public BigDecimal getCmVtStatus() {
		return this.cmVtStatus;
	}

	public void setCmVtStatus(BigDecimal cmVtStatus) {
		this.cmVtStatus = cmVtStatus;
	}

	public Date getConvertDate() {
		return this.convertDate;
	}

	public void setConvertDate(Date convertDate) {
		this.convertDate = convertDate;
	}

	public BigDecimal getConvertId() {
		return this.convertId;
	}

	public void setConvertId(BigDecimal convertId) {
		this.convertId = convertId;
	}

	public BigDecimal getDbinStpStatus() {
		return this.dbinStpStatus;
	}

	public void setDbinStpStatus(BigDecimal dbinStpStatus) {
		this.dbinStpStatus = dbinStpStatus;
	}

	public BigDecimal getDbinVtActStatus() {
		return this.dbinVtActStatus;
	}

	public void setDbinVtActStatus(BigDecimal dbinVtActStatus) {
		this.dbinVtActStatus = dbinVtActStatus;
	}

	public String getEvnCustId() {
		return this.evnCustId;
	}

	public void setEvnCustId(String evnCustId) {
		this.evnCustId = evnCustId;
	}

	public BigDecimal getEvnDebitCharge() {
		return this.evnDebitCharge;
	}

	public void setEvnDebitCharge(BigDecimal evnDebitCharge) {
		this.evnDebitCharge = evnDebitCharge;
	}

	public BigDecimal getEvnHotCharge() {
		return this.evnHotCharge;
	}

	public void setEvnHotCharge(BigDecimal evnHotCharge) {
		this.evnHotCharge = evnHotCharge;
	}

	public String getEvnIsdn() {
		return this.evnIsdn;
	}

	public void setEvnIsdn(String evnIsdn) {
		this.evnIsdn = evnIsdn;
	}

	public String getEvnPostpaidType() {
		return this.evnPostpaidType;
	}

	public void setEvnPostpaidType(String evnPostpaidType) {
		this.evnPostpaidType = evnPostpaidType;
	}

	public String getEvnProductCode() {
		return this.evnProductCode;
	}

	public void setEvnProductCode(String evnProductCode) {
		this.evnProductCode = evnProductCode;
	}

	public BigDecimal getEvnStatus() {
		return this.evnStatus;
	}

	public void setEvnStatus(BigDecimal evnStatus) {
		this.evnStatus = evnStatus;
	}

	public String getEvnSubId() {
		return this.evnSubId;
	}

	public void setEvnSubId(String evnSubId) {
		this.evnSubId = evnSubId;
	}

	public BigDecimal getExportMiMax() {
		return this.exportMiMax;
	}

	public void setExportMiMax(BigDecimal exportMiMax) {
		this.exportMiMax = exportMiMax;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BigDecimal getStatusConvert() {
		return this.statusConvert;
	}

	public void setStatusConvert(BigDecimal statusConvert) {
		this.statusConvert = statusConvert;
	}

	public BigDecimal getStatusDebit() {
		return this.statusDebit;
	}

	public void setStatusDebit(BigDecimal statusDebit) {
		this.statusDebit = statusDebit;
	}

	public BigDecimal getStatusPayment() {
		return this.statusPayment;
	}

	public void setStatusPayment(BigDecimal statusPayment) {
		this.statusPayment = statusPayment;
	}

	public BigDecimal getStpExportStatus() {
		return this.stpExportStatus;
	}

	public void setStpExportStatus(BigDecimal stpExportStatus) {
		this.stpExportStatus = stpExportStatus;
	}

	public BigDecimal getVtContractId() {
		return this.vtContractId;
	}

	public void setVtContractId(BigDecimal vtContractId) {
		this.vtContractId = vtContractId;
	}

	public BigDecimal getVtCustId() {
		return this.vtCustId;
	}

	public void setVtCustId(BigDecimal vtCustId) {
		this.vtCustId = vtCustId;
	}

	public String getVtProductCode() {
		return this.vtProductCode;
	}

	public void setVtProductCode(String vtProductCode) {
		this.vtProductCode = vtProductCode;
	}

	public String getVtSerial() {
		return this.vtSerial;
	}

	public void setVtSerial(String vtSerial) {
		this.vtSerial = vtSerial;
	}

	public BigDecimal getVtSubId() {
		return this.vtSubId;
	}

	public void setVtSubId(BigDecimal vtSubId) {
		this.vtSubId = vtSubId;
	}

}