package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SYNCH_SUB_MB database table.
 * 
 */
@Entity
@Table(name="SYNCH_SUB_MB")
@NamedQuery(name="SynchSubMb.findAll", query="SELECT s FROM SynchSubMb s")
public class SynchSubMb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="UPDATE_FPT")
	private BigDecimal updateFpt;

	@Column(name="VALID_DATETIME")
	private Object validDatetime;

	public SynchSubMb() {
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
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

	public BigDecimal getUpdateFpt() {
		return this.updateFpt;
	}

	public void setUpdateFpt(BigDecimal updateFpt) {
		this.updateFpt = updateFpt;
	}

	public Object getValidDatetime() {
		return this.validDatetime;
	}

	public void setValidDatetime(Object validDatetime) {
		this.validDatetime = validDatetime;
	}

}