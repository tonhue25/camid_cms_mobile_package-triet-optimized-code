package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the V_ACTIVE_AND_REFILL_200 database table.
 * 
 */
@Entity
@Table(name="V_ACTIVE_AND_REFILL_200")
@NamedQuery(name="VActiveAndRefill200.findAll", query="SELECT v FROM VActiveAndRefill200 v")
public class VActiveAndRefill200 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTIVATED_DATE")
	private Timestamp activatedDate;

	private String isdn;

	@Column(name="REFILL_AMOUNT")
	private BigDecimal refillAmount;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	public VActiveAndRefill200() {
	}

	public Timestamp getActivatedDate() {
		return this.activatedDate;
	}

	public void setActivatedDate(Timestamp activatedDate) {
		this.activatedDate = activatedDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getRefillAmount() {
		return this.refillAmount;
	}

	public void setRefillAmount(BigDecimal refillAmount) {
		this.refillAmount = refillAmount;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}