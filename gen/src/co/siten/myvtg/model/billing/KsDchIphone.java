package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the KS_DCH_IPHONE database table.
 * 
 */
@Entity
@Table(name="KS_DCH_IPHONE")
@NamedQuery(name="KsDchIphone.findAll", query="SELECT k FROM KsDchIphone k")
public class KsDchIphone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal fee;

	@Column(name="GOI_CUOC")
	private String goiCuoc;

	private String isdn;

	@Column(name="N_DONGTRUOC")
	private BigDecimal nDongtruoc;

	@Column(name="N_HOANTIEN")
	private BigDecimal nHoantien;

	@Column(name="REG_TYPE")
	private String regType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tgck;

	public KsDchIphone() {
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getGoiCuoc() {
		return this.goiCuoc;
	}

	public void setGoiCuoc(String goiCuoc) {
		this.goiCuoc = goiCuoc;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNDongtruoc() {
		return this.nDongtruoc;
	}

	public void setNDongtruoc(BigDecimal nDongtruoc) {
		this.nDongtruoc = nDongtruoc;
	}

	public BigDecimal getNHoantien() {
		return this.nHoantien;
	}

	public void setNHoantien(BigDecimal nHoantien) {
		this.nHoantien = nHoantien;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
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

	public BigDecimal getTgck() {
		return this.tgck;
	}

	public void setTgck(BigDecimal tgck) {
		this.tgck = tgck;
	}

}