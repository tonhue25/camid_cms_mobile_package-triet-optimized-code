package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_CDR_GPRS database table.
 * 
 */
@Entity
@Table(name="BI_CDR_GPRS")
@NamedQuery(name="BiCdrGpr.findAll", query="SELECT b FROM BiCdrGpr b")
public class BiCdrGpr implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal apn;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	private String called;

	private String calling;

	private BigDecimal charge;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal download;

	private BigDecimal duration;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="OPEAK_TYPE")
	private String opeakType;

	private String paid;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TIME_ZONE_ID")
	private BigDecimal timeZoneId;

	private BigDecimal upload;

	private String url;

	public BiCdrGpr() {
	}

	public BigDecimal getApn() {
		return this.apn;
	}

	public void setApn(BigDecimal apn) {
		this.apn = apn;
	}

	public Date getBillDatetime() {
		return this.billDatetime;
	}

	public void setBillDatetime(Date billDatetime) {
		this.billDatetime = billDatetime;
	}

	public String getCalled() {
		return this.called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getCalling() {
		return this.calling;
	}

	public void setCalling(String calling) {
		this.calling = calling;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDownload() {
		return this.download;
	}

	public void setDownload(BigDecimal download) {
		this.download = download;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public String getOpeakType() {
		return this.opeakType;
	}

	public void setOpeakType(String opeakType) {
		this.opeakType = opeakType;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
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

	public BigDecimal getTimeZoneId() {
		return this.timeZoneId;
	}

	public void setTimeZoneId(BigDecimal timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public BigDecimal getUpload() {
		return this.upload;
	}

	public void setUpload(BigDecimal upload) {
		this.upload = upload;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}