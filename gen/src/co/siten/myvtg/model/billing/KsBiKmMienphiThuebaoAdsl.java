package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the KS_BI_KM_MIENPHI_THUEBAO_ADSL database table.
 * 
 */
@Entity
@Table(name="KS_BI_KM_MIENPHI_THUEBAO_ADSL")
@NamedQuery(name="KsBiKmMienphiThuebaoAdsl.findAll", query="SELECT k FROM KsBiKmMienphiThuebaoAdsl k")
public class KsBiKmMienphiThuebaoAdsl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

	@Column(name="P_BUS_TYPE")
	private String pBusType;

	@Temporal(TemporalType.DATE)
	@Column(name="P_END_DATE")
	private Date pEndDate;

	@Column(name="P_ISFINISHED")
	private BigDecimal pIsfinished;

	@Column(name="P_ISOLD")
	private BigDecimal pIsold;

	@Column(name="P_MONTH")
	private BigDecimal pMonth;

	@Column(name="P_PACKAGE_ID")
	private String pPackageId;

	@Column(name="P_PROM_CODE")
	private String pPromCode;

	@Column(name="P_PROVINCE_CODE")
	private String pProvinceCode;

	@Temporal(TemporalType.DATE)
	@Column(name="P_STA_DATE")
	private Date pStaDate;

	@Column(name="P_START_MONTH")
	private BigDecimal pStartMonth;

	private BigDecimal status;

	public KsBiKmMienphiThuebaoAdsl() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getPBusType() {
		return this.pBusType;
	}

	public void setPBusType(String pBusType) {
		this.pBusType = pBusType;
	}

	public Date getPEndDate() {
		return this.pEndDate;
	}

	public void setPEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}

	public BigDecimal getPIsfinished() {
		return this.pIsfinished;
	}

	public void setPIsfinished(BigDecimal pIsfinished) {
		this.pIsfinished = pIsfinished;
	}

	public BigDecimal getPIsold() {
		return this.pIsold;
	}

	public void setPIsold(BigDecimal pIsold) {
		this.pIsold = pIsold;
	}

	public BigDecimal getPMonth() {
		return this.pMonth;
	}

	public void setPMonth(BigDecimal pMonth) {
		this.pMonth = pMonth;
	}

	public String getPPackageId() {
		return this.pPackageId;
	}

	public void setPPackageId(String pPackageId) {
		this.pPackageId = pPackageId;
	}

	public String getPPromCode() {
		return this.pPromCode;
	}

	public void setPPromCode(String pPromCode) {
		this.pPromCode = pPromCode;
	}

	public String getPProvinceCode() {
		return this.pProvinceCode;
	}

	public void setPProvinceCode(String pProvinceCode) {
		this.pProvinceCode = pProvinceCode;
	}

	public Date getPStaDate() {
		return this.pStaDate;
	}

	public void setPStaDate(Date pStaDate) {
		this.pStaDate = pStaDate;
	}

	public BigDecimal getPStartMonth() {
		return this.pStartMonth;
	}

	public void setPStartMonth(BigDecimal pStartMonth) {
		this.pStartMonth = pStartMonth;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}