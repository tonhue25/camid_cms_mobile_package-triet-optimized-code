package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "SERVICE" database table.
 * 
 */
@Entity
@Table(name="\"SERVICE\"")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DB_CONFIG")
	private String dbConfig;

	private String description;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="SEPARATOR_CHAR")
	private String separatorChar;

	@Column(name="SERVER_ID")
	private BigDecimal serverId;

	@Column(name="SERVICE_ID")
	private BigDecimal serviceId;

	@Column(name="SERVICE_NAME")
	private String serviceName;

	public Service() {
	}

	public String getDbConfig() {
		return this.dbConfig;
	}

	public void setDbConfig(String dbConfig) {
		this.dbConfig = dbConfig;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSeparatorChar() {
		return this.separatorChar;
	}

	public void setSeparatorChar(String separatorChar) {
		this.separatorChar = separatorChar;
	}

	public BigDecimal getServerId() {
		return this.serverId;
	}

	public void setServerId(BigDecimal serverId) {
		this.serverId = serverId;
	}

	public BigDecimal getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(BigDecimal serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}