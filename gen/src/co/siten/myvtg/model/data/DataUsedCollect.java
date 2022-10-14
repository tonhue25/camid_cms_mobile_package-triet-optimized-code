package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DATA_USED_COLLECT database table.
 * 
 */
@Entity
@Table(name="DATA_USED_COLLECT")
@NamedQuery(name="DataUsedCollect.findAll", query="SELECT d FROM DataUsedCollect d")
public class DataUsedCollect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"ACTION\"")
	private BigDecimal action;

	@Column(name="DATA_SUBCRIBER_ID")
	private BigDecimal dataSubcriberId;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_TIME")
	private Date effectTime;

	private BigDecimal id;

	private String msisdn;

	@Column(name="RESTRICT_DATA")
	private BigDecimal restrictData;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	private BigDecimal volume;

	public DataUsedCollect() {
	}

	public BigDecimal getAction() {
		return this.action;
	}

	public void setAction(BigDecimal action) {
		this.action = action;
	}

	public BigDecimal getDataSubcriberId() {
		return this.dataSubcriberId;
	}

	public void setDataSubcriberId(BigDecimal dataSubcriberId) {
		this.dataSubcriberId = dataSubcriberId;
	}

	public Date getEffectTime() {
		return this.effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public BigDecimal getRestrictData() {
		return this.restrictData;
	}

	public void setRestrictData(BigDecimal restrictData) {
		this.restrictData = restrictData;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getVolume() {
		return this.volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}