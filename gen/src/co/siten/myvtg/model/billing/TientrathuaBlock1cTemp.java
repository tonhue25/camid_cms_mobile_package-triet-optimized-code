package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TIENTRATHUA_BLOCK_1C_TEMP database table.
 * 
 */
@Entity
@Table(name="TIENTRATHUA_BLOCK_1C_TEMP")
@NamedQuery(name="TientrathuaBlock1cTemp.findAll", query="SELECT t FROM TientrathuaBlock1cTemp t")
public class TientrathuaBlock1cTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tt;

	public TientrathuaBlock1cTemp() {
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