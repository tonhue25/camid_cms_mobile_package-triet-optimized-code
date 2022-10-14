package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_SYNC_REG_LOG database table.
 * 
 */
@Entity
@Table(name="UD_SYNC_REG_LOG")
@NamedQuery(name="UdSyncRegLog.findAll", query="SELECT u FROM UdSyncRegLog u")
public class UdSyncRegLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_TIME")
	private Date lastTime;

	@Column(name="REG_TYPE")
	private String regType;

	public UdSyncRegLog() {
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

}