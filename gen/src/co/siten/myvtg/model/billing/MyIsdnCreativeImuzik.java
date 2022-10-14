package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MY_ISDN_CREATIVE_IMUZIK database table.
 * 
 */
@Entity
@Table(name="MY_ISDN_CREATIVE_IMUZIK")
@NamedQuery(name="MyIsdnCreativeImuzik.findAll", query="SELECT m FROM MyIsdnCreativeImuzik m")
public class MyIsdnCreativeImuzik implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	public MyIsdnCreativeImuzik() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

}