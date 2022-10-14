package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TS_MAPPING_VTT_STL database table.
 * 
 */
@Entity
@Table(name="TS_MAPPING_VTT_STL")
@NamedQuery(name="TsMappingVttStl.findAll", query="SELECT t FROM TsMappingVttStl t")
public class TsMappingVttStl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal status;

	@Column(name="STL_SERVICE_TYPE")
	private String stlServiceType;

	@Column(name="STL_TELECOM_SERVICE_ID")
	private BigDecimal stlTelecomServiceId;

	@Column(name="VTT_SERVICE_TYPE")
	private String vttServiceType;

	@Column(name="VTT_TELECOM_SERVICE_ID")
	private BigDecimal vttTelecomServiceId;

	public TsMappingVttStl() {
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getStlServiceType() {
		return this.stlServiceType;
	}

	public void setStlServiceType(String stlServiceType) {
		this.stlServiceType = stlServiceType;
	}

	public BigDecimal getStlTelecomServiceId() {
		return this.stlTelecomServiceId;
	}

	public void setStlTelecomServiceId(BigDecimal stlTelecomServiceId) {
		this.stlTelecomServiceId = stlTelecomServiceId;
	}

	public String getVttServiceType() {
		return this.vttServiceType;
	}

	public void setVttServiceType(String vttServiceType) {
		this.vttServiceType = vttServiceType;
	}

	public BigDecimal getVttTelecomServiceId() {
		return this.vttTelecomServiceId;
	}

	public void setVttTelecomServiceId(BigDecimal vttTelecomServiceId) {
		this.vttTelecomServiceId = vttTelecomServiceId;
	}

}