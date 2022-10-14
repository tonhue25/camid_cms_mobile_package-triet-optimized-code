package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the REG_FREETALK database table.
 * 
 */
@Entity
@Table(name="REG_FREETALK")
@NamedQuery(name="RegFreetalk.findAll", query="SELECT r FROM RegFreetalk r")
public class RegFreetalk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_TIME")
	private Date expireTime;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_REGISTER")
	private Date lastRegister;

	private String msisdn;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="REGISTER_ID")
	private BigDecimal registerId;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private BigDecimal subType;

	public RegFreetalk() {
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getRegisterId() {
		return this.registerId;
	}

	public void setRegisterId(BigDecimal registerId) {
		this.registerId = registerId;
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

	public BigDecimal getSubType() {
		return this.subType;
	}

	public void setSubType(BigDecimal subType) {
		this.subType = subType;
	}

}