package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RED_ALARM_BLACKLIST database table.
 * 
 */
@Entity
@Table(name="RED_ALARM_BLACKLIST")
@NamedQuery(name="RedAlarmBlacklist.findAll", query="SELECT r FROM RedAlarmBlacklist r")
public class RedAlarmBlacklist implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private String isdn;

	public RedAlarmBlacklist() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

}