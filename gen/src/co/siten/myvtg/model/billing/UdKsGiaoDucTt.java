package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_KS_GIAO_DUC_TT database table.
 * 
 */
@Entity
@Table(name="UD_KS_GIAO_DUC_TT")
@NamedQuery(name="UdKsGiaoDucTt.findAll", query="SELECT u FROM UdKsGiaoDucTt u")
public class UdKsGiaoDucTt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	public UdKsGiaoDucTt() {
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}