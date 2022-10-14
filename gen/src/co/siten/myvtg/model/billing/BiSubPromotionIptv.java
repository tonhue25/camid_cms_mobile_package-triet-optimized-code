package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_PROMOTION_IPTV database table.
 * 
 */
@Entity
@Table(name="BI_SUB_PROMOTION_IPTV")
@NamedQuery(name="BiSubPromotionIptv.findAll", query="SELECT b FROM BiSubPromotionIptv b")
public class BiSubPromotionIptv implements Serializable {
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

	public BiSubPromotionIptv() {
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