package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CLOSING_LOG database table.
 * 
 */
@Entity
@Table(name="CLOSING_LOG")
@NamedQuery(name="ClosingLog.findAll", query="SELECT c FROM ClosingLog c")
public class ClosingLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DETAIL_LOG")
	private String detailLog;

	@Temporal(TemporalType.DATE)
	@Column(name="PRO_DATETIME")
	private Date proDatetime;

	public ClosingLog() {
	}

	public String getDetailLog() {
		return this.detailLog;
	}

	public void setDetailLog(String detailLog) {
		this.detailLog = detailLog;
	}

	public Date getProDatetime() {
		return this.proDatetime;
	}

	public void setProDatetime(Date proDatetime) {
		this.proDatetime = proDatetime;
	}

}