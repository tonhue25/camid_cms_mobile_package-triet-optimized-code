package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_AREA database table.
 * 
 */
@Entity
@Table(name="PRINT_AREA")
@NamedQuery(name="PrintArea.findAll", query="SELECT p FROM PrintArea p")
public class PrintArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PRINT_AREA_ID")
	private BigDecimal printAreaId;

	@Column(name="PRINT_AREA_NAME")
	private String printAreaName;

	private BigDecimal status;

	public PrintArea() {
	}

	public BigDecimal getPrintAreaId() {
		return this.printAreaId;
	}

	public void setPrintAreaId(BigDecimal printAreaId) {
		this.printAreaId = printAreaId;
	}

	public String getPrintAreaName() {
		return this.printAreaName;
	}

	public void setPrintAreaName(String printAreaName) {
		this.printAreaName = printAreaName;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}