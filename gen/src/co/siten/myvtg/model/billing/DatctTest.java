package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATCT_TEST database table.
 * 
 */
@Entity
@Table(name="DATCT_TEST")
@NamedQuery(name="DatctTest.findAll", query="SELECT d FROM DatctTest d")
public class DatctTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="ISDN_NAME")
	private String isdnName;

	public DatctTest() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getIsdnName() {
		return this.isdnName;
	}

	public void setIsdnName(String isdnName) {
		this.isdnName = isdnName;
	}

}