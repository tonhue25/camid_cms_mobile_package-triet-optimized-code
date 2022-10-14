package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the V_SELFCARE_DATA_TO_BUY database table.
 * 
 */
@Entity
@Table(name = "V_SELFCARE_DATA_TO_BUY")
@NamedQuery(name = "VSelfcareDataToBuy.findAll", query = "SELECT v FROM VSelfcareDataToBuy v")
public class VSelfcareDataToBuy implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "DATA_NAME")
	private String dataName;

	private String unit;

	public VSelfcareDataToBuy() {
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}