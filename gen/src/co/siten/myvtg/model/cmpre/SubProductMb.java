package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_PRODUCT_MB database table.
 * 
 */
@Entity
@Table(name="SUB_PRODUCT_MB")
@NamedQuery(name="SubProductMb.findAll", query="SELECT s FROM SubProductMb s")
public class SubProductMb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="EFFECT_DATE")
	private Object effectDate;

	private BigDecimal id;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubProductMb() {
	}

	public Object getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Object effectDate) {
		this.effectDate = effectDate;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}