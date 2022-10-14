package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the V_SELFCARE_DATA database table.
 * 
 */
@Entity
@Table(name = "V_SELFCARE_DATA")
@NamedQuery(name = "VSelfcareData.findAll", query = "SELECT v FROM VSelfcareData v")
public class VSelfcareData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "BALANCE_ID")
	private String balanceId;

	@Column(name = "DATA_NAME")
	private String dataName;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_TIME")
	private Date expireTime;

	public VSelfcareData() {
	}

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}