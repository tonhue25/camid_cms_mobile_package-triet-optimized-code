package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the NIS_SUB database table.
 * 
 */
@Entity
@Table(name="NIS_SUB")
@NamedQuery(name="NisSub.findAll", query="SELECT n FROM NisSub n")
public class NisSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="FULL_INFO")
	private String fullInfo;

	@Column(name="HAS_IMAGE")
	private String hasImage;

	private String isdn;

	@Column(name="NIS_CUST_ID")
	private BigDecimal nisCustId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	public NisSub() {
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getFullInfo() {
		return this.fullInfo;
	}

	public void setFullInfo(String fullInfo) {
		this.fullInfo = fullInfo;
	}

	public String getHasImage() {
		return this.hasImage;
	}

	public void setHasImage(String hasImage) {
		this.hasImage = hasImage;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getNisCustId() {
		return this.nisCustId;
	}

	public void setNisCustId(BigDecimal nisCustId) {
		this.nisCustId = nisCustId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}