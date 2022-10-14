package co.siten.myvtg.model.sm;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the STOCK_SIM database table.
 * 
 */
@Entity
@Table(name = "STOCK_SIM")
@NamedQuery(name = "StockSim.findAll", query = "SELECT s FROM StockSim s")
public class StockSim implements Serializable {
	private static final long serialVersionUID = 1L;

	private String a3a8;

	private String adm1;

	@Temporal(TemporalType.DATE)
	@Column(name = "AUC_REG_DATE")
	private Date aucRegDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "AUC_REMOVE_DATE")
	private Date aucRemoveDate;

	@Column(name = "AUC_STATUS")
	private String aucStatus;

	@Column(name = "BATCH_CODE")
	private String batchCode;

	@Column(name = "CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name = "CHECK_DIAL")
	private BigDecimal checkDial;

	@Column(name = "CONTRACT_CODE")
	private String contractCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "CREATE_USER_ID")
	private BigDecimal createUserId;

	@Column(name = "DIAL_STATUS")
	private BigDecimal dialStatus;

	private String eki;

	private Long cardtype;

	@Column(name = "HLR_ID")
	private String hlrId;

	@Temporal(TemporalType.DATE)
	@Column(name = "HLR_REG_DATE")
	private Date hlrRegDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "HLR_REMOVE_DATE")
	private Date hlrRemoveDate;

	@Column(name = "HLR_STATUS")
	private BigDecimal hlrStatus;

	private String iccid;

	@Id
	private BigDecimal id;

	private String imsi;

	private String isdn;

	private String kind;

	@Column(name = "LAST_STATUS")
	private BigDecimal lastStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name = "LAST_UPDATE_USER_ID")
	private BigDecimal lastUpdateUserId;

	@Column(name = "OLD_OWNER_ID")
	private BigDecimal oldOwnerId;

	@Column(name = "OLD_OWNER_TYPE")
	private BigDecimal oldOwnerType;

	@Column(name = "OWNER_ID")
	private BigDecimal ownerId;

	@Column(name = "OWNER_RECEIVER_ID")
	private BigDecimal ownerReceiverId;

	@Column(name = "OWNER_RECEIVER_TYPE")
	private BigDecimal ownerReceiverType;

	@Column(name = "OWNER_TYPE")
	private BigDecimal ownerType;

	private String pin;

	private String pin2;

	private String puk;

	private String puk2;

	@Column(name = "RECEIVER_NAME")
	private String receiverName;

	@Temporal(TemporalType.DATE)
	@Column(name = "SALE_DATE")
	private Date saleDate;

	private String serial;

	@Column(name = "SIM_TYPE")
	private String simType;

	@Column(name = "STATE_ID")
	private BigDecimal stateId;

	private BigDecimal status;

	@Column(name = "STOCK_MODEL_ID")
	private BigDecimal stockModelId;

	@Column(name = "TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name = "USER_SESSION_ID")
	private String userSessionId;

	public StockSim() {
	}

	public String getA3a8() {
		return this.a3a8;
	}

	public void setA3a8(String a3a8) {
		this.a3a8 = a3a8;
	}

	public String getAdm1() {
		return this.adm1;
	}

	public void setAdm1(String adm1) {
		this.adm1 = adm1;
	}

	public Long getCardtype() {
		return cardtype;
	}

	public void setCardtype(Long cardtype) {
		this.cardtype = cardtype;
	}

	public Date getAucRegDate() {
		return this.aucRegDate;
	}

	public void setAucRegDate(Date aucRegDate) {
		this.aucRegDate = aucRegDate;
	}

	public Date getAucRemoveDate() {
		return this.aucRemoveDate;
	}

	public void setAucRemoveDate(Date aucRemoveDate) {
		this.aucRemoveDate = aucRemoveDate;
	}

	public String getAucStatus() {
		return this.aucStatus;
	}

	public void setAucStatus(String aucStatus) {
		this.aucStatus = aucStatus;
	}

	public String getBatchCode() {
		return this.batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public BigDecimal getCheckDial() {
		return this.checkDial;
	}

	public void setCheckDial(BigDecimal checkDial) {
		this.checkDial = checkDial;
	}

	public String getContractCode() {
		return this.contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public BigDecimal getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(BigDecimal createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getDialStatus() {
		return this.dialStatus;
	}

	public void setDialStatus(BigDecimal dialStatus) {
		this.dialStatus = dialStatus;
	}

	public String getEki() {
		return this.eki;
	}

	public void setEki(String eki) {
		this.eki = eki;
	}

	public String getHlrId() {
		return this.hlrId;
	}

	public void setHlrId(String hlrId) {
		this.hlrId = hlrId;
	}

	public Date getHlrRegDate() {
		return this.hlrRegDate;
	}

	public void setHlrRegDate(Date hlrRegDate) {
		this.hlrRegDate = hlrRegDate;
	}

	public Date getHlrRemoveDate() {
		return this.hlrRemoveDate;
	}

	public void setHlrRemoveDate(Date hlrRemoveDate) {
		this.hlrRemoveDate = hlrRemoveDate;
	}

	public BigDecimal getHlrStatus() {
		return this.hlrStatus;
	}

	public void setHlrStatus(BigDecimal hlrStatus) {
		this.hlrStatus = hlrStatus;
	}

	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public BigDecimal getLastStatus() {
		return this.lastStatus;
	}

	public void setLastStatus(BigDecimal lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public BigDecimal getLastUpdateUserId() {
		return this.lastUpdateUserId;
	}

	public void setLastUpdateUserId(BigDecimal lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public BigDecimal getOldOwnerId() {
		return this.oldOwnerId;
	}

	public void setOldOwnerId(BigDecimal oldOwnerId) {
		this.oldOwnerId = oldOwnerId;
	}

	public BigDecimal getOldOwnerType() {
		return this.oldOwnerType;
	}

	public void setOldOwnerType(BigDecimal oldOwnerType) {
		this.oldOwnerType = oldOwnerType;
	}

	public BigDecimal getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getOwnerReceiverId() {
		return this.ownerReceiverId;
	}

	public void setOwnerReceiverId(BigDecimal ownerReceiverId) {
		this.ownerReceiverId = ownerReceiverId;
	}

	public BigDecimal getOwnerReceiverType() {
		return this.ownerReceiverType;
	}

	public void setOwnerReceiverType(BigDecimal ownerReceiverType) {
		this.ownerReceiverType = ownerReceiverType;
	}

	public BigDecimal getOwnerType() {
		return this.ownerType;
	}

	public void setOwnerType(BigDecimal ownerType) {
		this.ownerType = ownerType;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPin2() {
		return this.pin2;
	}

	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	public String getPuk() {
		return this.puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getPuk2() {
		return this.puk2;
	}

	public void setPuk2(String puk2) {
		this.puk2 = puk2;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getSimType() {
		return this.simType;
	}

	public void setSimType(String simType) {
		this.simType = simType;
	}

	public BigDecimal getStateId() {
		return this.stateId;
	}

	public void setStateId(BigDecimal stateId) {
		this.stateId = stateId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStockModelId() {
		return this.stockModelId;
	}

	public void setStockModelId(BigDecimal stockModelId) {
		this.stockModelId = stockModelId;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public String getUserSessionId() {
		return this.userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

}