package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_3G_SUB_DEV_HIS database table.
 * 
 */
@Entity
@Table(name="RP_3G_SUB_DEV_HIS")
@NamedQuery(name="Rp3gSubDevHi.findAll", query="SELECT r FROM Rp3gSubDevHi r")
public class Rp3gSubDevHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="HIS_ID")
	private BigDecimal hisId;

	@Column(name="SYN_DATETIME")
	private Object synDatetime;

	public Rp3gSubDevHi() {
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