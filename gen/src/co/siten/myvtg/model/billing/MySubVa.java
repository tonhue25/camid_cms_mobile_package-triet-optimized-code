package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_SUB_VAS database table.
 * 
 */
@Entity
@Table(name="MY_SUB_VAS")
@NamedQuery(name="MySubVa.findAll", query="SELECT m FROM MySubVa m")
public class MySubVa implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public MySubVa() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}