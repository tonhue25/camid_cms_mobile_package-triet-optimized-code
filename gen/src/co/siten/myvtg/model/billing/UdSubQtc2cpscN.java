package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_SUB_QTC2CPSC_N database table.
 * 
 */
@Entity
@Table(name="UD_SUB_QTC2CPSC_N")
@NamedQuery(name="UdSubQtc2cpscN.findAll", query="SELECT u FROM UdSubQtc2cpscN u")
public class UdSubQtc2cpscN implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String address;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	private String district;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATETIME")
	private Date issueDatetime;

	private String name;

	private String province;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdSubQtc2cpscN() {
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Date issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}