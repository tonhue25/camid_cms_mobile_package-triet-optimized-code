package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_PROM_CONTRACT_RULE_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_PROM_CONTRACT_RULE_TEMP")
@NamedQuery(name="BiPromContractRuleTemp.findAll", query="SELECT b FROM BiPromContractRuleTemp b")
public class BiPromContractRuleTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	private String closed;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CONTRACT_PROM")
	private BigDecimal contractProm;

	private String isdn;

	@Column(name="NUM_OF_SUBSCRIBERS")
	private BigDecimal numOfSubscribers;

	@Column(name="RULE_ID")
	private BigDecimal ruleId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_PROM")
	private BigDecimal subProm;

	public BiPromContractRuleTemp() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public String getClosed() {
		return this.closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getContractProm() {
		return this.contractProm;
	}

	public void setContractProm(BigDecimal contractProm) {
		this.contractProm = contractProm;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNumOfSubscribers() {
		return this.numOfSubscribers;
	}

	public void setNumOfSubscribers(BigDecimal numOfSubscribers) {
		this.numOfSubscribers = numOfSubscribers;
	}

	public BigDecimal getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(BigDecimal ruleId) {
		this.ruleId = ruleId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSubProm() {
		return this.subProm;
	}

	public void setSubProm(BigDecimal subProm) {
		this.subProm = subProm;
	}

}