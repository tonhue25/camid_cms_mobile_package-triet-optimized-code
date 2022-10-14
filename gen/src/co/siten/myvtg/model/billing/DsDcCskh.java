package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DS_DC_CSKH database table.
 * 
 */
@Entity
@Table(name="DS_DC_CSKH")
@NamedQuery(name="DsDcCskh.findAll", query="SELECT d FROM DsDcCskh d")
public class DsDcCskh implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="SUB_BCCS")
	private BigDecimal subBccs;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public DsDcCskh() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getSubBccs() {
		return this.subBccs;
	}

	public void setSubBccs(BigDecimal subBccs) {
		this.subBccs = subBccs;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}