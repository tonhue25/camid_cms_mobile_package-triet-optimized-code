package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UD_NHANTIN_CHANCAT database table.
 * 
 */
@Entity
@Table(name="UD_NHANTIN_CHANCAT")
@NamedQuery(name="UdNhantinChancat.findAll", query="SELECT u FROM UdNhantinChancat u")
public class UdNhantinChancat implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="\"MESSAGE\"")
	private String message;

	private String status;

	public UdNhantinChancat() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}