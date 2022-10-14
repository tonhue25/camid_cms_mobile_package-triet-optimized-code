package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROMOTION_TYPE database table.
 * 
 */
@Entity
@Table(name="PROMOTION_TYPE")
@NamedQuery(name="PromotionType.findAll", query="SELECT p FROM PromotionType p")
public class PromotionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHANGE_DATETIME")
	private Object changeDatetime;

	@Column(name="CREATE_DATETIME")
	private Object createDatetime;

	private String description;

	@Column(name="END_DATE")
	private Object endDate;

	@Column(name="\"LOCAL\"")
	private BigDecimal local;

	private String name;

	@Column(name="PROCEDURE_ID")
	private BigDecimal procedureId;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="PROM_TYPE")
	private String promType;

	@Column(name="STA_DATE")
	private Object staDate;

	private BigDecimal status;

	@Column(name="TEL_SERVICE")
	private String telService;

	@Column(name="\"TYPE\"")
	private String type;

	public PromotionType() {
	}

	public Object getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Object changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getLocal() {
		return this.local;
	}

	public void setLocal(BigDecimal local) {
		this.local = local;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getProcedureId() {
		return this.procedureId;
	}

	public void setProcedureId(BigDecimal procedureId) {
		this.procedureId = procedureId;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getPromProgramCode() {
		return this.promProgramCode;
	}

	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}

	public String getPromType() {
		return this.promType;
	}

	public void setPromType(String promType) {
		this.promType = promType;
	}

	public Object getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Object staDate) {
		this.staDate = staDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTelService() {
		return this.telService;
	}

	public void setTelService(String telService) {
		this.telService = telService;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}