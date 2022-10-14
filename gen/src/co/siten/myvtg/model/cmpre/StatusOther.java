package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the STATUS_OTHER database table.
 * 
 */
@Entity
@Table(name="STATUS_OTHER")
@NamedQuery(name="StatusOther.findAll", query="SELECT s FROM StatusOther s")
public class StatusOther implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_CHANGE")
	private Object dateChange;

	@Column(name="ID_IN")
	private String idIn;

	private String ip;

	private String isdn;

	@Column(name="NEW_STATUS")
	private BigDecimal newStatus;

	@Column(name="OLD_STATUS")
	private BigDecimal oldStatus;

	@Column(name="PHONE_TYPE")
	private BigDecimal phoneType;

	public StatusOther() {
	}

	public Object getDateChange() {
		return this.dateChange;
	}

	public void setDateChange(Object dateChange) {
		this.dateChange = dateChange;
	}

	public String getIdIn() {
		return this.idIn;
	}

	public void setIdIn(String idIn) {
		this.idIn = idIn;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNewStatus() {
		return this.newStatus;
	}

	public void setNewStatus(BigDecimal newStatus) {
		this.newStatus = newStatus;
	}

	public BigDecimal getOldStatus() {
		return this.oldStatus;
	}

	public void setOldStatus(BigDecimal oldStatus) {
		this.oldStatus = oldStatus;
	}

	public BigDecimal getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(BigDecimal phoneType) {
		this.phoneType = phoneType;
	}

}