package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HAPPY_ZONE database table.
 * 
 */
@Entity
@Table(name="HAPPY_ZONE")
@NamedQuery(name="HappyZone.findAll", query="SELECT h FROM HappyZone h")
public class HappyZone implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	@Column(name="DISTRICT_CODE")
	private String districtCode;

	@Column(name="PRECINCT_CODE")
	private String precinctCode;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="ZONE_CODE")
	private String zoneCode;

	@Column(name="ZONE_ID")
	private BigDecimal zoneId;

	@Column(name="ZONE_NAME")
	private String zoneName;

	public HappyZone() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getPrecinctCode() {
		return this.precinctCode;
	}

	public void setPrecinctCode(String precinctCode) {
		this.precinctCode = precinctCode;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getZoneCode() {
		return this.zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public BigDecimal getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(BigDecimal zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

}