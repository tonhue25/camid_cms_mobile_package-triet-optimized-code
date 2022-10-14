package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_BARCODE database table.
 * 
 */
@Entity
@Table(name="PRINT_BARCODE")
@NamedQuery(name="PrintBarcode.findAll", query="SELECT p FROM PrintBarcode p")
public class PrintBarcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BAR_CODE")
	private String barCode;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	public PrintBarcode() {
	}

	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

}