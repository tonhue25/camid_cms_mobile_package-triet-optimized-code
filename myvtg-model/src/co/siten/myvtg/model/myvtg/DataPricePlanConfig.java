package co.siten.myvtg.model.myvtg;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the DATA_PRICE_PLAN_CONFIG database table.
 * 
 */
@Entity
@Table(name = "DATA_PRICE_PLAN_CONFIG")
@NamedQuery(name = "DataPricePlanConfig.findAll", query = "SELECT d FROM DataPricePlanConfig d")
public class DataPricePlanConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name = "ACC_CODE")
	private Integer accCode;

	@Column(name = "PACKAGE_CODE")
	private String packageCode;

	@Column(name = "PACKAGE_NAME")
	private String packageName;

	@Column(name = "PRICE_PLAN")
	private Integer pricePlan;
        
        @Column(name = "SIM_TYPE")
	private Integer simType;

	public DataPricePlanConfig() {
	}

	public long getId() {
		return this.id;
	}

	public Integer getAccCode() {
		return accCode;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public String getPackageName() {
		return packageName;
	}

	public Integer getPricePlan() {
		return pricePlan;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAccCode(Integer accCode) {
		this.accCode = accCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setPricePlan(Integer pricePlan) {
		this.pricePlan = pricePlan;
	}

        public Integer getSimType() {
                return simType;
        }

        public void setSimType(Integer simType) {
                this.simType = simType;
        }
}