package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_SUM_HOTCHARGE_191_TEMP database table.
 * 
 */
@Entity
@Table(name="UD_SUM_HOTCHARGE_191_TEMP")
@NamedQuery(name="UdSumHotcharge191Temp.findAll", query="SELECT u FROM UdSumHotcharge191Temp u")
public class UdSumHotcharge191Temp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUM_DATAVOLUME")
	private BigDecimal sumDatavolume;

	@Column(name="SUM_DATAVOLUME_FEE")
	private BigDecimal sumDatavolumeFee;

	@Column(name="SUM_TOT_CHARGE")
	private BigDecimal sumTotCharge;

	public UdSumHotcharge191Temp() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
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

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSumDatavolume() {
		return this.sumDatavolume;
	}

	public void setSumDatavolume(BigDecimal sumDatavolume) {
		this.sumDatavolume = sumDatavolume;
	}

	public BigDecimal getSumDatavolumeFee() {
		return this.sumDatavolumeFee;
	}

	public void setSumDatavolumeFee(BigDecimal sumDatavolumeFee) {
		this.sumDatavolumeFee = sumDatavolumeFee;
	}

	public BigDecimal getSumTotCharge() {
		return this.sumTotCharge;
	}

	public void setSumTotCharge(BigDecimal sumTotCharge) {
		this.sumTotCharge = sumTotCharge;
	}

}