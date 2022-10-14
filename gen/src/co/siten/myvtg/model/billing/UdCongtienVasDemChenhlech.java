package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_CONGTIEN_VAS_DEM_CHENHLECH database table.
 * 
 */
@Entity
@Table(name="UD_CONGTIEN_VAS_DEM_CHENHLECH")
@NamedQuery(name="UdCongtienVasDemChenhlech.findAll", query="SELECT u FROM UdCongtienVasDemChenhlech u")
public class UdCongtienVasDemChenhlech implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	private BigDecimal chenhlech;

	@Column(name="SL_CP")
	private BigDecimal slCp;

	@Column(name="SL_VT")
	private BigDecimal slVt;

	public UdCongtienVasDemChenhlech() {
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

	public BigDecimal getChenhlech() {
		return this.chenhlech;
	}

	public void setChenhlech(BigDecimal chenhlech) {
		this.chenhlech = chenhlech;
	}

	public BigDecimal getSlCp() {
		return this.slCp;
	}

	public void setSlCp(BigDecimal slCp) {
		this.slCp = slCp;
	}

	public BigDecimal getSlVt() {
		return this.slVt;
	}

	public void setSlVt(BigDecimal slVt) {
		this.slVt = slVt;
	}

}