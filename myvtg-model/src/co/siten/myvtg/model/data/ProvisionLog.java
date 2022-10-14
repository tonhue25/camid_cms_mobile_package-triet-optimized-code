package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PROVISION_LOG database table.
 * 
 */
@Entity
@Table(name="PROVISION_LOG")
@NamedQuery(name="ProvisionLog.findAll", query="SELECT p FROM ProvisionLog p")
public class ProvisionLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal duration;
	@Id
	private BigDecimal id;

	@Temporal(TemporalType.DATE)
	@Column(name="MODIFIED_TIME")
	private Date modifiedTime;

	private String msisdn;

	@Column(name="PROCESS_CODE")
	private String processCode;

	private String request;

	private String respone;

	@Column(name="RESPONE_CODE")
	private String responeCode;

	@Column(name="SERVICE_CODE")
	private BigDecimal serviceCode;

	public ProvisionLog() {
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getProcessCode() {
		return this.processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRespone() {
		return this.respone;
	}

	public void setRespone(String respone) {
		this.respone = respone;
	}

	public String getResponeCode() {
		return this.responeCode;
	}

	public void setResponeCode(String responeCode) {
		this.responeCode = responeCode;
	}

	public BigDecimal getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(BigDecimal serviceCode) {
		this.serviceCode = serviceCode;
	}

}