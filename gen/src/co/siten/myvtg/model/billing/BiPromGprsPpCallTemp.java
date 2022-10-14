package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_PROM_GPRS_PP_CALL_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_PROM_GPRS_PP_CALL_TEMP")
@NamedQuery(name="BiPromGprsPpCallTemp.findAll", query="SELECT b FROM BiPromGprsPpCallTemp b")
public class BiPromGprsPpCallTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal charge;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal duration;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal volumn;

	public BiPromGprsPpCallTemp() {
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getVolumn() {
		return this.volumn;
	}

	public void setVolumn(BigDecimal volumn) {
		this.volumn = volumn;
	}

}