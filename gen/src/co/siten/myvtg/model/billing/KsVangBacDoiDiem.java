package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the KS_VANG_BAC_DOI_DIEM database table.
 * 
 */
@Entity
@Table(name="KS_VANG_BAC_DOI_DIEM")
@NamedQuery(name="KsVangBacDoiDiem.findAll", query="SELECT k FROM KsVangBacDoiDiem k")
public class KsVangBacDoiDiem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tien;

	public KsVangBacDoiDiem() {
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

	public BigDecimal getTien() {
		return this.tien;
	}

	public void setTien(BigDecimal tien) {
		this.tien = tien;
	}

}