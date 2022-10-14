package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_USAGE_CHARGE_FREETALK database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_FREETALK")
@NamedQuery(name="SubUsageChargeFreetalk.findAll", query="SELECT s FROM SubUsageChargeFreetalk s")
public class SubUsageChargeFreetalk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	private Date datetime;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUM_OF_USED_FREE_TIME")
	private BigDecimal sumOfUsedFreeTime;

	public SubUsageChargeFreetalk() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSumOfUsedFreeTime() {
		return this.sumOfUsedFreeTime;
	}

	public void setSumOfUsedFreeTime(BigDecimal sumOfUsedFreeTime) {
		this.sumOfUsedFreeTime = sumOfUsedFreeTime;
	}

}