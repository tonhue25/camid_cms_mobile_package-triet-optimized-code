package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PACKAGES database table.
 * 
 */
@Entity
@Table(name="PACKAGES")
@NamedQuery(name="Package.findAll", query="SELECT p FROM Package p")
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	@Column(name="\"DOMAIN\"")
	private String domain;

	private String name;

	@Column(name="PACKAGE_ID")
	private BigDecimal packageId;

	@Column(name="PACKAGE_ORDER")
	private BigDecimal packageOrder;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	private String status;

	@Column(name="SUB_KIND")
	private String subKind;

	@Column(name="SUPPORT_IP")
	private String supportIp;

	public Package() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPackageId() {
		return this.packageId;
	}

	public void setPackageId(BigDecimal packageId) {
		this.packageId = packageId;
	}

	public BigDecimal getPackageOrder() {
		return this.packageOrder;
	}

	public void setPackageOrder(BigDecimal packageOrder) {
		this.packageOrder = packageOrder;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubKind() {
		return this.subKind;
	}

	public void setSubKind(String subKind) {
		this.subKind = subKind;
	}

	public String getSupportIp() {
		return this.supportIp;
	}

	public void setSupportIp(String supportIp) {
		this.supportIp = supportIp;
	}

}