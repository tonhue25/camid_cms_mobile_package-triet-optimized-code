package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_ACTIVATE_ADSL_LL database table.
 * 
 */
@Entity
@Table(name="SUB_ACTIVATE_ADSL_LL")
@NamedQuery(name="SubActivateAdslLl.findAll", query="SELECT s FROM SubActivateAdslLl s")
public class SubActivateAdslLl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_DATE")
	private Date effectDate;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubActivateAdslLl() {
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Date getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}