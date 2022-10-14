package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_KS_NHABAO_ISDN database table.
 * 
 */
@Entity
@Table(name="UD_KS_NHABAO_ISDN")
@NamedQuery(name="UdKsNhabaoIsdn.findAll", query="SELECT u FROM UdKsNhabaoIsdn u")
public class UdKsNhabaoIsdn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATETIME")
	private Date createDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	public UdKsNhabaoIsdn() {
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
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