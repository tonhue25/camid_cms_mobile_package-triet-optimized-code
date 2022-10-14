package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RP_MIG_LOG database table.
 * 
 */
@Entity
@Table(name="RP_MIG_LOG")
@NamedQuery(name="RpMigLog.findAll", query="SELECT r FROM RpMigLog r")
public class RpMigLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String app;

	private String description;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="OBJECT_ID")
	private BigDecimal objectId;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="TABLE_NAME")
	private String tableName;

	public RpMigLog() {
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

	public BigDecimal getObjectId() {
		return this.objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
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