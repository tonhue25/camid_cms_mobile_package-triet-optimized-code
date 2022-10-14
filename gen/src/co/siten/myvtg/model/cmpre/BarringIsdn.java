package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BARRING_ISDN database table.
 * 
 */
@Entity
@Table(name="BARRING_ISDN")
@NamedQuery(name="BarringIsdn.findAll", query="SELECT b FROM BarringIsdn b")
public class BarringIsdn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_BARRING")
	private Object dateBarring;

	private String imsi;

	private String isdn;

	@Column(name="NEW_STATUS")
	private String newStatus;

	@Column(name="OLD_STATUS")
	private String oldStatus;

	private String serial;

	@Column(name="UPDATE_IM_STATUS")
	private String updateImStatus;

	public BarringIsdn() {
	}

	public Object getDateBarring() {
		return this.dateBarring;
	}

	public void setDateBarring(Object dateBarring) {
		this.dateBarring = dateBarring;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
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

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getUpdateImStatus() {
		return this.updateImStatus;
	}

	public void setUpdateImStatus(String updateImStatus) {
		this.updateImStatus = updateImStatus;
	}

}