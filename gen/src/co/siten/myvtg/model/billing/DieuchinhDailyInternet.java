package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DIEUCHINH_DAILY_INTERNET database table.
 * 
 */
@Entity
@Table(name="DIEUCHINH_DAILY_INTERNET")
@NamedQuery(name="DieuchinhDailyInternet.findAll", query="SELECT d FROM DieuchinhDailyInternet d")
public class DieuchinhDailyInternet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public DieuchinhDailyInternet() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
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

}