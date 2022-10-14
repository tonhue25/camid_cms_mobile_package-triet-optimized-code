package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the V_ACTIVES database table.
 * 
 */
@Entity
@Table(name="V_ACTIVES")
@NamedQuery(name="VActive.findAll", query="SELECT v FROM VActive v")
public class VActive implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="STA_DATETIME")
	private Timestamp staDatetime;

	public VActive() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Timestamp getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Timestamp staDatetime) {
		this.staDatetime = staDatetime;
	}

}