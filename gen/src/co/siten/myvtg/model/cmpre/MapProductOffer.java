package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAP_PRODUCT_OFFER database table.
 * 
 */
@Entity
@Table(name="MAP_PRODUCT_OFFER")
@NamedQuery(name="MapProductOffer.findAll", query="SELECT m FROM MapProductOffer m")
public class MapProductOffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	public MapProductOffer() {
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

}