package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the SUB_REL_PRODUCT database table.
 * 
 */
@Entity
@Table(name="SUB_REL_PRODUCT")
@NamedQuery(name="SubRelProduct.findAll", query="SELECT s FROM SubRelProduct s")
public class SubRelProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUB_REL_PRODUCT_ID")
	private long subRelProductId;

	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="IS_CONNECTED")
	private Integer isConnected;

	@Column(name="MAIN_PRODUCT_CODE")
	private String mainProductCode;

	@Column(name="REG_DATE")
	private Date regDate;

	@Column(name="REL_PRODUCT_CODE")
	private String relProductCode;

	@Column(name="REL_PRODUCT_VALUE")
	private String relProductValue;

	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private Integer status;

	@Column(name="SUB_ID")
	private Long subId;

	public SubRelProduct() {
	}
	

    public SubRelProduct(String relProductCode, Integer isConnected) {
		super();
		this.relProductCode = relProductCode;
		this.isConnected = isConnected;
	}

	public long getSubRelProductId() {
		return this.subRelProductId;
	}

	public void setSubRelProductId(long subRelProductId) {
		this.subRelProductId = subRelProductId;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Integer getIsConnected() {
		return this.isConnected;
	}

	public void setIsConnected(Integer isConnected) {
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSubId() {
		return this.subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

}