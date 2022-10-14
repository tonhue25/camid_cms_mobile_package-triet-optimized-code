package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_PROM_SMS_SUMMARY database table.
 * 
 */
@Entity
@Table(name="V_PROM_SMS_SUMMARY")
@NamedQuery(name="VPromSmsSummary.findAll", query="SELECT v FROM VPromSmsSummary v")
public class VPromSmsSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String isdn;

	@Column(name="NO_SMS_USED")
	private BigDecimal noSmsUsed;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	public VPromSmsSummary() {
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
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

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}