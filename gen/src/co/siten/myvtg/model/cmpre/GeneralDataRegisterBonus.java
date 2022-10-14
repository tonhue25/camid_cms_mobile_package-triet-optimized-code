package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GENERAL_DATA_REGISTER_BONUS database table.
 * 
 */
@Entity
@Table(name="GENERAL_DATA_REGISTER_BONUS")
@NamedQuery(name="GeneralDataRegisterBonus.findAll", query="SELECT g FROM GeneralDataRegisterBonus g")
public class GeneralDataRegisterBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_TYPE")
	private BigDecimal accountType;

	@Column(name="AGENT_ID")
	private BigDecimal agentId;

	@Column(name="BONUS_STATUS_DAILY")
	private BigDecimal bonusStatusDaily;

	@Column(name="BONUS_STATUS_MONTH")
	private BigDecimal bonusStatusMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="DEALER_REF")
	private BigDecimal dealerRef;

	private String description;

	@Column(name="ERROR_CODE_DAILY")
	private String errorCodeDaily;

	@Column(name="ERROR_CODE_MONTH")
	private String errorCodeMonth;

	@Column(name="GDRB_ID")
	private BigDecimal gdrbId;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="MSISDN_REF")
	private String msisdnRef;

	@Column(name="NUMBER_REFILL")
	private BigDecimal numberRefill;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TOTAL_REFILL")
	private BigDecimal totalRefill;

	@Column(name="TRANS_ID_DAILY")
	private BigDecimal transIdDaily;

	@Column(name="TRANS_ID_MONTH")
	private BigDecimal transIdMonth;

	@Column(name="TRANS_ID_REF_DAILY")
	private BigDecimal transIdRefDaily;

	@Column(name="TRANS_ID_REF_MONTH")
	private BigDecimal transIdRefMonth;

	@Column(name="USER_NAME")
	private String userName;

	public GeneralDataRegisterBonus() {
	}

	public BigDecimal getAccountType() {
		return this.accountType;
	}

	public void setAccountType(BigDecimal accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAgentId() {
		return this.agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public BigDecimal getBonusStatusDaily() {
		return this.bonusStatusDaily;
	}

	public void setBonusStatusDaily(BigDecimal bonusStatusDaily) {
		this.bonusStatusDaily = bonusStatusDaily;
	}

	public BigDecimal getBonusStatusMonth() {
		return this.bonusStatusMonth;
	}

	public void setBonusStatusMonth(BigDecimal bonusStatusMonth) {
		this.bonusStatusMonth = bonusStatusMonth;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getDealerRef() {
		return this.dealerRef;
	}

	public void setDealerRef(BigDecimal dealerRef) {
		this.dealerRef = dealerRef;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorCodeDaily() {
		return this.errorCodeDaily;
	}

	public void setErrorCodeDaily(String errorCodeDaily) {
		this.errorCodeDaily = errorCodeDaily;
	}

	public String getErrorCodeMonth() {
		return this.errorCodeMonth;
	}

	public void setErrorCodeMonth(String errorCodeMonth) {
		this.errorCodeMonth = errorCodeMonth;
	}

	public BigDecimal getGdrbId() {
		return this.gdrbId;
	}

	public void setGdrbId(BigDecimal gdrbId) {
		this.gdrbId = gdrbId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMsisdnRef() {
		return this.msisdnRef;
	}

	public void setMsisdnRef(String msisdnRef) {
		this.msisdnRef = msisdnRef;
	}

	public BigDecimal getNumberRefill() {
		return this.numberRefill;
	}

	public void setNumberRefill(BigDecimal numberRefill) {
		this.numberRefill = numberRefill;
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

	public BigDecimal getTotalRefill() {
		return this.totalRefill;
	}

	public void setTotalRefill(BigDecimal totalRefill) {
		this.totalRefill = totalRefill;
	}

	public BigDecimal getTransIdDaily() {
		return this.transIdDaily;
	}

	public void setTransIdDaily(BigDecimal transIdDaily) {
		this.transIdDaily = transIdDaily;
	}

	public BigDecimal getTransIdMonth() {
		return this.transIdMonth;
	}

	public void setTransIdMonth(BigDecimal transIdMonth) {
		this.transIdMonth = transIdMonth;
	}

	public BigDecimal getTransIdRefDaily() {
		return this.transIdRefDaily;
	}

	public void setTransIdRefDaily(BigDecimal transIdRefDaily) {
		this.transIdRefDaily = transIdRefDaily;
	}

	public BigDecimal getTransIdRefMonth() {
		return this.transIdRefMonth;
	}

	public void setTransIdRefMonth(BigDecimal transIdRefMonth) {
		this.transIdRefMonth = transIdRefMonth;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}