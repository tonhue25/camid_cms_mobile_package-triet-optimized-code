package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RP_CHANGE_SIM_HP_HIS database table.
 * 
 */
@Entity
@Table(name="RP_CHANGE_SIM_HP_HIS")
@NamedQuery(name="RpChangeSimHpHi.findAll", query="SELECT r FROM RpChangeSimHpHi r")
public class RpChangeSimHpHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SYNC_DATETIME")
	private Object syncDatetime;

	public RpChangeSimHpHi() {
	}

	public Object getSyncDatetime() {
		return this.syncDatetime;
	}

	public void setSyncDatetime(Object syncDatetime) {
		this.syncDatetime = syncDatetime;
	}

}