package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_PR_SMS_SUM database table.
 * 
 */
@Entity
@Table(name="UD_PR_SMS_SUM")
@NamedQuery(name="UdPrSmsSum.findAll", query="SELECT u FROM UdPrSmsSum u")
public class UdPrSmsSum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	private String isdn;

	@Column(name="KEY_SEARCH")
	private BigDecimal keySearch;

	@Column(name="NO_SMS_USED")
	private BigDecimal noSmsUsed;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	public UdPrSmsSum() {
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getKeySearch() {
		return this.keySearch;
	}

	public void setKeySearch(BigDecimal keySearch) {
		this.keySearch = keySearch;
	}

	public BigDecimal getNoSmsUsed() {
		return this.noSmsUsed;
	}

	public void setNoSmsUsed(BigDecimal noSmsUsed) {
		this.noSmsUsed = noSmsUsed;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}