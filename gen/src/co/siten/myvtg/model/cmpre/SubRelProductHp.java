package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_REL_PRODUCT_HP database table.
 * 
 */
@Entity
@Table(name="SUB_REL_PRODUCT_HP")
@NamedQuery(name="SubRelProductHp.findAll", query="SELECT s FROM SubRelProductHp s")
public class SubRelProductHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="IS_CONNECTED")
	private BigDecimal isConnected;

	@Column(name="MAIN_PRODUCT_CODE")
	private String mainProductCode;

	@Column(name="REG_DATE")
	private Object regDate;

	@Column(name="REL_PRODUCT_CODE")
	private String relProductCode;

	@Column(name="REL_PRODUCT_VALUE")
	private String relProductValue;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_REL_PRODUCT_ID")
	private BigDecimal subRelProductId;

	public SubRelProductHp() {
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
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

	public Object getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Object regDate) {
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

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
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

	public BigDecimal getSubRelProductId() {
		return this.subRelProductId;
	}

	public void setSubRelProductId(BigDecimal subRelProductId) {
		this.subRelProductId = subRelProductId;
	}

}