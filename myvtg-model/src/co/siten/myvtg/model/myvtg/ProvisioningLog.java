package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PROVISIONING_LOG database table.
 * 
 */
@Entity
@Table(name="PROVISIONING_LOG")
@NamedQuery(name="ProvisioningLog.findAll", query="SELECT p FROM ProvisioningLog p")
public class ProvisioningLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "PROVISIONING_LOG_SEQ", allocationSize = 1)
	private BigDecimal id;

	private BigDecimal duration;

	@Column(name="MODIFIED_TIME")
	private Timestamp modifiedTime;

	private String msisdn;

	private String request;

	private String respone;

	@Column(name="RESPONE_CODE")
	private String responeCode;

	public ProvisioningLog() {
	}

	
	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}


	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
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

}