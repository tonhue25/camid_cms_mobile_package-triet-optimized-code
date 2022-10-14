package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_PRODUCT_ADSL_LL database table.
 * 
 */
@Entity
@Table(name="SUB_PRODUCT_ADSL_LL")
@NamedQuery(name="SubProductAdslLl.findAll", query="SELECT s FROM SubProductAdslLl s")
public class SubProductAdslLl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_DATE")
	private Date effectDate;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubProductAdslLl() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
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