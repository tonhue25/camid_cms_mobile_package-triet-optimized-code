package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_CYCLE_RE database table.
 * 
 */
@Entity
@Table(name="SUB_CYCLE_RE")
@NamedQuery(name="SubCycleRe.findAll", query="SELECT s FROM SubCycleRe s")
public class SubCycleRe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SCR_ID")
	private long scrId;

	@Column(name="CLASS_ID")
	private String classId;

	@Temporal(TemporalType.DATE)
	@Column(name="EXCHANGE_DATE")
	private Date exchangeDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_VIP")
	private Date expireVip;

	@Temporal(TemporalType.DATE)
	@Column(name="FROM_DATE")
	private Date fromDate;

	@Column(name="IS_VIP")
	private String isVip;

	@Column(name="MARK_EXCHANGE")
	private BigDecimal markExchange;

	@Column(name="MARK_RATE")
	private BigDecimal markRate;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Temporal(TemporalType.DATE)
	@Column(name="TO_DATE")
	private Date toDate;

	public SubCycleRe() {
	}

	public long getScrId() {
		return this.scrId;
	}

	public void setScrId(long scrId) {
		this.scrId = scrId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Date getExchangeDate() {
		return this.exchangeDate;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public Date getExpireVip() {
		return this.expireVip;
	}

	public void setExpireVip(Date expireVip) {
		this.expireVip = expireVip;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getIsVip() {
		return this.isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public BigDecimal getMarkExchange() {
		return this.markExchange;
	}

	public void setMarkExchange(BigDecimal markExchange) {
		this.markExchange = markExchange;
	}

	public BigDecimal getMarkRate() {
		return this.markRate;
	}

	public void setMarkRate(BigDecimal markRate) {
		this.markRate = markRate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}