package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the JUNK database table.
 * 
 */
@Entity
@NamedQuery(name="Junk.findAll", query="SELECT j FROM Junk j")
public class Junk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="HAN_MUC")
	private BigDecimal hanMuc;

	@Column(name="KEY_SEARCH")
	private BigDecimal keySearch;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public Junk() {
	}

	public BigDecimal getHanMuc() {
		return this.hanMuc;
	}

	public void setHanMuc(BigDecimal hanMuc) {
		this.hanMuc = hanMuc;
	}

	public BigDecimal getKeySearch() {
		return this.keySearch;
	}

	public void setKeySearch(BigDecimal keySearch) {
		this.keySearch = keySearch;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}