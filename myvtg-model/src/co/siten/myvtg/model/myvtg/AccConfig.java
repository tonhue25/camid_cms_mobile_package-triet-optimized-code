package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ACC_CONFIG database table.
 * 
 */
@Entity
@Table(name = "ACC_CONFIG")
@NamedQuery(name = "AccConfig.findAll", query = "SELECT a FROM AccConfig a")
public class AccConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name = "ACC_CODE")
	private Integer accCode;

	@Column(name = "ACC_NAME")
	private String accName;

	@Column(name = "ACC_ORDER")
	private Integer accOrder;

	@Column(name = "ACC_TYPE")
	private Integer accType;

	private String des;

	private String language;

	private BigDecimal unit;

	@Column(name = "UNIT_LABEL")
	private String unitLabel;

	public AccConfig() {
	}

	public Integer getAccOrder() {
		return accOrder;
	}

	public void setAccOrder(Integer accOrder) {
		this.accOrder = accOrder;
	}

	public long getId() {
		return id;
	}

	public Integer getAccCode() {
		return accCode;
	}

	public String getAccName() {
		return accName;
	}

	public Integer getAccType() {
		return accType;
	}

	public String getDes() {
		return des;
	}

	public BigDecimal getUnit() {
		return unit;
	}

	public String getUnitLabel() {
		return unitLabel;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAccCode(Integer accCode) {
		this.accCode = accCode;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setAccType(Integer accType) {
		this.accType = accType;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public void setUnitLabel(String unitLabel) {
		this.unitLabel = unitLabel;
	}

}