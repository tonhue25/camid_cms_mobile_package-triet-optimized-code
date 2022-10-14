package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DATA_SUBCRIBER_GEN database table.
 * 
 */
@Entity
@Table(name="DATA_SUBCRIBER_GEN")
@NamedQuery(name="DataSubcriberGen.findAll", query="SELECT d FROM DataSubcriberGen d")
public class DataSubcriberGen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACT_NOTE")
	private String actNote;

	@Column(name="AUTO_EXTEND")
	private BigDecimal autoExtend;

	@Column(name="DATA_NAME")
	private String dataName;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_TIME")
	private Date expireTime;

	private String msisdn;

	@Temporal(TemporalType.DATE)
	@Column(name="PAID_TIME")
	private Date paidTime;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="RESTRICT_DATA")
	private BigDecimal restrictData;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private BigDecimal subType;

	@Column(name="TEMPLATE_HLR")
	private String templateHlr;

	public DataSubcriberGen() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActNote() {
		return this.actNote;
	}

	public void setActNote(String actNote) {
		this.actNote = actNote;
	}

	public BigDecimal getAutoExtend() {
		return this.autoExtend;
	}

	public void setAutoExtend(BigDecimal autoExtend) {
		this.autoExtend = autoExtend;
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Date getPaidTime() {
		return this.paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getRestrictData() {
		return this.restrictData;
	}

	public void setRestrictData(BigDecimal restrictData) {
		this.restrictData = restrictData;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSubType() {
		return this.subType;
	}

	public void setSubType(BigDecimal subType) {
		this.subType = subType;
	}

	public String getTemplateHlr() {
		return this.templateHlr;
	}

	public void setTemplateHlr(String templateHlr) {
		this.templateHlr = templateHlr;
	}

}