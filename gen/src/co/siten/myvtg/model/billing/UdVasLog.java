package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_VAS_LOG database table.
 * 
 */
@Entity
@Table(name="UD_VAS_LOG")
@NamedQuery(name="UdVasLog.findAll", query="SELECT u FROM UdVasLog u")
public class UdVasLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DELAY_HOUR")
	private BigDecimal delayHour;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="THREAD_NAME")
	private String threadName;

	public UdVasLog() {
	}

	public BigDecimal getDelayHour() {
		return this.delayHour;
	}

	public void setDelayHour(BigDecimal delayHour) {
		this.delayHour = delayHour;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getThreadName() {
		return this.threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}