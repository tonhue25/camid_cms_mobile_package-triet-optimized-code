package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PACKAGES_VERSION database table.
 * 
 */
@Entity
@Table(name="PACKAGES_VERSION")
@NamedQuery(name="PackagesVersion.findAll", query="SELECT p FROM PackagesVersion p")
public class PackagesVersion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_PROFILE")
	private String accountProfile;

	@Column(name="ADMIN_ITEM_ID")
	private BigDecimal adminItemId;

	@Column(name="CCS_ID")
	private BigDecimal ccsId;

	@Column(name="CHARGE_METHOD")
	private String chargeMethod;

	@Column(name="DAILY_FEE")
	private BigDecimal dailyFee;

	@Column(name="DAYFEE_ONLY")
	private BigDecimal dayfeeOnly;

	private String description;

	@Column(name="\"DOMAIN\"")
	private String domain;

	@Column(name="DOWNLOAD_SPEED")
	private BigDecimal downloadSpeed;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_FROM")
	private Date effectFrom;

	@Column(name="MAX_CHARGE")
	private BigDecimal maxCharge;

	@Column(name="MIN_CHARGE")
	private BigDecimal minCharge;

	@Column(name="MONFEE_ONLY")
	private BigDecimal monfeeOnly;

	@Column(name="MONTHLY_FEE")
	private BigDecimal monthlyFee;

	@Column(name="PACKAGE_ID")
	private BigDecimal packageId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"UNTIL\"")
	private Date until;

	@Column(name="UPLOAD_SPEED")
	private BigDecimal uploadSpeed;

	@Column(name="VERSION_NO")
	private String versionNo;

	public PackagesVersion() {
	}

	public String getAccountProfile() {
		return this.accountProfile;
	}

	public void setAccountProfile(String accountProfile) {
		this.accountProfile = accountProfile;
	}

	public BigDecimal getAdminItemId() {
		return this.adminItemId;
	}

	public void setAdminItemId(BigDecimal adminItemId) {
		this.adminItemId = adminItemId;
	}

	public BigDecimal getCcsId() {
		return this.ccsId;
	}

	public void setCcsId(BigDecimal ccsId) {
		this.ccsId = ccsId;
	}

	public String getChargeMethod() {
		return this.chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public BigDecimal getDailyFee() {
		return this.dailyFee;
	}

	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}

	public BigDecimal getDayfeeOnly() {
		return this.dayfeeOnly;
	}

	public void setDayfeeOnly(BigDecimal dayfeeOnly) {
		this.dayfeeOnly = dayfeeOnly;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public BigDecimal getDownloadSpeed() {
		return this.downloadSpeed;
	}

	public void setDownloadSpeed(BigDecimal downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public Date getEffectFrom() {
		return this.effectFrom;
	}

	public void setEffectFrom(Date effectFrom) {
		this.effectFrom = effectFrom;
	}

	public BigDecimal getMaxCharge() {
		return this.maxCharge;
	}

	public void setMaxCharge(BigDecimal maxCharge) {
		this.maxCharge = maxCharge;
	}

	public BigDecimal getMinCharge() {
		return this.minCharge;
	}

	public void setMinCharge(BigDecimal minCharge) {
		this.minCharge = minCharge;
	}

	public BigDecimal getMonfeeOnly() {
		return this.monfeeOnly;
	}

	public void setMonfeeOnly(BigDecimal monfeeOnly) {
		this.monfeeOnly = monfeeOnly;
	}

	public BigDecimal getMonthlyFee() {
		return this.monthlyFee;
	}

	public void setMonthlyFee(BigDecimal monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public BigDecimal getPackageId() {
		return this.packageId;
	}

	public void setPackageId(BigDecimal packageId) {
		this.packageId = packageId;
	}

	public Date getUntil() {
		return this.until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public BigDecimal getUploadSpeed() {
		return this.uploadSpeed;
	}

	public void setUploadSpeed(BigDecimal uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}

	public String getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

}