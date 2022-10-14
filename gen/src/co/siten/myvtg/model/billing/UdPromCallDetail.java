package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_PROM_CALL_DETAIL database table.
 * 
 */
@Entity
@Table(name="UD_PROM_CALL_DETAIL")
@NamedQuery(name="UdPromCallDetail.findAll", query="SELECT u FROM UdPromCallDetail u")
public class UdPromCallDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal duration;

	private String isdn;

	@Column(name="KEY_SEARCH")
	private BigDecimal keySearch;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public UdPromCallDetail() {
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getKeySearch() {
		return this.keySearch;
	}

	public void setKeySearch(BigDecimal keySearch) {
		this.keySearch = keySearch;
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