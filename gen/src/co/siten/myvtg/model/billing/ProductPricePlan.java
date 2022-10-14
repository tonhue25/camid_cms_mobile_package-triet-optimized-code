package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT_PRICE_PLAN database table.
 * 
 */
@Entity
@Table(name="PRODUCT_PRICE_PLAN")
@NamedQuery(name="ProductPricePlan.findAll", query="SELECT p FROM ProductPricePlan p")
public class ProductPricePlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHARGE_METHOD")
	private BigDecimal chargeMethod;

	private String code;

	private BigDecimal id;

	@Column(name="MAX_MONEY")
	private BigDecimal maxMoney;

	private String name;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	private BigDecimal speed;

	private String status;

	@Column(name="SUB_TYPE")
	private String subType;

	public ProductPricePlan() {
	}

	public BigDecimal getChargeMethod() {
		return this.chargeMethod;
	}

	public void setChargeMethod(BigDecimal chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getMaxMoney() {
		return this.maxMoney;
	}

	public void setMaxMoney(BigDecimal maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public BigDecimal getSpeed() {
		return this.speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}