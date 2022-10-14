package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_ID_NO database table.
 * 
 */
@Entity
@Table(name="SUB_ID_NO")
@NamedQuery(name="SubIdNo.findAll", query="SELECT s FROM SubIdNo s")
public class SubIdNo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String app;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="ID_NO")
	private String idNo;

	private String isdn;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubIdNo() {
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}