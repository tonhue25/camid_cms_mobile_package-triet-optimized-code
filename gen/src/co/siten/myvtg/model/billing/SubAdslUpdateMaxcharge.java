package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_ADSL_UPDATE_MAXCHARGE database table.
 * 
 */
@Entity
@Table(name="SUB_ADSL_UPDATE_MAXCHARGE")
@NamedQuery(name="SubAdslUpdateMaxcharge.findAll", query="SELECT s FROM SubAdslUpdateMaxcharge s")
public class SubAdslUpdateMaxcharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date created;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubAdslUpdateMaxcharge() {
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}