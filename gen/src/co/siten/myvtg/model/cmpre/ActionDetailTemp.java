package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTION_DETAIL_TEMP database table.
 * 
 */
@Entity
@Table(name="ACTION_DETAIL_TEMP")
@NamedQuery(name="ActionDetailTemp.findAll", query="SELECT a FROM ActionDetailTemp a")
public class ActionDetailTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_DETAIL_ID")
	private long actionDetailId;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="COL_NAME")
	private String colName;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="NEW_VALUE")
	private String newValue;

	@Column(name="OLD_VALUE")
	private String oldValue;

	@Column(name="ROW_ID")
	private BigDecimal rowId;

	@Column(name="TABLE_NAME")
	private String tableName;

	public ActionDetailTemp() {
	}

	public long getActionDetailId() {
		return this.actionDetailId;
	}

	public void setActionDetailId(long actionDetailId) {
		this.actionDetailId = actionDetailId;
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Object getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Object issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public String getNewValue() {
		return this.newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOldValue() {
		return this.oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public BigDecimal getRowId() {
		return this.rowId;
	}

	public void setRowId(BigDecimal rowId) {
		this.rowId = rowId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}