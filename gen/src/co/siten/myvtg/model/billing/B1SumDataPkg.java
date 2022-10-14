package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the B1_SUM_DATA_PKG database table.
 * 
 */
@Entity
@Table(name="B1_SUM_DATA_PKG")
@NamedQuery(name="B1SumDataPkg.findAll", query="SELECT b FROM B1SumDataPkg b")
public class B1SumDataPkg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATA_VOLUME")
	private BigDecimal dataVolume;

	@Column(name="PACKAGE_TYPE")
	private String packageType;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public B1SumDataPkg() {
	}

	public BigDecimal getDataVolume() {
		return this.dataVolume;
	}

	public void setDataVolume(BigDecimal dataVolume) {
		this.dataVolume = dataVolume;
	}

	public String getPackageType() {
		return this.packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}