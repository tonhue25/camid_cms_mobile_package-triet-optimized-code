package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_ADJUST_1800_PSTN database table.
 * 
 */
@Entity
@Table(name="BI_ADJUST_1800_PSTN")
@NamedQuery(name="BiAdjust1800Pstn.findAll", query="SELECT b FROM BiAdjust1800Pstn b")
public class BiAdjust1800Pstn implements Serializable {
	private static final long serialVersionUID = 1L;

	private String closed;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Temporal(TemporalType.DATE)
	@Column(name="\"MONTH\"")
	private Date month;

	@Column(name="SUB_ID")
	private String subId;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE")
	private Date updateDate;

	public BiAdjust1800Pstn() {
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

	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}