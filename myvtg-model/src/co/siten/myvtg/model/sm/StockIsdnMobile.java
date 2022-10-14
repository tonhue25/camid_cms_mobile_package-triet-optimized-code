package co.siten.myvtg.model.sm;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the STOCK_ISDN_MOBILE database table.
 * 
 */
@Entity
@Table(name="STOCK_ISDN_MOBILE")
@NamedQuery(name="StockIsdnMobile.findAll", query="SELECT s FROM StockIsdnMobile s")
public class StockIsdnMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHECK_DIAL")
	private BigDecimal checkDial;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="DESTROY_DATE")
	private Timestamp destroyDate;

	@Column(name="DIAL_STATUS")
	private BigDecimal dialStatus;

	@Column(name="EXCHANGE_NAME")
	private String exchangeName;

	@Column(name="FILTER_TYPE_ID")
	private BigDecimal filterTypeId;

	@Column(name="HLR_STATUS")
	private BigDecimal hlrStatus;

	@Id
	private BigDecimal id;

	private String imsi;

	@Column(name="IS_ORIGIN")
	private BigDecimal isOrigin;

	private String isdn;

	@Column(name="ISDN_TYPE")
	private String isdnType;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_MODIFY")
	private Date lastModify;

	@Column(name="LAST_UPDATE_IP_ADDRESS")
	private String lastUpdateIpAddress;

	@Column(name="LAST_UPDATE_KEY")
	private String lastUpdateKey;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_TIME")
	private Date lastUpdateTime;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="LAST_USER_MODIFY")
	private String lastUserModify;

	@Column(name="LOCK_BY_STAFF")
	private BigDecimal lockByStaff;

	@Temporal(TemporalType.DATE)
	@Column(name="LOCK_DATETIME")
	private Date lockDatetime;

	@Column(name="OPERATOR_CODE")
	private String operatorCode;

	@Column(name="OWNER_ID")
	private BigDecimal ownerId;

	@Column(name="OWNER_RECEIVER_ID")
	private BigDecimal ownerReceiverId;

	@Column(name="OWNER_RECEIVER_TYPE")
	private BigDecimal ownerReceiverType;

	@Column(name="OWNER_TYPE")
	private Integer ownerType;

	private BigDecimal price;

	@Column(name="RECEIVER_NAME")
	private String receiverName;

	@Temporal(TemporalType.DATE)
	@Column(name="REMOVE_DATE")
	private Date removeDate;

	@Column(name="RULES_ID")
	private BigDecimal rulesId;

	private String serial;

	@Column(name="SOURCE_PRICE")
	private BigDecimal sourcePrice;

	@Column(name="STATE_ID")
	private BigDecimal stateId;

	private BigDecimal status;

	@Column(name="STOCK_MODEL_ID")
	private BigDecimal stockModelId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="USER_SESSION_ID")
	private String userSessionId;

	public StockIsdnMobile() {
	}

	public BigDecimal getCheckDial() {
		return this.checkDial;
	}

	public void setCheckDial(BigDecimal checkDial) {
		this.checkDial = checkDial;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Timestamp getDestroyDate() {
		return this.destroyDate;
	}

	public void setDestroyDate(Timestamp destroyDate) {
		this.destroyDate = destroyDate;
	}

	public BigDecimal getDialStatus() {
		return this.dialStatus;
	}

	public void setDialStatus(BigDecimal dialStatus) {
		this.dialStatus = dialStatus;
	}

	public String getExchangeName() {
		return this.exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public BigDecimal getFilterTypeId() {
		return this.filterTypeId;
	}

	public void setFilterTypeId(BigDecimal filterTypeId) {
		this.filterTypeId = filterTypeId;
	}

	public BigDecimal getHlrStatus() {
		return this.hlrStatus;
	}

	public void setHlrStatus(BigDecimal hlrStatus) {
		this.hlrStatus = hlrStatus;
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

	public BigDecimal getIsOrigin() {
		return this.isOrigin;
	}

	public void setIsOrigin(BigDecimal isOrigin) {
		this.isOrigin = isOrigin;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getIsdnType() {
		return this.isdnType;
	}

	public void setIsdnType(String isdnType) {
		this.isdnType = isdnType;
	}

	public Date getLastModify() {
		return this.lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getLastUpdateIpAddress() {
		return this.lastUpdateIpAddress;
	}

	public void setLastUpdateIpAddress(String lastUpdateIpAddress) {
		this.lastUpdateIpAddress = lastUpdateIpAddress;
	}

	public String getLastUpdateKey() {
		return this.lastUpdateKey;
	}

	public void setLastUpdateKey(String lastUpdateKey) {
		this.lastUpdateKey = lastUpdateKey;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLastUserModify() {
		return this.lastUserModify;
	}

	public void setLastUserModify(String lastUserModify) {
		this.lastUserModify = lastUserModify;
	}

	public BigDecimal getLockByStaff() {
		return this.lockByStaff;
	}

	public void setLockByStaff(BigDecimal lockByStaff) {
		this.lockByStaff = lockByStaff;
	}

	public Date getLockDatetime() {
		return this.lockDatetime;
	}

	public void setLockDatetime(Date lockDatetime) {
		this.lockDatetime = lockDatetime;
	}

	public String getOperatorCode() {
		return this.operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
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

	public Integer getOwnerType() {
		return this.ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	public BigDecimal getRulesId() {
		return this.rulesId;
	}

	public void setRulesId(BigDecimal rulesId) {
		this.rulesId = rulesId;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public BigDecimal getSourcePrice() {
		return this.sourcePrice;
	}

	public void setSourcePrice(BigDecimal sourcePrice) {
		this.sourcePrice = sourcePrice;
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