package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_REG_SMS_MERGE database table.
 * 
 */
@Entity
@Table(name="UD_REG_SMS_MERGE")
@NamedQuery(name="UdRegSmsMerge.findAll", query="SELECT u FROM UdRegSmsMerge u")
public class UdRegSmsMerge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATABASE_NAME")
	private String databaseName;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="NUM_REG_CYCLE")
	private BigDecimal numRegCycle;

	@Column(name="NUM_SMS")
	private BigDecimal numSms;

	@Column(name="NUM_SMS_MORE")
	private BigDecimal numSmsMore;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	public UdRegSmsMerge() {
	}

	public String getDatabaseName() {
		return this.databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getNumRegCycle() {
		return this.numRegCycle;
	}

	public void setNumRegCycle(BigDecimal numRegCycle) {
		this.numRegCycle = numRegCycle;
	}

	public BigDecimal getNumSms() {
		return this.numSms;
	}

	public void setNumSms(BigDecimal numSms) {
		this.numSms = numSms;
	}

	public BigDecimal getNumSmsMore() {
		return this.numSmsMore;
	}

	public void setNumSmsMore(BigDecimal numSmsMore) {
		this.numSmsMore = numSmsMore;
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

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}