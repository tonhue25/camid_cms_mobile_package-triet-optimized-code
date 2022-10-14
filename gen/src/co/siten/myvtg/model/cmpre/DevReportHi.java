package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DEV_REPORT_HIS database table.
 * 
 */
@Entity
@Table(name="DEV_REPORT_HIS")
@NamedQuery(name="DevReportHi.findAll", query="SELECT d FROM DevReportHi d")
public class DevReportHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="REPORT_DATE_HISTORY")
	private Object reportDateHistory;

	private String status;

	public DevReportHi() {
	}

	public Object getReportDateHistory() {
		return this.reportDateHistory;
	}

	public void setReportDateHistory(Object reportDateHistory) {
		this.reportDateHistory = reportDateHistory;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}