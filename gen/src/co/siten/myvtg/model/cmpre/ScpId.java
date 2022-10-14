package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SCP_ID database table.
 * 
 */
@Entity
@Table(name="SCP_ID")
@NamedQuery(name="ScpId.findAll", query="SELECT s FROM ScpId s")
public class ScpId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dangchay;

	@Column(name="SCP_ID")
	private String scpId;

	public ScpId() {
	}

	public String getDangchay() {
		return this.dangchay;
	}

	public void setDangchay(String dangchay) {
		this.dangchay = dangchay;
	}

	public String getScpId() {
		return this.scpId;
	}

	public void setScpId(String scpId) {
		this.scpId = scpId;
	}

}