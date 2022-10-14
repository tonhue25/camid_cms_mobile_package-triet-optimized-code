package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAPPING_MIGRATION database table.
 * 
 */
@Entity
@Table(name="MAPPING_MIGRATION")
@NamedQuery(name="MappingMigration.findAll", query="SELECT m FROM MappingMigration m")
public class MappingMigration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_NAME")
	private String actionName;

	private BigDecimal channel;

	private BigDecimal id;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REASON_NAME")
	private String reasonName;

	@Column(name="SALE_SERVICE_ID")
	private BigDecimal saleServiceId;

	@Column(name="SALE_SERVICE_NAME")
	private String saleServiceName;

	private BigDecimal status;

	@Column(name="TEL_SERVICE_ID")
	private BigDecimal telServiceId;

	private String vas;

	@Column(name="VAS_NAME")
	private String vasName;

	public MappingMigration() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public BigDecimal getChannel() {
		return this.channel;
	}

	public void setChannel(BigDecimal channel) {
		this.channel = channel;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public BigDecimal getSaleServiceId() {
		return this.saleServiceId;
	}

	public void setSaleServiceId(BigDecimal saleServiceId) {
		this.saleServiceId = saleServiceId;
	}

	public String getSaleServiceName() {
		return this.saleServiceName;
	}

	public void setSaleServiceName(String saleServiceName) {
		this.saleServiceName = saleServiceName;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTelServiceId() {
		return this.telServiceId;
	}

	public void setTelServiceId(BigDecimal telServiceId) {
		this.telServiceId = telServiceId;
	}

	public String getVas() {
		return this.vas;
	}

	public void setVas(String vas) {
		this.vas = vas;
	}

	public String getVasName() {
		return this.vasName;
	}

	public void setVasName(String vasName) {
		this.vasName = vasName;
	}

}