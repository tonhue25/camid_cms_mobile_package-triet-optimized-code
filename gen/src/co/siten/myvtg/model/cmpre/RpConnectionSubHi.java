package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_CONNECTION_SUB_HIS database table.
 * 
 */
@Entity
@Table(name="RP_CONNECTION_SUB_HIS")
@NamedQuery(name="RpConnectionSubHi.findAll", query="SELECT r FROM RpConnectionSubHi r")
public class RpConnectionSubHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="SYN_DATETIME")
	private Object synDatetime;

	public RpConnectionSubHi() {
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