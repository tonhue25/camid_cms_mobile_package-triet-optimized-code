package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the VTCI_AREA database table.
 * 
 */
@Entity
@Table(name="VTCI_AREA")
@NamedQuery(name="VtciArea.findAll", query="SELECT v FROM VtciArea v")
public class VtciArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AREA_CODE")
	private String areaCode;

	private String district;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="FULL_NAME")
	private String fullName;

	private String huyen;

	private String kv;

	private String precinct;

	private String province;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	private String xa;

	public VtciArea() {
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHuyen() {
		return this.huyen;
	}

	public void setHuyen(String huyen) {
		this.huyen = huyen;
	}

	public String getKv() {
		return this.kv;
	}

	public void setKv(String kv) {
		this.kv = kv;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public String getXa() {
		return this.xa;
	}

	public void setXa(String xa) {
		this.xa = xa;
	}

}