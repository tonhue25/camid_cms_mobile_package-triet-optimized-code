package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the DATA_BUY_RULE database table.
 * 
 */
@Entity
@Table(name = "DATA_BUY_RULE")
@NamedQuery(name = "DataBuyRule.findAll", query = "SELECT d FROM DataBuyRule d")
public class DataBuyRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "BALANCE_ID")
	private String balanceId;

	@Column(name = "DATA_ID")
	private String dataId;
	@Id
	@Column(name = "DATA_NAME")
	private String dataName;

	private String name;

	private String unit;

	public DataBuyRule() {
	}

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getDataId() {
		return this.dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}