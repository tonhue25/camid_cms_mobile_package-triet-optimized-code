package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the UD_TH195_SUB database table.
 * 
 */
@Entity
@Table(name="UD_TH195_SUB")
@NamedQuery(name="UdTh195Sub.findAll", query="SELECT u FROM UdTh195Sub u")
public class UdTh195Sub implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public UdTh195Sub() {
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

}