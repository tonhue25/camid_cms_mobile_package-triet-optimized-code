package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EXC_RATE database table.
 * 
 */
@Entity
@Table(name="EXC_RATE")
@NamedQuery(name="ExcRate.findAll", query="SELECT e FROM ExcRate e")
public class ExcRate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="EXC_RATE_ID")
	private BigDecimal excRateId;

	@Column(name="FIR_CURRENCY")
	private String firCurrency;

	private BigDecimal rate;

	@Column(name="SEC_CURRENCY")
	private String secCurrency;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	public ExcRate() {
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getExcRateId() {
		return this.excRateId;
	}

	public void setExcRateId(BigDecimal excRateId) {
		this.excRateId = excRateId;
	}

	public String getFirCurrency() {
		return this.firCurrency;
	}

	public void setFirCurrency(String firCurrency) {
		this.firCurrency = firCurrency;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getSecCurrency() {
		return this.secCurrency;
	}

	public void setSecCurrency(String secCurrency) {
		this.secCurrency = secCurrency;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

}