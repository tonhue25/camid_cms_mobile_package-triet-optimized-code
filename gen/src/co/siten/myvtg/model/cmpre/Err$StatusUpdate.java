package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ERR$_STATUS_UPDATE database table.
 * 
 */
@Entity
@Table(name="ERR$_STATUS_UPDATE")
@NamedQuery(name="Err$StatusUpdate.findAll", query="SELECT e FROM Err$StatusUpdate e")
public class Err$StatusUpdate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_CHANGE")
	private String dateChange;

	@Column(name="ID_IN")
	private String idIn;

	private String ip;

	private String isdn;

	@Column(name="NEW_STATUS")
	private String newStatus;

	@Column(name="OLD_STATUS")
	private String oldStatus;

	@Column(name="ORA_ERR_MESG$")
	private String oraErrMesg$;

	@Column(name="ORA_ERR_NUMBER$")
	private BigDecimal oraErrNumber$;

	@Column(name="ORA_ERR_OPTYP$")
	private String oraErrOptyp$;

	@Column(name="ORA_ERR_TAG$")
	private String oraErrTag$;

	@Column(name="PHONE_TYPE")
	private String phoneType;

	private String valid;

	public Err$StatusUpdate() {
	}

	public String getDateChange() {
		return this.dateChange;
	}

	public void setDateChange(String dateChange) {
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

	public String getNewStatus() {
		return this.newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public String getOldStatus() {
		return this.oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getOraErrMesg$() {
		return this.oraErrMesg$;
	}

	public void setOraErrMesg$(String oraErrMesg$) {
		this.oraErrMesg$ = oraErrMesg$;
	}

	public BigDecimal getOraErrNumber$() {
		return this.oraErrNumber$;
	}

	public void setOraErrNumber$(BigDecimal oraErrNumber$) {
		this.oraErrNumber$ = oraErrNumber$;
	}

	public String getOraErrOptyp$() {
		return this.oraErrOptyp$;
	}

	public void setOraErrOptyp$(String oraErrOptyp$) {
		this.oraErrOptyp$ = oraErrOptyp$;
	}

	public String getOraErrTag$() {
		return this.oraErrTag$;
	}

	public void setOraErrTag$(String oraErrTag$) {
		this.oraErrTag$ = oraErrTag$;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}