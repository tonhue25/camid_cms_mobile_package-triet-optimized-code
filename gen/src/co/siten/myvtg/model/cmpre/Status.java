package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the STATUS database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_CHANGE")
	private Timestamp dateChange;

	@Column(name="ID_IN")
	private String idIn;

	@Column(name="IMPORT_DATETIME")
	private Timestamp importDatetime;

	private String ip;

	private String isdn;

	@Column(name="NEW_STATUS")
	private BigDecimal newStatus;

	@Column(name="OLD_STATUS")
	private BigDecimal oldStatus;

	@Column(name="PHONE_TYPE")
	private BigDecimal phoneType;

	@Column(name="SYN_BUILD_CMD_DATETIME")
	private Timestamp synBuildCmdDatetime;

	@Column(name="SYN_BUILD_CMD_STATUS")
	private BigDecimal synBuildCmdStatus;

	public Status() {
	}

	public Timestamp getDateChange() {
		return this.dateChange;
	}

	public void setDateChange(Timestamp dateChange) {
		this.dateChange = dateChange;
	}

	public String getIdIn() {
		return this.idIn;
	}

	public void setIdIn(String idIn) {
		this.idIn = idIn;
	}

	public Timestamp getImportDatetime() {
		return this.importDatetime;
	}

	public void setImportDatetime(Timestamp importDatetime) {
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

	public Timestamp getSynBuildCmdDatetime() {
		return this.synBuildCmdDatetime;
	}

	public void setSynBuildCmdDatetime(Timestamp synBuildCmdDatetime) {
		this.synBuildCmdDatetime = synBuildCmdDatetime;
	}

	public BigDecimal getSynBuildCmdStatus() {
		return this.synBuildCmdStatus;
	}

	public void setSynBuildCmdStatus(BigDecimal synBuildCmdStatus) {
		this.synBuildCmdStatus = synBuildCmdStatus;
	}

}