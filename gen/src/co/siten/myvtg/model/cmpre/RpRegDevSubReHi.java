package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_REG_DEV_SUB_RE_HIS database table.
 * 
 */
@Entity
@Table(name="RP_REG_DEV_SUB_RE_HIS")
@NamedQuery(name="RpRegDevSubReHi.findAll", query="SELECT r FROM RpRegDevSubReHi r")
public class RpRegDevSubReHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="SYN_DATETIME")
	private Object synDatetime;

	public RpRegDevSubReHi() {
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