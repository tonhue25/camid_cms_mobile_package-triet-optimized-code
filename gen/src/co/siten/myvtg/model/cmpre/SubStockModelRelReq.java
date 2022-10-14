package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_STOCK_MODEL_REL_REQ database table.
 * 
 */
@Entity
@Table(name="SUB_STOCK_MODEL_REL_REQ")
@NamedQuery(name="SubStockModelRelReq.findAll", query="SELECT s FROM SubStockModelRelReq s")
public class SubStockModelRelReq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CREATED_DATE")
	private Object createdDate;

	@Column(name="PARTNER_ID")
	private BigDecimal partnerId;

	@Column(name="PARTNER_NAME")
	private String partnerName;

	@Column(name="SALE_SERVICE_ID")
	private BigDecimal saleServiceId;

	@Column(name="SALE_TRANS_ID")
	private BigDecimal saleTransId;

	private String serial;

	private BigDecimal status;

	@Column(name="STOCK_MODEL_ID")
	private BigDecimal stockModelId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_STOCK_MODEL_REL_ID")
	private BigDecimal subStockModelRelId;

	public SubStockModelRelReq() {
	}

	public Object getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Object createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(BigDecimal partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return this.partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public BigDecimal getSaleServiceId() {
		return this.saleServiceId;
	}

	public void setSaleServiceId(BigDecimal saleServiceId) {
		this.saleServiceId = saleServiceId;
	}

	public BigDecimal getSaleTransId() {
		return this.saleTransId;
	}

	public void setSaleTransId(BigDecimal saleTransId) {
		this.saleTransId = saleTransId;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStockModelId() {
		return this.stockModelId;
	}

	public void setStockModelId(BigDecimal stockModelId) {
		this.stockModelId = stockModelId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSubStockModelRelId() {
		return this.subStockModelRelId;
	}

	public void setSubStockModelRelId(BigDecimal subStockModelRelId) {
		this.subStockModelRelId = subStockModelRelId;
	}

}