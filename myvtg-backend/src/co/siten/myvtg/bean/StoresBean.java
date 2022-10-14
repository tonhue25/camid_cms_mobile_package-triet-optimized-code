package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class StoresBean {
	private String name;
	private String addr;
	private String openTime;
	private String provinceName;
	private String districtName;
	private String isdn;
	private Double distance;
	private BigDecimal latitude;
	private BigDecimal longitude;
	public StoresBean(String name, String addr, String openTime, String provinceName, String districtName, String isdn,
			Double distance, BigDecimal latitude, BigDecimal longitude) {
		super();
		this.name = name;
		this.addr = addr;
		this.openTime = openTime;
		this.provinceName = provinceName;
		this.districtName = districtName;
		this.isdn = isdn;
		this.distance = distance;
		this.latitude = latitude;
		this.longitude = longitude;
	}
        
        public StoresBean(String name, String addr, String openTime, String provinceName, String districtName, String isdn,
			Integer distance, BigDecimal latitude, BigDecimal longitude) {
		super();
		this.name = name;
		this.addr = addr;
		this.openTime = openTime;
		this.provinceName = provinceName;
		this.districtName = districtName;
		this.isdn = isdn;
		this.distance = new Double(distance);
		this.latitude = latitude;
		this.longitude = longitude;
	}
        
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getIsdn() {
		return isdn;
	}
	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

}
