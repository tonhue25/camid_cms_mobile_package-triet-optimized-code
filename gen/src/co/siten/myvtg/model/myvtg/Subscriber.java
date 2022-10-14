package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SUBSCRIBER database table.
 * 
 */
@Entity
@NamedQuery(name="Subscriber.findAll", query="SELECT s FROM Subscriber s")
public class Subscriber implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriberPK id;

	@Column(name="CANCEL_TIME")
	private Timestamp cancelTime;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="REGISTER_TIME")
	private Timestamp registerTime;

	@Column(name="\"STATE\"")
	private BigDecimal state;

	private BigDecimal status;

	public Subscriber() {
	}

	public SubscriberPK getId() {
		return this.id;
	}

	public void setId(SubscriberPK id) {
		this.id = id;
	}

	public Timestamp getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(Timestamp cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}