package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_SUB_DIFFERENCE database table.
 * 
 */
@Entity
@Table(name="MY_SUB_DIFFERENCE")
@NamedQuery(name="MySubDifference.findAll", query="SELECT m FROM MySubDifference m")
public class MySubDifference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SYSTEM_NAME")
	private String systemName;

	public MySubDifference() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

}