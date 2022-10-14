package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MB_POST_CALL_PROSHOW database table.
 * 
 */
@Entity
@Table(name="MB_POST_CALL_PROSHOW")
@NamedQuery(name="MbPostCallProshow.findAll", query="SELECT m FROM MbPostCallProshow m")
public class MbPostCallProshow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	private BigDecimal duration;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public MbPostCallProshow() {
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
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