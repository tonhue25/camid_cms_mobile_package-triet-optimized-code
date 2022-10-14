package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the STOCK_KIT database table.
 * 
 */
@Entity
@Table(name="STOCK_KIT")
@NamedQuery(name="StockKit.findAll", query="SELECT s FROM StockKit s")
public class StockKit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AUC_REG_DATE")
	private Object aucRegDate;

	@Column(name="AUC_REMOVE_DATE")
	private Object aucRemoveDate;

	@Column(name="AUC_STATUS")
	private String aucStatus;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Column(name="CHECK_DIAL")
	private BigDecimal checkDial;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="CREATE_USER_ID")
	private BigDecimal createUserId;

	@Column(name="DIAL_STATUS")
	private BigDecimal dialStatus;

	@Column(name="HLR_ID")
	private String hlrId;

	@Column(name="HLR_REG_DATE")
	private Object hlrRegDate;

	@Column(name="HLR_REMOVE_DATE")
	private Object hlrRemoveDate;

	@Column(name="HLR_STATUS")
	private BigDecimal hlrStatus;

	private String iccid;

	private BigDecimal id;

	private String imsi;

	private String isdn;

	@Column(name="LAST_UPDATE_DATE")
	private Object lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="LAST_UPDATE_USER_ID")
	private BigDecimal lastUpdateUserId;

	@Column(name="OLD_OWNER_ID")
	private BigDecimal oldOwnerId;

	@Column(name="OLD_OWNER_TYPE")
	private BigDecimal oldOwnerType;

	@Column(name="OWNER_ID")
	private BigDecimal ownerId;

	@Column(name="OWNER_RECEIVER_ID")
	private BigDecimal ownerReceiverId;

	@Column(name="OWNER_RECEIVER_TYPE")
	private BigDecimal ownerReceiverType;

	@Column(name="OWNER_TYPE")
	private BigDecimal ownerType;

	private String pin;

	private String pin2;

	private String puk;

	private String puk2;

	@Column(name="RECEIVER_NAME")
	private String receiverName;

	@Column(name="SALE_DATE")
	private Object saleDate;

	private String serial;

	@Column(name="SIM_TYPE")
	private String simType;

	@Column(name="STATE_ID")
	private BigDecimal stateId;

	private BigDecimal status;

	@Column(name="STOCK_MODEL_ID")
	private BigDecimal stockModelId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="USER_SESSION_ID")
	private String userSessionId;

	public StockKit() {
	}

	public Object getAucRegDate() {
		return this.aucRegDate;
	}

	public void setAucRegDate(Object aucRegDate) {
		this.aucRegDate = aucRegDate;
	}

	public Object getAucRemoveDate() {
		return this.aucRemoveDate;
	}

	public void setAucRemoveDate(Object aucRemoveDate) {
		this.aucRemoveDate = aucRemoveDate;
	}

	public String getAucStatus() {
		return this.aucStatus;
	}

	public void setAucStatus(String aucStatus) {
		this.aucStatus = aucStatus;
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

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
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

	public String getHlrId() {
		return this.hlrId;
	}

	public void setHlrId(String hlrId) {
		this.hlrId = hlrId;
	}

	public Object getHlrRegDate() {
		return this.hlrRegDate;
	}

	public void setHlrRegDate(Object hlrRegDate) {
		this.hlrRegDate = hlrRegDate;
	}

	public Object getHlrRemoveDate() {
		return this.hlrRemoveDate;
	}

	public void setHlrRemoveDate(Object hlrRemoveDate) {
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

	public Object getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Object lastUpdateDate) {
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

	public Object getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(Object saleDate) {
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

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
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