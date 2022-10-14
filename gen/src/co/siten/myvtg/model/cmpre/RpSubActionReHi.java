package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_SUB_ACTION_RE_HIS database table.
 * 
 */
@Entity
@Table(name="RP_SUB_ACTION_RE_HIS")
@NamedQuery(name="RpSubActionReHi.findAll", query="SELECT r FROM RpSubActionReHi r")
public class RpSubActionReHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="SYN_DATETIME")
	private Object synDatetime;

	public RpSubActionReHi() {
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