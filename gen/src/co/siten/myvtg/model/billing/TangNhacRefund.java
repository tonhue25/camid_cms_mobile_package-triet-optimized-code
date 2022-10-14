package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TANG_NHAC_REFUND database table.
 * 
 */
@Entity
@Table(name="TANG_NHAC_REFUND")
@NamedQuery(name="TangNhacRefund.findAll", query="SELECT t FROM TangNhacRefund t")
public class TangNhacRefund implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	private BigDecimal charge;

	@Column(name="CHARGING_ID")
	private String chargingId;

	@Column(name="CP_ID")
	private String cpId;

	private String error;

	private String imsi;

	@Column(name="MUSIC_ID")
	private String musicId;

	@Column(name="MUSIC_NAME")
	private String musicName;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public TangNhacRefund() {
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public String getChargingId() {
		return this.chargingId;
	}

	public void setChargingId(String chargingId) {
		this.chargingId = chargingId;
	}

	public String getCpId() {
		return this.cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMusicId() {
		return this.musicId;
	}

	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}

	public String getMusicName() {
		return this.musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}