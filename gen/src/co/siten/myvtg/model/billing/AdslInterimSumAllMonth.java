package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ADSL_INTERIM_SUM_ALL_MONTH database table.
 * 
 */
@Entity
@Table(name="ADSL_INTERIM_SUM_ALL_MONTH")
@NamedQuery(name="AdslInterimSumAllMonth.findAll", query="SELECT a FROM AdslInterimSumAllMonth a")
public class AdslInterimSumAllMonth implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;

	@Column(name="CHARGE_DOWNLOAD")
	private BigDecimal chargeDownload;

	@Column(name="CHARGE_UPLOAD")
	private BigDecimal chargeUpload;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal download;

	private BigDecimal duration;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private BigDecimal upload;

	public AdslInterimSumAllMonth() {
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getChargeDownload() {
		return this.chargeDownload;
	}

	public void setChargeDownload(BigDecimal chargeDownload) {
		this.chargeDownload = chargeDownload;
	}

	public BigDecimal getChargeUpload() {
		return this.chargeUpload;
	}

	public void setChargeUpload(BigDecimal chargeUpload) {
		this.chargeUpload = chargeUpload;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getUpload() {
		return this.upload;
	}

	public void setUpload(BigDecimal upload) {
		this.upload = upload;
	}

}