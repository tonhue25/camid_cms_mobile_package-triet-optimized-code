package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATA_BUY_SUPPORT database table.
 * 
 */
@Entity
@Table(name="DATA_BUY_SUPPORT")
@NamedQuery(name="DataBuySupport.findAll", query="SELECT d FROM DataBuySupport d")
public class DataBuySupport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATA_NAME")
	private String dataName;

	private String name;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public DataBuySupport() {
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}