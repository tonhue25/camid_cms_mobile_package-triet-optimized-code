package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the LOG_RUN database table.
 * 
 */
@Entity
@Table(name="LOG_RUN")
@NamedQuery(name="LogRun.findAll", query="SELECT l FROM LogRun l")
public class LogRun implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="RUN_TIME")
	private Date runTime;

	public LogRun() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRunTime() {
		return this.runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

}