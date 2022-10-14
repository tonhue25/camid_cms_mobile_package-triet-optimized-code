package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SERVICE_TYPE_MAPPING_CM_OCS database table.
 * 
 */
@Entity
@Table(name="SERVICE_TYPE_MAPPING_CM_OCS")
@NamedQuery(name="ServiceTypeMappingCmOc.findAll", query="SELECT s FROM ServiceTypeMappingCmOc s")
public class ServiceTypeMappingCmOc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="MB_GPRS")
	private BigDecimal mbGprs;

	@Column(name="MB_SERVICE_CALL")
	private BigDecimal mbServiceCall;

	private BigDecimal price;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private BigDecimal status;

	@Column(name="SUB_REL_PRODUCT")
	private String subRelProduct;

	@Column(name="VAS_MONTHLY_FEE")
	private BigDecimal vasMonthlyFee;

	public ServiceTypeMappingCmOc() {
	}

	public BigDecimal getMbGprs() {
		return this.mbGprs;
	}

	public void setMbGprs(BigDecimal mbGprs) {
		this.mbGprs = mbGprs;
	}

	public BigDecimal getMbServiceCall() {
		return this.mbServiceCall;
	}

	public void setMbServiceCall(BigDecimal mbServiceCall) {
		this.mbServiceCall = mbServiceCall;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public String getSubRelProduct() {
		return this.subRelProduct;
	}

	public void setSubRelProduct(String subRelProduct) {
		this.subRelProduct = subRelProduct;
	}

	public BigDecimal getVasMonthlyFee() {
		return this.vasMonthlyFee;
	}

	public void setVasMonthlyFee(BigDecimal vasMonthlyFee) {
		this.vasMonthlyFee = vasMonthlyFee;
	}

}