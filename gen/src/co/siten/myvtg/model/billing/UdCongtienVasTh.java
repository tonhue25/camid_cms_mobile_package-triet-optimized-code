package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_CONGTIEN_VAS_TH database table.
 * 
 */
@Entity
@Table(name="UD_CONGTIEN_VAS_TH")
@NamedQuery(name="UdCongtienVasTh.findAll", query="SELECT u FROM UdCongtienVasTh u")
public class UdCongtienVasTh implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	private BigDecimal rate;

	public UdCongtienVasTh() {
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}