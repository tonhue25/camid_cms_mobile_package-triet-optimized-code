package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_KMT8_CALL database table.
 * 
 */
@Entity
@Table(name="UD_KMT8_CALL")
@NamedQuery(name="UdKmt8Call.findAll", query="SELECT u FROM UdKmt8Call u")
public class UdKmt8Call implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	private BigDecimal duration;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public UdKmt8Call() {
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