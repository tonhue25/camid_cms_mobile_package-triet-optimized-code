package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the V_ACTIVE_NEW database table.
 * 
 */
@Entity
@Table(name="V_ACTIVE_NEW")
@NamedQuery(name="VActiveNew.findAll", query="SELECT v FROM VActiveNew v")
public class VActiveNew implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="STA_DATETIME")
	private Timestamp staDatetime;

	public VActiveNew() {
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