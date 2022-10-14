package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the STATUS_UPDATE database table.
 * 
 */
@Entity
@Table(name="STATUS_UPDATE")
@NamedQuery(name="StatusUpdate.findAll", query="SELECT s FROM StatusUpdate s")
public class StatusUpdate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_CHANGE")
	private Object dateChange;

	@Column(name="ID_IN")
	private String idIn;

	@Temporal(TemporalType.DATE)
	@Column(name="IMPORT_DATETIME")
	private Date importDatetime;

	private String ip;

	private String isdn;

	@Column(name="NEW_STATUS")
	private BigDecimal newStatus;

	@Column(name="OLD_STATUS")
	private BigDecimal oldStatus;

	@Column(name="PHONE_TYPE")
	private BigDecimal phoneType;

	private String valid;

	public StatusUpdate() {
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

	public Date getImportDatetime() {
		return this.importDatetime;
	}

	public void setImportDatetime(Date importDatetime) {
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

	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}