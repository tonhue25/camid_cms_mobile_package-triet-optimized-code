package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TEST_COMMIT database table.
 * 
 */
@Entity
@Table(name="TEST_COMMIT")
@NamedQuery(name="TestCommit.findAll", query="SELECT t FROM TestCommit t")
public class TestCommit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="OBJECT_ID")
	private BigDecimal objectId;

	private String test;

	public TestCommit() {
	}

	public BigDecimal getObjectId() {
		return this.objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	public String getTest() {
		return this.test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}