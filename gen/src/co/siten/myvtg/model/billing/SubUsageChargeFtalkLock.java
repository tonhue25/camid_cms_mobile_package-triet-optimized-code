package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_USAGE_CHARGE_FTALK_LOCK database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_FTALK_LOCK")
@NamedQuery(name="SubUsageChargeFtalkLock.findAll", query="SELECT s FROM SubUsageChargeFtalkLock s")
public class SubUsageChargeFtalkLock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String error;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUM_OF_USED_FREE_TIME")
	private BigDecimal sumOfUsedFreeTime;

	public SubUsageChargeFtalkLock() {
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getSumOfUsedFreeTime() {
		return this.sumOfUsedFreeTime;
	}

	public void setSumOfUsedFreeTime(BigDecimal sumOfUsedFreeTime) {
		this.sumOfUsedFreeTime = sumOfUsedFreeTime;
	}

}