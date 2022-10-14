package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TIENTRATHUA_BLOCK_TEMP database table.
 * 
 */
@Entity
@Table(name="TIENTRATHUA_BLOCK_TEMP")
@NamedQuery(name="TientrathuaBlockTemp.findAll", query="SELECT t FROM TientrathuaBlockTemp t")
public class TientrathuaBlockTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tt;

	public TientrathuaBlockTemp() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTt() {
		return this.tt;
	}

	public void setTt(BigDecimal tt) {
		this.tt = tt;
	}

}