package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "PROCEDURE" database table.
 * 
 */
@Entity
@Table(name="\"PROCEDURE\"")
@NamedQuery(name="Procedure.findAll", query="SELECT p FROM Procedure p")
public class Procedure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PROCEDURE_ID")
	private BigDecimal procedureId;

	@Column(name="PROCEDURE_NAME")
	private String procedureName;

	private String status;

	public Procedure() {
	}

	public BigDecimal getProcedureId() {
		return this.procedureId;
	}

	public void setProcedureId(BigDecimal procedureId) {
		this.procedureId = procedureId;
	}

	public String getProcedureName() {
		return this.procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}