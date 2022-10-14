package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the VAS_OFFLINE_REGISTER database table.
 * 
 */
@Entity
@Table(name="VAS_OFFLINE_REGISTER")
@NamedQuery(name="VasOfflineRegister.findAll", query="SELECT v FROM VasOfflineRegister v")
public class VasOfflineRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_ID")
	private String actionId;

	@Column(name="DATE_TIME")
	private Object dateTime;

	private BigDecimal id;

	private String isdn;

	@Column(name="MOBILE_TYPE")
	private String mobileType;

	private BigDecimal status;

	@Column(name="VAS_CODE")
	private String vasCode;

	public VasOfflineRegister() {
	}

	public String getActionId() {
		return this.actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public Object getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Object dateTime) {
		this.dateTime = dateTime;
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

	public String getMobileType() {
		return this.mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getVasCode() {
		return this.vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

}