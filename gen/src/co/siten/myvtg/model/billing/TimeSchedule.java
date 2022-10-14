package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TIME_SCHEDULE database table.
 * 
 */
@Entity
@Table(name="TIME_SCHEDULE")
@NamedQuery(name="TimeSchedule.findAll", query="SELECT t FROM TimeSchedule t")
public class TimeSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="IS_DELETE")
	private BigDecimal isDelete;

	@Column(name="SERVICE_CODE")
	private String serviceCode;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATETIME")
	private Date startDatetime;

	@Column(name="TIME_SCHEDULE_ID")
	private BigDecimal timeScheduleId;

	public TimeSchedule() {
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(BigDecimal isDelete) {
		this.isDelete = isDelete;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Date getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public BigDecimal getTimeScheduleId() {
		return this.timeScheduleId;
	}

	public void setTimeScheduleId(BigDecimal timeScheduleId) {
		this.timeScheduleId = timeScheduleId;
	}

}