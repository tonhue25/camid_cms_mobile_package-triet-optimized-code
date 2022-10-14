package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TIENTRATHUA_SMS_TEMP database table.
 * 
 */
@Entity
@Table(name="TIENTRATHUA_SMS_TEMP")
@NamedQuery(name="TientrathuaSmsTemp.findAll", query="SELECT t FROM TientrathuaSmsTemp t")
public class TientrathuaSmsTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tt;

	public TientrathuaSmsTemp() {
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