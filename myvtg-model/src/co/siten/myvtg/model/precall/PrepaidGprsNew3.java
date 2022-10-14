package co.siten.myvtg.model.precall;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PREPAID_GPRS_NEW3 database table.
 * 
 */
@Entity
@Table(name="PREPAID_GPRS_NEW3")
@NamedQuery(name="PrepaidGprsNew3.findAll", query="SELECT p FROM PrepaidGprsNew3 p")
public class PrepaidGprsNew3 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BASIC_AFTER")
	private BigDecimal basicAfter;

	@Column(name="BASIC_BEFORE")
	private BigDecimal basicBefore;

	@Column(name="BASIC2_AFTER")
	private BigDecimal basic2After;

	@Column(name="BASIC2_BEFORE")
	private BigDecimal basic2Before;

	@Column(name="BASIC2_COST")
	private BigDecimal basic2Cost;

	@Column(name="CALL_BASIC_COST")
	private BigDecimal callBasicCost;

	@Column(name="CALL_BONUS")
	private BigDecimal callBonus;

	@Column(name="CALL_PROM_2_COST")
	private BigDecimal callProm2Cost;

	@Column(name="CALL_PROM_COST")
	private BigDecimal callPromCost;

	@Column(name="CALL_TYPE")
	private String callType;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLED_VLR_ADDRESS")
	private String calledVlrAddress;

	@Column(name="CALLFREE_AFTER")
	private BigDecimal callfreeAfter;

	@Column(name="CALLFREE_BEFORE")
	private BigDecimal callfreeBefore;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CELL_ID")
	private String cellId;

	@Column(name="DATA_2_FREE_AFTER")
	private BigDecimal data2FreeAfter;

	@Column(name="DATA_2_FREE_BEFORE")
	private BigDecimal data2FreeBefore;

	@Column(name="DATA_FREE_AFTER")
	private BigDecimal dataFreeAfter;

	@Column(name="DATA_FREE_BEFORE")
	private BigDecimal dataFreeBefore;

	@Column(name="DATA3_FREE_AFTER")
	private BigDecimal data3FreeAfter;

	@Column(name="DATA3_FREE_BEFORE")
	private BigDecimal data3FreeBefore;

	private BigDecimal duration;

	@Column(name="EXPORT_STATUS")
	private String exportStatus;

	@Column(name="FILE_ID")
	private BigDecimal fileId;

	@Temporal(TemporalType.DATE)
	@Column(name="IMPORT_DATE")
	private Date importDate;

	@Column(name="IN_PROFILE")
	private BigDecimal inProfile;

	@Temporal(TemporalType.DATE)
	@Column(name="INSERT_DATE")
	private Date insertDate;

	@Column(name="INT_CALL_FREE_AFTER")
	private BigDecimal intCallFreeAfter;

	@Column(name="INT_CALL_FREE_BEFORE")
	private BigDecimal intCallFreeBefore;

	@Column(name="INT_SMS_FREE_AFTER")
	private BigDecimal intSmsFreeAfter;

	@Column(name="INT_SMS_FREE_BEFORE")
	private BigDecimal intSmsFreeBefore;

	@Column(name="MSC_ADDRESS")
	private String mscAddress;

	@Column(name="NA_CALL_FREE_AFTER")
	private BigDecimal naCallFreeAfter;

	@Column(name="NA_CALL_FREE_BEFORE")
	private BigDecimal naCallFreeBefore;

	@Column(name="PROM_2_AFTER")
	private BigDecimal prom2After;

	@Column(name="PROM_2_BEFORE")
	private BigDecimal prom2Before;

	@Column(name="PROM_AFTER")
	private BigDecimal promAfter;

	@Column(name="PROM_BEFORE")
	private BigDecimal promBefore;

	@Column(name="RATING_GROUP")
	private String ratingGroup;

	@Column(name="SER_ID")
	private BigDecimal serId;

	@Column(name="SERVICES_ID")
	private String servicesId;

	@Column(name="SGSN_ADDRESS")
	private String sgsnAddress;

	@Column(name="SMSFREE_AFTER")
	private BigDecimal smsfreeAfter;

	@Column(name="SMSFREE_BEFORE")
	private BigDecimal smsfreeBefore;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="TOTAL_DATA")
	private BigDecimal totalData;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="VLR_ADDRESS")
	private String vlrAddress;

	@Column(name="VOLUME_DOWN")
	private BigDecimal volumeDown;

	@Column(name="VOLUME_UP")
	private BigDecimal volumeUp;

	@Column(name="ZONE_ID")
	private String zoneId;

	public PrepaidGprsNew3() {
	}

	public BigDecimal getBasicAfter() {
		return this.basicAfter;
	}

	public void setBasicAfter(BigDecimal basicAfter) {
		this.basicAfter = basicAfter;
	}

	public BigDecimal getBasicBefore() {
		return this.basicBefore;
	}

	public void setBasicBefore(BigDecimal basicBefore) {
		this.basicBefore = basicBefore;
	}

	public BigDecimal getBasic2After() {
		return this.basic2After;
	}

	public void setBasic2After(BigDecimal basic2After) {
		this.basic2After = basic2After;
	}

	public BigDecimal getBasic2Before() {
		return this.basic2Before;
	}

	public void setBasic2Before(BigDecimal basic2Before) {
		this.basic2Before = basic2Before;
	}

	public BigDecimal getBasic2Cost() {
		return this.basic2Cost;
	}

	public void setBasic2Cost(BigDecimal basic2Cost) {
		this.basic2Cost = basic2Cost;
	}

	public BigDecimal getCallBasicCost() {
		return this.callBasicCost;
	}

	public void setCallBasicCost(BigDecimal callBasicCost) {
		this.callBasicCost = callBasicCost;
	}

	public BigDecimal getCallBonus() {
		return this.callBonus;
	}

	public void setCallBonus(BigDecimal callBonus) {
		this.callBonus = callBonus;
	}

	public BigDecimal getCallProm2Cost() {
		return this.callProm2Cost;
	}

	public void setCallProm2Cost(BigDecimal callProm2Cost) {
		this.callProm2Cost = callProm2Cost;
	}

	public BigDecimal getCallPromCost() {
		return this.callPromCost;
	}

	public void setCallPromCost(BigDecimal callPromCost) {
		this.callPromCost = callPromCost;
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

	public String getCalledVlrAddress() {
		return this.calledVlrAddress;
	}

	public void setCalledVlrAddress(String calledVlrAddress) {
		this.calledVlrAddress = calledVlrAddress;
	}

	public BigDecimal getCallfreeAfter() {
		return this.callfreeAfter;
	}

	public void setCallfreeAfter(BigDecimal callfreeAfter) {
		this.callfreeAfter = callfreeAfter;
	}

	public BigDecimal getCallfreeBefore() {
		return this.callfreeBefore;
	}

	public void setCallfreeBefore(BigDecimal callfreeBefore) {
		this.callfreeBefore = callfreeBefore;
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

	public BigDecimal getData2FreeAfter() {
		return this.data2FreeAfter;
	}

	public void setData2FreeAfter(BigDecimal data2FreeAfter) {
		this.data2FreeAfter = data2FreeAfter;
	}

	public BigDecimal getData2FreeBefore() {
		return this.data2FreeBefore;
	}

	public void setData2FreeBefore(BigDecimal data2FreeBefore) {
		this.data2FreeBefore = data2FreeBefore;
	}

	public BigDecimal getDataFreeAfter() {
		return this.dataFreeAfter;
	}

	public void setDataFreeAfter(BigDecimal dataFreeAfter) {
		this.dataFreeAfter = dataFreeAfter;
	}

	public BigDecimal getDataFreeBefore() {
		return this.dataFreeBefore;
	}

	public void setDataFreeBefore(BigDecimal dataFreeBefore) {
		this.dataFreeBefore = dataFreeBefore;
	}

	public BigDecimal getData3FreeAfter() {
		return this.data3FreeAfter;
	}

	public void setData3FreeAfter(BigDecimal data3FreeAfter) {
		this.data3FreeAfter = data3FreeAfter;
	}

	public BigDecimal getData3FreeBefore() {
		return this.data3FreeBefore;
	}

	public void setData3FreeBefore(BigDecimal data3FreeBefore) {
		this.data3FreeBefore = data3FreeBefore;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getExportStatus() {
		return this.exportStatus;
	}

	public void setExportStatus(String exportStatus) {
		this.exportStatus = exportStatus;
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public Date getImportDate() {
		return this.importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public BigDecimal getInProfile() {
		return this.inProfile;
	}

	public void setInProfile(BigDecimal inProfile) {
		this.inProfile = inProfile;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public BigDecimal getIntCallFreeAfter() {
		return this.intCallFreeAfter;
	}

	public void setIntCallFreeAfter(BigDecimal intCallFreeAfter) {
		this.intCallFreeAfter = intCallFreeAfter;
	}

	public BigDecimal getIntCallFreeBefore() {
		return this.intCallFreeBefore;
	}

	public void setIntCallFreeBefore(BigDecimal intCallFreeBefore) {
		this.intCallFreeBefore = intCallFreeBefore;
	}

	public BigDecimal getIntSmsFreeAfter() {
		return this.intSmsFreeAfter;
	}

	public void setIntSmsFreeAfter(BigDecimal intSmsFreeAfter) {
		this.intSmsFreeAfter = intSmsFreeAfter;
	}

	public BigDecimal getIntSmsFreeBefore() {
		return this.intSmsFreeBefore;
	}

	public void setIntSmsFreeBefore(BigDecimal intSmsFreeBefore) {
		this.intSmsFreeBefore = intSmsFreeBefore;
	}

	public String getMscAddress() {
		return this.mscAddress;
	}

	public void setMscAddress(String mscAddress) {
		this.mscAddress = mscAddress;
	}

	public BigDecimal getNaCallFreeAfter() {
		return this.naCallFreeAfter;
	}

	public void setNaCallFreeAfter(BigDecimal naCallFreeAfter) {
		this.naCallFreeAfter = naCallFreeAfter;
	}

	public BigDecimal getNaCallFreeBefore() {
		return this.naCallFreeBefore;
	}

	public void setNaCallFreeBefore(BigDecimal naCallFreeBefore) {
		this.naCallFreeBefore = naCallFreeBefore;
	}

	public BigDecimal getProm2After() {
		return this.prom2After;
	}

	public void setProm2After(BigDecimal prom2After) {
		this.prom2After = prom2After;
	}

	public BigDecimal getProm2Before() {
		return this.prom2Before;
	}

	public void setProm2Before(BigDecimal prom2Before) {
		this.prom2Before = prom2Before;
	}

	public BigDecimal getPromAfter() {
		return this.promAfter;
	}

	public void setPromAfter(BigDecimal promAfter) {
		this.promAfter = promAfter;
	}

	public BigDecimal getPromBefore() {
		return this.promBefore;
	}

	public void setPromBefore(BigDecimal promBefore) {
		this.promBefore = promBefore;
	}

	public String getRatingGroup() {
		return this.ratingGroup;
	}

	public void setRatingGroup(String ratingGroup) {
		this.ratingGroup = ratingGroup;
	}

	public BigDecimal getSerId() {
		return this.serId;
	}

	public void setSerId(BigDecimal serId) {
		this.serId = serId;
	}

	public String getServicesId() {
		return this.servicesId;
	}

	public void setServicesId(String servicesId) {
		this.servicesId = servicesId;
	}

	public String getSgsnAddress() {
		return this.sgsnAddress;
	}

	public void setSgsnAddress(String sgsnAddress) {
		this.sgsnAddress = sgsnAddress;
	}

	public BigDecimal getSmsfreeAfter() {
		return this.smsfreeAfter;
	}

	public void setSmsfreeAfter(BigDecimal smsfreeAfter) {
		this.smsfreeAfter = smsfreeAfter;
	}

	public BigDecimal getSmsfreeBefore() {
		return this.smsfreeBefore;
	}

	public void setSmsfreeBefore(BigDecimal smsfreeBefore) {
		this.smsfreeBefore = smsfreeBefore;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public BigDecimal getTotalData() {
		return this.totalData;
	}

	public void setTotalData(BigDecimal totalData) {
		this.totalData = totalData;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVlrAddress() {
		return this.vlrAddress;
	}

	public void setVlrAddress(String vlrAddress) {
		this.vlrAddress = vlrAddress;
	}

	public BigDecimal getVolumeDown() {
		return this.volumeDown;
	}

	public void setVolumeDown(BigDecimal volumeDown) {
		this.volumeDown = volumeDown;
	}

	public BigDecimal getVolumeUp() {
		return this.volumeUp;
	}

	public void setVolumeUp(BigDecimal volumeUp) {
		this.volumeUp = volumeUp;
	}

	public String getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

}