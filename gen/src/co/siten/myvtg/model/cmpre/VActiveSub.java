package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the V_ACTIVE_SUB database table.
 * 
 */
@Entity
@Table(name="V_ACTIVE_SUB")
@NamedQuery(name="VActiveSub.findAll", query="SELECT v FROM VActiveSub v")
public class VActiveSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTIVATED_DATE")
	private Timestamp activatedDate;

	private String isdn;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	public VActiveSub() {
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