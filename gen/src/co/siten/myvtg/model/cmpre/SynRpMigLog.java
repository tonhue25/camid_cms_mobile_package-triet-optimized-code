package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYN_RP_MIG_LOG database table.
 * 
 */
@Entity
@Table(name="SYN_RP_MIG_LOG")
@NamedQuery(name="SynRpMigLog.findAll", query="SELECT s FROM SynRpMigLog s")
public class SynRpMigLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String app;

	private String description;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="TABLE_NAME")
	private String tableName;

	public SynRpMigLog() {
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}