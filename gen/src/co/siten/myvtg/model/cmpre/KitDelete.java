package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KIT_DELETE database table.
 * 
 */
@Entity
@Table(name="KIT_DELETE")
@NamedQuery(name="KitDelete.findAll", query="SELECT k FROM KitDelete k")
public class KitDelete implements Serializable {
	private static final long serialVersionUID = 1L;

	private String imsi;

	private String isdn;

	private String serial;

	private String status;

	public KitDelete() {
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

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}