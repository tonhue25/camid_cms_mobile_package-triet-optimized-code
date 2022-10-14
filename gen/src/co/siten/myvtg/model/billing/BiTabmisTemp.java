package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_TABMIS_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_TABMIS_TEMP")
@NamedQuery(name="BiTabmisTemp.findAll", query="SELECT b FROM BiTabmisTemp b")
public class BiTabmisTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ACCOUNT")
	private String custAccount;

	@Column(name="CUST_NAME")
	private String custName;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal tabmis;

	public BiTabmisTemp() {
	}

	public String getCustAccount() {
		return this.custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTabmis() {
		return this.tabmis;
	}

	public void setTabmis(BigDecimal tabmis) {
		this.tabmis = tabmis;
	}

}