package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MB_GPRS_ERROR database table.
 * 
 */
@Entity
@Table(name="MB_GPRS_ERROR")
@NamedQuery(name="MbGprsError.findAll", query="SELECT m FROM MbGprsError m")
public class MbGprsError implements Serializable {
	private static final long serialVersionUID = 1L;

	private String anp;

	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_OF_CYCLE")
	private Date beginOfCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="CALL_ID")
	private String callId;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CELL_ID")
	private String cellId;

	@Column(name="CHARGING_CALL_ID")
	private String chargingCallId;

	@Column(name="CHARGING_ID")
	private String chargingId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal duration;

	@Column(name="ERR_DESCRIPTION")
	private String errDescription;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="OPEAK_TYPE")
	private String opeakType;

	@Column(name="PACKAGE_TYPE")
	private String packageType;

	@Column(name="PO_CODE")
	private String poCode;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="RATING_GROUP")
	private BigDecimal ratingGroup;

	@Column(name="SEQEUNCE_NUMBER")
	private BigDecimal seqeunceNumber;

	@Column(name="SERVICE_IDENTIFIER")
	private BigDecimal serviceIdentifier;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private String url;

	private BigDecimal volume;

	public MbGprsError() {
	}

	public String getAnp() {
		return this.anp;
	}

	public void setAnp(String anp) {
		this.anp = anp;
	}

	public Date getBeginOfCycle() {
		return this.beginOfCycle;
	}

	public void setBeginOfCycle(Date beginOfCycle) {
		this.beginOfCycle = beginOfCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public Date getBillDatetime() {
		return this.billDatetime;
	}

	public void setBillDatetime(Date billDatetime) {
		this.billDatetime = billDatetime;
	}

	public String getCallId() {
		return this.callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
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

	public String getChargingCallId() {
		return this.chargingCallId;
	}

	public void setChargingCallId(String chargingCallId) {
		this.chargingCallId = chargingCallId;
	}

	public String getChargingId() {
		return this.chargingId;
	}

	public void setChargingId(String chargingId) {
		this.chargingId = chargingId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getErrDescription() {
		return this.errDescription;
	}

	public void setErrDescription(String errDescription) {
		this.errDescription = errDescription;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getPackageType() {
		return this.packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPoCode() {
		return this.poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getRatingGroup() {
		return this.ratingGroup;
	}

	public void setRatingGroup(BigDecimal ratingGroup) {
		this.ratingGroup = ratingGroup;
	}

	public BigDecimal getSeqeunceNumber() {
		return this.seqeunceNumber;
	}

	public void setSeqeunceNumber(BigDecimal seqeunceNumber) {
		this.seqeunceNumber = seqeunceNumber;
	}

	public BigDecimal getServiceIdentifier() {
		return this.serviceIdentifier;
	}

	public void setServiceIdentifier(BigDecimal serviceIdentifier) {
		this.serviceIdentifier = serviceIdentifier;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getVolume() {
		return this.volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}