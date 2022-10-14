package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SUB_PROMOTION_ADSL_LL_OLD database table.
 * 
 */
@Entity
@Table(name="SUB_PROMOTION_ADSL_LL_OLD")
@NamedQuery(name="SubPromotionAdslLlOld.findAll", query="SELECT s FROM SubPromotionAdslLlOld s")
public class SubPromotionAdslLlOld implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUB_ID")
	private long subId;

	@Temporal(TemporalType.DATE)
	private Date effect;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Temporal(TemporalType.DATE)
	@Column(name="\"UNTIL\"")
	private Date until;

	public SubPromotionAdslLlOld() {
	}

	public long getSubId() {
		return this.subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
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

	public Date getUntil() {
		return this.until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

}