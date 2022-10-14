package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_USAGE_CHARGE_SMS_TEMP database table.
 * 
 */
@Entity
@Table(name="SUB_USAGE_CHARGE_SMS_TEMP")
@NamedQuery(name="SubUsageChargeSmsTemp.findAll", query="SELECT s FROM SubUsageChargeSmsTemp s")
public class SubUsageChargeSmsTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal cuocphatsinh;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubUsageChargeSmsTemp() {
	}

	public BigDecimal getCuocphatsinh() {
		return this.cuocphatsinh;
	}

	public void setCuocphatsinh(BigDecimal cuocphatsinh) {
		this.cuocphatsinh = cuocphatsinh;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}