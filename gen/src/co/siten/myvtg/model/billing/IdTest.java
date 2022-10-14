package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ID_TEST database table.
 * 
 */
@Entity
@Table(name="ID_TEST")
@NamedQuery(name="IdTest.findAll", query="SELECT i FROM IdTest i")
public class IdTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public IdTest() {
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}