package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the B1_TRANS database table.
 * 
 */
@Entity
@Table(name="B1_TRANS")
@NamedQuery(name="B1Tran.findAll", query="SELECT b FROM B1Tran b")
public class B1Tran implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal gprs;

	@Column(name="GPRS_FEE")
	private BigDecimal gprsFee;

	private BigDecimal km;

	@Column(name="MON_FEE")
	private BigDecimal monFee;

	@Column(name="OVER_VOL")
	private BigDecimal overVol;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public B1Tran() {
	}

	public BigDecimal getGprs() {
		return this.gprs;
	}

	public void setGprs(BigDecimal gprs) {
		this.gprs = gprs;
	}

	public BigDecimal getGprsFee() {
		return this.gprsFee;
	}

	public void setGprsFee(BigDecimal gprsFee) {
		this.gprsFee = gprsFee;
	}

	public BigDecimal getKm() {
		return this.km;
	}

	public void setKm(BigDecimal km) {
		this.km = km;
	}

	public BigDecimal getMonFee() {
		return this.monFee;
	}

	public void setMonFee(BigDecimal monFee) {
		this.monFee = monFee;
	}

	public BigDecimal getOverVol() {
		return this.overVol;
	}

	public void setOverVol(BigDecimal overVol) {
		this.overVol = overVol;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}