package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_SYNC_SMS50_REG database table.
 * 
 */
@Entity
@Table(name="UD_SYNC_SMS50_REG")
@NamedQuery(name="UdSyncSms50Reg.findAll", query="SELECT u FROM UdSyncSms50Reg u")
public class UdSyncSms50Reg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_TIME")
	private Date expireTime;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_REGISTER")
	private Date lastRegister;

	private String msisdn;

	@Column(name="NUM_REG")
	private BigDecimal numReg;

	@Column(name="NUM_REG_CYCLE")
	private BigDecimal numRegCycle;

	@Column(name="POST_REGISTER_ID")
	private BigDecimal postRegisterId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="SMS_TYPE")
	private String smsType;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdSyncSms50Reg() {
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

	public BigDecimal getNumReg() {
		return this.numReg;
	}

	public void setNumReg(BigDecimal numReg) {
		this.numReg = numReg;
	}

	public BigDecimal getNumRegCycle() {
		return this.numRegCycle;
	}

	public void setNumRegCycle(BigDecimal numRegCycle) {
		this.numRegCycle = numRegCycle;
	}

	public BigDecimal getPostRegisterId() {
		return this.postRegisterId;
	}

	public void setPostRegisterId(BigDecimal postRegisterId) {
		this.postRegisterId = postRegisterId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSmsType() {
		return this.smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
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