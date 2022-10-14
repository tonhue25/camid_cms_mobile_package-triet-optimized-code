package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACC_NBR database table.
 * 
 */
@Entity
@Table(name="ACC_NBR")
@NamedQuery(name="AccNbr.findAll", query="SELECT a FROM AccNbr a")
public class AccNbr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACC_NBR")
	private String accNbr;

	public AccNbr() {
	}

	public String getAccNbr() {
		return this.accNbr;
	}

	public void setAccNbr(String accNbr) {
		this.accNbr = accNbr;
	}

}