package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ERR$_STATUS database table.
 * 
 */
@Entity
@Table(name="ERR$_STATUS")
@NamedQuery(name="Err$Status.findAll", query="SELECT e FROM Err$Status e")
public class Err$Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_CHANGE")
	private String dateChange;

	@Column(name="ID_IN")
	private String idIn;

	@Column(name="IMPORT_DATETIME")
	private String importDatetime;

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

	@Column(name="ORA_ERR_ROWID$")
	private String oraErrRowid$;

	@Column(name="ORA_ERR_TAG$")
	private String oraErrTag$;

	@Column(name="PHONE_TYPE")
	private String phoneType;

	@Column(name="SYN_BUILD_CMD_DATETIME")
	private String synBuildCmdDatetime;

	@Column(name="SYN_BUILD_CMD_STATUS")
	private String synBuildCmdStatus;

	public Err$Status() {
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

	public String getImportDatetime() {
		return this.importDatetime;
	}

	public void setImportDatetime(String importDatetime) {
		this.importDatetime = importDatetime;
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

	public String getOraErrRowid$() {
		return this.oraErrRowid$;
	}

	public void setOraErrRowid$(String oraErrRowid$) {
		this.oraErrRowid$ = oraErrRowid$;
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

	public String getSynBuildCmdDatetime() {
		return this.synBuildCmdDatetime;
	}

	public void setSynBuildCmdDatetime(String synBuildCmdDatetime) {
		this.synBuildCmdDatetime = synBuildCmdDatetime;
	}

	public String getSynBuildCmdStatus() {
		return this.synBuildCmdStatus;
	}

	public void setSynBuildCmdStatus(String synBuildCmdStatus) {
		this.synBuildCmdStatus = synBuildCmdStatus;
	}

}