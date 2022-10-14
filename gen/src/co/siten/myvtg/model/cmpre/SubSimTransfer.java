package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_SIM_TRANSFER database table.
 * 
 */
@Entity
@Table(name="SUB_SIM_TRANSFER")
@NamedQuery(name="SubSimTransfer.findAll", query="SELECT s FROM SubSimTransfer s")
public class SubSimTransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubSimTransferPK id;

	@Column(name="CHANGE_DATETIME")
	private Object changeDatetime;

	private String imsi;

	private String serial;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private BigDecimal status;

	public SubSimTransfer() {
	}

	public SubSimTransferPK getId() {
		return this.id;
	}

	public void setId(SubSimTransferPK id) {
		this.id = id;
	}

	public Object getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Object changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}