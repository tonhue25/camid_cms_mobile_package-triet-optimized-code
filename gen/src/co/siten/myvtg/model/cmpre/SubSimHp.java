package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_SIM_HP database table.
 * 
 */
@Entity
@Table(name="SUB_SIM_HP")
@NamedQuery(name="SubSimHp.findAll", query="SELECT s FROM SubSimHp s")
public class SubSimHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	private BigDecimal id;

	private String imsi;

	private String serial;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubSimHp() {
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
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

}