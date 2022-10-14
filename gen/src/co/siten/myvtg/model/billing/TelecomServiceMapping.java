package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TELECOM_SERVICE_MAPPING database table.
 * 
 */
@Entity
@Table(name="TELECOM_SERVICE_MAPPING")
@NamedQuery(name="TelecomServiceMapping.findAll", query="SELECT t FROM TelecomServiceMapping t")
public class TelecomServiceMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private String status;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public TelecomServiceMapping() {
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}