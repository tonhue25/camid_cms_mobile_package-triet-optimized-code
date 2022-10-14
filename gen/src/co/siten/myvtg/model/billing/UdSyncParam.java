package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UD_SYNC_PARAM database table.
 * 
 */
@Entity
@Table(name="UD_SYNC_PARAM")
@NamedQuery(name="UdSyncParam.findAll", query="SELECT u FROM UdSyncParam u")
public class UdSyncParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="PAR_DATE")
	private Date parDate;

	@Column(name="PAR_NAME")
	private String parName;

	@Column(name="PAR_VALUE")
	private String parValue;

	public UdSyncParam() {
	}

	public Date getParDate() {
		return this.parDate;
	}

	public void setParDate(Date parDate) {
		this.parDate = parDate;
	}

	public String getParName() {
		return this.parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

	public String getParValue() {
		return this.parValue;
	}

	public void setParValue(String parValue) {
		this.parValue = parValue;
	}

}