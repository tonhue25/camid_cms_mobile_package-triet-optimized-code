package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_REMOVE_SERVICE database table.
 * 
 */
@Entity
@Table(name="SUB_REMOVE_SERVICE")
@NamedQuery(name="SubRemoveService.findAll", query="SELECT s FROM SubRemoveService s")
public class SubRemoveService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	private BigDecimal id;

	private String isdn;

	@Column(name="PROCESS_DATE")
	private Object processDate;

	@Column(name="REL_PRODUCT_CODE")
	private String relProductCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal valid;

	public SubRemoveService() {
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Object getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Object processDate) {
		this.processDate = processDate;
	}

	public String getRelProductCode() {
		return this.relProductCode;
	}

	public void setRelProductCode(String relProductCode) {
		this.relProductCode = relProductCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getValid() {
		return this.valid;
	}

	public void setValid(BigDecimal valid) {
		this.valid = valid;
	}

}