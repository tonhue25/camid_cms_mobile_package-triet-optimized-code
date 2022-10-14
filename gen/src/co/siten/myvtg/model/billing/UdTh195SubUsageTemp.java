package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_TH195_SUB_USAGE_TEMP database table.
 * 
 */
@Entity
@Table(name="UD_TH195_SUB_USAGE_TEMP")
@NamedQuery(name="UdTh195SubUsageTemp.findAll", query="SELECT u FROM UdTh195SubUsageTemp u")
public class UdTh195SubUsageTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal charge;

	private String isdn;

	public UdTh195SubUsageTemp() {
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

}