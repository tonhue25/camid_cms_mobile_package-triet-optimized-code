package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BONUS_HISTORY database table.
 * 
 */
@Entity
@Table(name="BONUS_HISTORY")
@NamedQuery(name="BonusHistory.findAll", query="SELECT b FROM BonusHistory b")
public class BonusHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AGENT_ID")
	private BigDecimal agentId;

	@Column(name="BONUS_HISTORY_ID")
	private BigDecimal bonusHistoryId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="MONEY_DAILY")
	private BigDecimal moneyDaily;

	@Column(name="MONEY_DAILY_REF")
	private BigDecimal moneyDailyRef;

	@Column(name="MONEY_MONTH")
	private BigDecimal moneyMonth;

	@Column(name="MONEY_MONTH_REF")
	private BigDecimal moneyMonthRef;

	private String msisdn;

	@Column(name="NUMBER_DAILY")
	private BigDecimal numberDaily;

	@Column(name="NUMBER_DAILY_REF")
	private BigDecimal numberDailyRef;

	@Column(name="NUMBER_MONTH")
	private BigDecimal numberMonth;

	@Column(name="NUMBER_MONTH_REF")
	private BigDecimal numberMonthRef;

	public BonusHistory() {
	}

	public BigDecimal getAgentId() {
		return this.agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public BigDecimal getBonusHistoryId() {
		return this.bonusHistoryId;
	}

	public void setBonusHistoryId(BigDecimal bonusHistoryId) {
		this.bonusHistoryId = bonusHistoryId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getMoneyDaily() {
		return this.moneyDaily;
	}

	public void setMoneyDaily(BigDecimal moneyDaily) {
		this.moneyDaily = moneyDaily;
	}

	public BigDecimal getMoneyDailyRef() {
		return this.moneyDailyRef;
	}

	public void setMoneyDailyRef(BigDecimal moneyDailyRef) {
		this.moneyDailyRef = moneyDailyRef;
	}

	public BigDecimal getMoneyMonth() {
		return this.moneyMonth;
	}

	public void setMoneyMonth(BigDecimal moneyMonth) {
		this.moneyMonth = moneyMonth;
	}

	public BigDecimal getMoneyMonthRef() {
		return this.moneyMonthRef;
	}

	public void setMoneyMonthRef(BigDecimal moneyMonthRef) {
		this.moneyMonthRef = moneyMonthRef;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public BigDecimal getNumberDaily() {
		return this.numberDaily;
	}

	public void setNumberDaily(BigDecimal numberDaily) {
		this.numberDaily = numberDaily;
	}

	public BigDecimal getNumberDailyRef() {
		return this.numberDailyRef;
	}

	public void setNumberDailyRef(BigDecimal numberDailyRef) {
		this.numberDailyRef = numberDailyRef;
	}

	public BigDecimal getNumberMonth() {
		return this.numberMonth;
	}

	public void setNumberMonth(BigDecimal numberMonth) {
		this.numberMonth = numberMonth;
	}

	public BigDecimal getNumberMonthRef() {
		return this.numberMonthRef;
	}

	public void setNumberMonthRef(BigDecimal numberMonthRef) {
		this.numberMonthRef = numberMonthRef;
	}

}