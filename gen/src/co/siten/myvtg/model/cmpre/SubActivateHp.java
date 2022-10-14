package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_ACTIVATE_HP database table.
 * 
 */
@Entity
@Table(name="SUB_ACTIVATE_HP")
@NamedQuery(name="SubActivateHp.findAll", query="SELECT s FROM SubActivateHp s")
public class SubActivateHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Column(name="EFFECT_DATE")
	private Object effectDate;

	private BigDecimal id;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubActivateHp() {
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Object getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Object effectDate) {
		this.effectDate = effectDate;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
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

}