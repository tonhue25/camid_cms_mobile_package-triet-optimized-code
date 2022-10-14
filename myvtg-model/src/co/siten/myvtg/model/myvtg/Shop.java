package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the SHOP database table.
 * 
 */
@Entity
@NamedQuery(name = "Shop.findAll", query = "SELECT s FROM Shop s")
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	private String addr;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "DISTRICT_ID")
	private String districtId;

	private String isdn;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String name;

	@Column(name = "OPEN_TIME")
	private String openTime;

	@Column(name = "PROVINCE_ID")
	private String provinceId;

	private Integer status;

	@Column(name = "\"TYPE\"")
	private BigDecimal type;

	public Shop() {
	}

	public String getId() {
		return id;
	}

	public String getAddr() {
		return addr;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getDistrictId() {
		return districtId;
	}

	public String getIsdn() {
		return isdn;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public String getOpenTime() {
		return openTime;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public Integer getStatus() {
		return status;
	}

	public BigDecimal getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}