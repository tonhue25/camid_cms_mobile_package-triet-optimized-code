package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_ISDN_HP database table.
 * 
 */
@Entity
@Table(name="SUB_ISDN_HP")
@NamedQuery(name="SubIsdnHp.findAll", query="SELECT s FROM SubIsdnHp s")
public class SubIsdnHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	private BigDecimal id;

	private String isdn;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubIsdnHp() {
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

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
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