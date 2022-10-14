package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_TIMESTAMP database table.
 * 
 */
@Entity
@Table(name="UD_TIMESTAMP")
@NamedQuery(name="UdTimestamp.findAll", query="SELECT u FROM UdTimestamp u")
public class UdTimestamp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="\"CURRENT_TIME\"")
	private Date currentTime;

	public UdTimestamp() {
	}

	public Date getCurrentTime() {
		return this.currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

}