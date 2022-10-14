package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_KS_BO_DOI_TT database table.
 * 
 */
@Entity
@Table(name="UD_KS_BO_DOI_TT")
@NamedQuery(name="UdKsBoDoiTt.findAll", query="SELECT u FROM UdKsBoDoiTt u")
public class UdKsBoDoiTt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	public UdKsBoDoiTt() {
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