package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the AUTO_DELETE_SUBINFO_LOG database table.
 * 
 */
@Entity
@Table(name="AUTO_DELETE_SUBINFO_LOG")
@NamedQuery(name="AutoDeleteSubinfoLog.findAll", query="SELECT a FROM AutoDeleteSubinfoLog a")
public class AutoDeleteSubinfoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="ROW_ID")
	private String rowId;

	public AutoDeleteSubinfoLog() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Object issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getRowId() {
		return this.rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

}