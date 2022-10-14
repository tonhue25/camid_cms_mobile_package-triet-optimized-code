package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_TRACUU_DURATION_FREE database table.
 * 
 */
@Entity
@Table(name="V_TRACUU_DURATION_FREE")
@NamedQuery(name="VTracuuDurationFree.findAll", query="SELECT v FROM VTracuuDurationFree v")
public class VTracuuDurationFree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	private BigDecimal duration;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String isdn;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public VTracuuDurationFree() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getPromProgramCode() {
		return this.promProgramCode;
	}

	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

}