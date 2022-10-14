package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_CHANGE_PRODUCT_HIS database table.
 * 
 */
@Entity
@Table(name="RP_CHANGE_PRODUCT_HIS")
@NamedQuery(name="RpChangeProductHi.findAll", query="SELECT r FROM RpChangeProductHi r")
public class RpChangeProductHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="SYN_DATETIME")
	private Object synDatetime;

	public RpChangeProductHi() {
	}

	public BigDecimal getHisId() {
		return this.hisId;
	}

	public void setHisId(BigDecimal hisId) {
		this.hisId = hisId;
	}

	public Object getSynDatetime() {
		return this.synDatetime;
	}

	public void setSynDatetime(Object synDatetime) {
		this.synDatetime = synDatetime;
	}

}