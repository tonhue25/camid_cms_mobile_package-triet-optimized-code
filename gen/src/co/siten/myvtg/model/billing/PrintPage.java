package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_PAGE database table.
 * 
 */
@Entity
@Table(name="PRINT_PAGE")
@NamedQuery(name="PrintPage.findAll", query="SELECT p FROM PrintPage p")
public class PrintPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="PAGE_NUM")
	private BigDecimal pageNum;

	@Column(name="TELECOM_SERVICE_ID")
	private String telecomServiceId;

	@Column(name="TEMPLATE_TYPE")
	private BigDecimal templateType;

	public PrintPage() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(BigDecimal pageNum) {
		this.pageNum = pageNum;
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

}