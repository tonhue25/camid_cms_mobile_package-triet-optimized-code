package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_REL_PRODUCT database table.
 * 
 */
@Entity
@Table(name="SUB_REL_PRODUCT")
@NamedQuery(name="SubRelProduct.findAll", query="SELECT s FROM SubRelProduct s")
public class SubRelProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Id
	private BigDecimal id;

	@Column(name="IS_CONNECTED")
	private BigDecimal isConnected;

	@Column(name="MAIN_PRODUCT_CODE")
	private String mainProductCode;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	private Date regDate;

	@Column(name="REL_PRODUCT_CODE")
	private String relProductCode;

	@Column(name="REL_PRODUCT_VALUE")
	private String relProductValue;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private Long subId;

	public SubRelProduct() {
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIsConnected() {
		return this.isConnected;
	}

	public void setIsConnected(BigDecimal isConnected) {
		this.isConnected = isConnected;
	}

	public String getMainProductCode() {
		return this.mainProductCode;
	}

	public void setMainProductCode(String mainProductCode) {
		this.mainProductCode = mainProductCode;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRelProductCode() {
		return this.relProductCode;
	}

	public void setRelProductCode(String relProductCode) {
		this.relProductCode = relProductCode;
	}

	public String getRelProductValue() {
		return this.relProductValue;
	}

	public void setRelProductValue(String relProductValue) {
		this.relProductValue = relProductValue;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public Long getSubId() {
		return this.subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

}