package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the V_TRANS_TYPE database table.
 * 
 */
@Entity
@Table(name="V_TRANS_TYPE")
@NamedQuery(name="VTransType.findAll", query="SELECT v FROM VTransType v")
public class VTransType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String description;

	public VTransType() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}