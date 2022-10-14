package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_TEMP_CATEGORY database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_CATEGORY")
@NamedQuery(name="MyTempCategory.findAll", query="SELECT m FROM MyTempCategory m")
public class MyTempCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="PRINT_METHOD_CODE")
	private String printMethodCode;

	public MyTempCategory() {
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getPrintMethodCode() {
		return this.printMethodCode;
	}

	public void setPrintMethodCode(String printMethodCode) {
		this.printMethodCode = printMethodCode;
	}

}