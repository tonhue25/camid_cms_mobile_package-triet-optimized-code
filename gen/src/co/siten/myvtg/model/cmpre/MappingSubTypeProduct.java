package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAPPING_SUB_TYPE_PRODUCT database table.
 * 
 */
@Entity
@Table(name="MAPPING_SUB_TYPE_PRODUCT")
@NamedQuery(name="MappingSubTypeProduct.findAll", query="SELECT m FROM MappingSubTypeProduct m")
public class MappingSubTypeProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="IS_DEFAULT")
	private BigDecimal isDefault;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private BigDecimal status;

	@Column(name="SUB_TYPE")
	private String subType;

	public MappingSubTypeProduct() {
	}

	public BigDecimal getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(BigDecimal isDefault) {
		this.isDefault = isDefault;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}