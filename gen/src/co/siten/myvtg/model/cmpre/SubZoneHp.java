package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_ZONE_HP database table.
 * 
 */
@Entity
@Table(name="SUB_ZONE_HP")
@NamedQuery(name="SubZoneHp.findAll", query="SELECT s FROM SubZoneHp s")
public class SubZoneHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_ZONE_HP_ID")
	private BigDecimal subZoneHpId;

	@Column(name="ZONE_ID")
	private BigDecimal zoneId;

	public SubZoneHp() {
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
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

	public BigDecimal getSubZoneHpId() {
		return this.subZoneHpId;
	}

	public void setSubZoneHpId(BigDecimal subZoneHpId) {
		this.subZoneHpId = subZoneHpId;
	}

	public BigDecimal getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(BigDecimal zoneId) {
		this.zoneId = zoneId;
	}

}