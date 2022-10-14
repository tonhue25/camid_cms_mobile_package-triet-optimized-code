package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HP_DUP database table.
 * 
 */
@Entity
@Table(name="HP_DUP")
@NamedQuery(name="HpDup.findAll", query="SELECT h FROM HpDup h")
public class HpDup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ROW_ID")
	private BigDecimal rowId;

	public HpDup() {
	}

	public BigDecimal getRowId() {
		return this.rowId;
	}

	public void setRowId(BigDecimal rowId) {
		this.rowId = rowId;
	}

}