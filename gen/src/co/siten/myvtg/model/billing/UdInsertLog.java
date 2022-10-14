package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_INSERT_LOG database table.
 * 
 */
@Entity
@Table(name="UD_INSERT_LOG")
@NamedQuery(name="UdInsertLog.findAll", query="SELECT u FROM UdInsertLog u")
public class UdInsertLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CURR_DATE")
	private Date currDate;

	private String description;

	private BigDecimal id;

	public UdInsertLog() {
	}

	public Date getCurrDate() {
		return this.currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

}