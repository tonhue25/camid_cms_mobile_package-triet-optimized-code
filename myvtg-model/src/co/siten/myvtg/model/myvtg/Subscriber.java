package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SUBSCRIBER database table.
 * 
 */
@Entity
@NamedQuery(name = "Subscriber.findAll", query = "SELECT s FROM Subscriber s")
public class Subscriber implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriberPK id;

	@Column(name = "CANCEL_TIME")
	private Timestamp cancelTime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "REGISTER_TIME")
	private Date registerTime;

	@Column(name = "\"STATE\"")
	private Integer state;

	private Integer status;
        
        @Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
        private Date lastUpdatedTime;
        
        @Column(name = "SUB_ID")
        private Long subId;

	public Subscriber() {
	}

	public SubscriberPK getId() {
		return this.id;
	}

	public void setId(SubscriberPK id) {
		this.id = id;
	}

	public Timestamp getCancelTime() {
		return cancelTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public Integer getState() {
		return state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCancelTime(Timestamp cancelTime) {
		this.cancelTime = cancelTime;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

        public Date getLastUpdatedTime() {
                return lastUpdatedTime;
        }

        public void setLastUpdatedTime(Date lastUpdatedTime) {
                this.lastUpdatedTime = lastUpdatedTime;
        }

        public Long getSubId() {
                return subId;
        }

        public void setSubId(Long subId) {
                this.subId = subId;
        }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Subscriber) && (((Subscriber) obj).id == this.id);
    }

    @Override
    public int hashCode() {
    	return super.hashCode();
    }
        
}