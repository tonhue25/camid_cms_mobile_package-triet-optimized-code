package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ROAMING_WARNING_MESSAGE database table.
 * 
 */
@Entity
@Table(name="ROAMING_WARNING_MESSAGE")
@NamedQuery(name="RoamingWarningMessage.findAll", query="SELECT r FROM RoamingWarningMessage r")
public class RoamingWarningMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String isdn;

	@Column(name="ROAMING_CHARGE")
	private BigDecimal roamingCharge;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TOT_CHARGE")
	private BigDecimal totCharge;

	public RoamingWarningMessage() {
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getRoamingCharge() {
		return this.roamingCharge;
	}

	public void setRoamingCharge(BigDecimal roamingCharge) {
		this.roamingCharge = roamingCharge;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getTotCharge() {
		return this.totCharge;
	}

	public void setTotCharge(BigDecimal totCharge) {
		this.totCharge = totCharge;
	}

}