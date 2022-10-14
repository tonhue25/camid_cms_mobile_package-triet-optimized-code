package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_CONGTIEN_DEM_CP database table.
 * 
 */
@Entity
@Table(name="UD_CONGTIEN_DEM_CP")
@NamedQuery(name="UdCongtienDemCp.findAll", query="SELECT u FROM UdCongtienDemCp u")
public class UdCongtienDemCp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="SL_CP")
	private BigDecimal slCp;

	public UdCongtienDemCp() {
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public BigDecimal getSlCp() {
		return this.slCp;
	}

	public void setSlCp(BigDecimal slCp) {
		this.slCp = slCp;
	}

}