package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATA_PRICE_PLAN_CONFIG database table.
 * 
 */
@Entity
@Table(name="DATA_PRICE_PLAN_CONFIG")
@NamedQuery(name="DataPricePlanConfig.findAll", query="SELECT d FROM DataPricePlanConfig d")
public class DataPricePlanConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACC_CODE")
	private BigDecimal accCode;

	@Column(name="PACKAGE_CODE")
	private String packageCode;

	@Column(name="PACKAGE_NAME")
	private String packageName;

	@Column(name="PRICE_PLAN")
	private BigDecimal pricePlan;

	public DataPricePlanConfig() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAccCode() {
		return this.accCode;
	}

	public void setAccCode(BigDecimal accCode) {
		this.accCode = accCode;
	}

	public String getPackageCode() {
		return this.packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public BigDecimal getPricePlan() {
		return this.pricePlan;
	}

	public void setPricePlan(BigDecimal pricePlan) {
		this.pricePlan = pricePlan;
	}

}