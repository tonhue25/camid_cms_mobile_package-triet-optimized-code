package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ISDN_HLR database table.
 * 
 */
@Entity
@Table(name="ISDN_HLR")
@NamedQuery(name="IsdnHlr.findAll", query="SELECT i FROM IsdnHlr i")
public class IsdnHlr implements Serializable {
	private static final long serialVersionUID = 1L;

	private String center;

	@Column(name="HLR_ID")
	private BigDecimal hlrId;

	@Column(name="ISDN_PREFIX")
	private String isdnPrefix;

	@Column(name="MOB_TYPE")
	private String mobType;

	public IsdnHlr() {
	}

	public String getCenter() {
		return this.center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public BigDecimal getHlrId() {
		return this.hlrId;
	}

	public void setHlrId(BigDecimal hlrId) {
		this.hlrId = hlrId;
	}

	public String getIsdnPrefix() {
		return this.isdnPrefix;
	}

	public void setIsdnPrefix(String isdnPrefix) {
		this.isdnPrefix = isdnPrefix;
	}

	public String getMobType() {
		return this.mobType;
	}

	public void setMobType(String mobType) {
		this.mobType = mobType;
	}

}