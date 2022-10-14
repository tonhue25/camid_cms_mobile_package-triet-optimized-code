package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PSTN_HOAN_CHAN_QLT database table.
 * 
 */
@Entity
@Table(name="PSTN_HOAN_CHAN_QLT")
@NamedQuery(name="PstnHoanChanQlt.findAll", query="SELECT p FROM PstnHoanChanQlt p")
public class PstnHoanChanQlt implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	public PstnHoanChanQlt() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

}