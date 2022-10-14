package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAP_ACTIVE_INFO_LOG database table.
 * 
 */
@Entity
@Table(name="MAP_ACTIVE_INFO_LOG")
@NamedQuery(name="MapActiveInfoLog.findAll", query="SELECT m FROM MapActiveInfoLog m")
public class MapActiveInfoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private BigDecimal id;

	private String ip;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="MAP_ACTIVE_INFO_ID")
	private BigDecimal mapActiveInfoId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	public MapActiveInfoLog() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Object getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Object issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public BigDecimal getMapActiveInfoId() {
		return this.mapActiveInfoId;
	}

	public void setMapActiveInfoId(BigDecimal mapActiveInfoId) {
		this.mapActiveInfoId = mapActiveInfoId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}