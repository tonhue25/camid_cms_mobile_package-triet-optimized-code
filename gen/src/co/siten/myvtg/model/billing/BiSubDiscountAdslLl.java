package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_DISCOUNT_ADSL_LL database table.
 * 
 */
@Entity
@Table(name="BI_SUB_DISCOUNT_ADSL_LL")
@NamedQuery(name="BiSubDiscountAdslLl.findAll", query="SELECT b FROM BiSubDiscountAdslLl b")
public class BiSubDiscountAdslLl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="DISCOUNT_CODE")
	private String discountCode;

	@Column(name="DISCOUNT_TYPE")
	private String discountType;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_FROM")
	private Date effectFrom;

	private BigDecimal id;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private String unit;

	@Temporal(TemporalType.DATE)
	private Date util;

	public BiSubDiscountAdslLl() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDiscountCode() {
		return this.discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountType() {
		return this.discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Date getEffectFrom() {
		return this.effectFrom;
	}

	public void setEffectFrom(Date effectFrom) {
		this.effectFrom = effectFrom;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getUtil() {
		return this.util;
	}

	public void setUtil(Date util) {
		this.util = util;
	}

}