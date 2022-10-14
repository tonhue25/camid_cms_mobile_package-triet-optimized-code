package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REPORT_SUB_ACTION database table.
 * 
 */
@Entity
@Table(name="REPORT_SUB_ACTION")
@NamedQuery(name="ReportSubAction.findAll", query="SELECT r FROM ReportSubAction r")
public class ReportSubAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_NAME")
	private String actionName;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SHOP_NAME")
	private String shopName;

	public ReportSubAction() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}