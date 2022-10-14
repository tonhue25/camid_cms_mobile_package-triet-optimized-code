package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RECHARGE_HIS database table.
 * 
 */
@Entity
@Table(name="RECHARGE_HIS")
@NamedQuery(name="RechargeHis.findAll", query="SELECT r FROM RechargeHis r")
public class RechargeHis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "RECHARGE_HIS_SEQ", allocationSize = 1)
	private BigDecimal id;

	@Column(name="CARD_TYPE")
	private BigDecimal cardType;

	private String description;

	private String msisdn;

	@Column(name="PROCESS_TIME")
	private Timestamp processTime;

	private String serial;

	@Column(name="SERVICE_TYPE")
	private BigDecimal serviceType;

	public RechargeHis() {
	}


	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}


	public BigDecimal getCardType() {
		return this.cardType;
	}

	public void setCardType(BigDecimal cardType) {
		this.cardType = cardType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Timestamp getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Timestamp processTime) {
		this.processTime = processTime;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public BigDecimal getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(BigDecimal serviceType) {
		this.serviceType = serviceType;
	}

}