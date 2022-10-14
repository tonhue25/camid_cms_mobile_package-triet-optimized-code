package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_PROMOTION_ADSL_LL database table.
 * 
 */
@Entity
@Table(name="SUB_PROMOTION_ADSL_LL")
@NamedQuery(name="SubPromotionAdslLl.findAll", query="SELECT s FROM SubPromotionAdslLl s")
public class SubPromotionAdslLl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date effect;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"UNTIL\"")
	private Date until;

	public SubPromotionAdslLl() {
	}

	public Date getEffect() {
		return this.effect;
	}

	public void setEffect(Date effect) {
		this.effect = effect;
	}

	public String getPromotionCode() {
		return this.promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public Date getUntil() {
		return this.until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

}