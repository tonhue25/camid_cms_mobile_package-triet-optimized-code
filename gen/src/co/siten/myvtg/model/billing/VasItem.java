package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the VAS_ITEMS database table.
 * 
 */
@Entity
@Table(name="VAS_ITEMS")
@NamedQuery(name="VasItem.findAll", query="SELECT v FROM VasItem v")
public class VasItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VAS_ID")
	private long vasId;

	@Column(name="SERVICE_NAME")
	private String serviceName;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="VAS_GROUP_ID")
	private BigDecimal vasGroupId;

	public VasItem() {
	}

	public long getVasId() {
		return this.vasId;
	}

	public void setVasId(long vasId) {
		this.vasId = vasId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getVasGroupId() {
		return this.vasGroupId;
	}

	public void setVasGroupId(BigDecimal vasGroupId) {
		this.vasGroupId = vasGroupId;
	}

}