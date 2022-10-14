package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACT_CC database table.
 * 
 */
@Entity
@Table(name="ACT_CC")
@NamedQuery(name="ActCc.findAll", query="SELECT a FROM ActCc a")
public class ActCc implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	public ActCc() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

}