package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_OTP_HIS database table.
 * 
 */
@Entity
@Table(name="SUB_OTP_HIS")
@NamedQuery(name="SubOtpHi.findAll", query="SELECT s FROM SubOtpHi s")
public class SubOtpHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_DATE")
	private Date expireDate;

	@Temporal(TemporalType.DATE)
	@Column(name="INSERT_DATE")
	private Date insertDate;

	private String isdn;

	@Column(name="MAX_VALIDATE")
	private BigDecimal maxValidate;

	@Column(name="NUM_VALIDATE")
	private BigDecimal numValidate;

	private String otp;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubOtpHi() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getMaxValidate() {
		return this.maxValidate;
	}

	public void setMaxValidate(BigDecimal maxValidate) {
		this.maxValidate = maxValidate;
	}

	public BigDecimal getNumValidate() {
		return this.numValidate;
	}

	public void setNumValidate(BigDecimal numValidate) {
		this.numValidate = numValidate;
	}

	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}