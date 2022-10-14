package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_INFO database table.
 * 
 */
@Entity
@Table(name="SUB_INFO")
@NamedQuery(name="SubInfo.findAll", query="SELECT s FROM SubInfo s")
public class SubInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="DEPLOY_ADDRESS")
	private String deployAddress;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_ZONE_ID")
	private BigDecimal subZoneId;

	@Column(name="ZONE_ID")
	private BigDecimal zoneId;

	public SubInfo() {
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDeployAddress() {
		return this.deployAddress;
	}

	public void setDeployAddress(String deployAddress) {
		this.deployAddress = deployAddress;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSubZoneId() {
		return this.subZoneId;
	}

	public void setSubZoneId(BigDecimal subZoneId) {
		this.subZoneId = subZoneId;
	}

	public BigDecimal getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(BigDecimal zoneId) {
		this.zoneId = zoneId;
	}

}