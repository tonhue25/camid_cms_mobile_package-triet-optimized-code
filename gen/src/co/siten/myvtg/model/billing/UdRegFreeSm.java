package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_REG_FREE_SMS database table.
 * 
 */
@Entity
@Table(name="UD_REG_FREE_SMS")
@NamedQuery(name="UdRegFreeSm.findAll", query="SELECT u FROM UdRegFreeSm u")
public class UdRegFreeSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_TIME")
	private Date expireTime;

	@Column(name="KEY_SEARCH")
	private BigDecimal keySearch;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_REGISTER")
	private Date lastRegister;

	private String msisdn;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdRegFreeSm() {
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public BigDecimal getKeySearch() {
		return this.keySearch;
	}

	public void setKeySearch(BigDecimal keySearch) {
		this.keySearch = keySearch;
	}

	public Date getLastRegister() {
		return this.lastRegister;
	}

	public void setLastRegister(Date lastRegister) {
		this.lastRegister = lastRegister;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}