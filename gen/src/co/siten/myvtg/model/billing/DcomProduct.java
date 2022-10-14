package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DCOM_PRODUCT database table.
 * 
 */
@Entity
@Table(name="DCOM_PRODUCT")
@NamedQuery(name="DcomProduct.findAll", query="SELECT d FROM DcomProduct d")
public class DcomProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CM_PRODUCT_CODE")
	private String cmProductCode;

	@Column(name="FREE_LIMIT")
	private String freeLimit;

	private String name;

	@Column(name="PC_TYPE")
	private String pcType;

	private String price;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="SUB_TYPE")
	private String subType;

	public DcomProduct() {
	}

	public String getCmProductCode() {
		return this.cmProductCode;
	}

	public void setCmProductCode(String cmProductCode) {
		this.cmProductCode = cmProductCode;
	}

	public String getFreeLimit() {
		return this.freeLimit;
	}

	public void setFreeLimit(String freeLimit) {
		this.freeLimit = freeLimit;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcType() {
		return this.pcType;
	}

	public void setPcType(String pcType) {
		this.pcType = pcType;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}