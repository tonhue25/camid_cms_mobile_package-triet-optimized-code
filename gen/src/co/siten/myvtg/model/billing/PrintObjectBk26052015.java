package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_OBJECT_BK26052015 database table.
 * 
 */
@Entity
@Table(name="PRINT_OBJECT_BK26052015")
@NamedQuery(name="PrintObjectBk26052015.findAll", query="SELECT p FROM PrintObjectBk26052015 p")
public class PrintObjectBk26052015 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	@Column(name="IS_TOTAL")
	private BigDecimal isTotal;

	@Column(name="PAGE_NUM")
	private BigDecimal pageNum;

	@Column(name="PAGE_ORDER")
	private BigDecimal pageOrder;

	@Column(name="PRINT_OBJECT_ID")
	private BigDecimal printObjectId;

	@Column(name="\"SPACE\"")
	private BigDecimal space;

	private BigDecimal status;

	@Column(name="TELECOM_SERVICE_ID")
	private String telecomServiceId;

	@Column(name="TEMPLATE_TYPE")
	private BigDecimal templateType;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public PrintObjectBk26052015() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public BigDecimal getIsTotal() {
		return this.isTotal;
	}

	public void setIsTotal(BigDecimal isTotal) {
		this.isTotal = isTotal;
	}

	public BigDecimal getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(BigDecimal pageNum) {
		this.pageNum = pageNum;
	}

	public BigDecimal getPageOrder() {
		return this.pageOrder;
	}

	public void setPageOrder(BigDecimal pageOrder) {
		this.pageOrder = pageOrder;
	}

	public BigDecimal getPrintObjectId() {
		return this.printObjectId;
	}

	public void setPrintObjectId(BigDecimal printObjectId) {
		this.printObjectId = printObjectId;
	}

	public BigDecimal getSpace() {
		return this.space;
	}

	public void setSpace(BigDecimal space) {
		this.space = space;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(String telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public BigDecimal getTemplateType() {
		return this.templateType;
	}

	public void setTemplateType(BigDecimal templateType) {
		this.templateType = templateType;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}