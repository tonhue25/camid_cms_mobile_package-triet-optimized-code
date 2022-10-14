package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MB_POSTPAID_CALL_FRTALK database table.
 * 
 */
@Entity
@Table(name="MB_POSTPAID_CALL_FRTALK")
@NamedQuery(name="MbPostpaidCallFrtalk.findAll", query="SELECT m FROM MbPostpaidCallFrtalk m")
public class MbPostpaidCallFrtalk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	private BigDecimal duration;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public MbPostpaidCallFrtalk() {
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

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

}