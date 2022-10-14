package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CHARGE_LOG database table.
 * 
 */
@Entity
@Table(name="CHARGE_LOG")
@NamedQuery(name="ChargeLog.findAll", query="SELECT c FROM ChargeLog c")
public class ChargeLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACTION_TYPE")
	private BigDecimal actionType;

	@Column(name="BALANCE_ID")
	private String balanceId;

	@Column(name="BALANCE_VALUE")
	private String balanceValue;

	@Temporal(TemporalType.DATE)
	@Column(name="CHARGE_TIME")
	private Date chargeTime;

	@Column(name="DATA_NAME")
	private String dataName;

	private String description;

	private String msisdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public ChargeLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
	}

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getBalanceValue() {
		return this.balanceValue;
	}

	public void setBalanceValue(String balanceValue) {
		this.balanceValue = balanceValue;
	}

	public Date getChargeTime() {
		return this.chargeTime;
	}

	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}