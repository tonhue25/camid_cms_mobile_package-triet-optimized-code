package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_CONTRACT_DISCOUNT_ADSL_LL database table.
 * 
 */
@Entity
@Table(name="BI_CONTRACT_DISCOUNT_ADSL_LL")
@NamedQuery(name="BiContractDiscountAdslLl.findAll", query="SELECT b FROM BiContractDiscountAdslLl b")
public class BiContractDiscountAdslLl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DISCOUNT_CODE")
	private String discountCode;

	@Column(name="DISCOUNT_TYPE")
	private String discountType;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_FROM")
	private Date effectFrom;

	private BigDecimal id;

	private String unit;

	@Temporal(TemporalType.DATE)
	@Column(name="\"UNTIL\"")
	private Date until;

	public BiContractDiscountAdslLl() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getUntil() {
		return this.until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

}