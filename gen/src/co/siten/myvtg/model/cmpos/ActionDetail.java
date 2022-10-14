package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTION_DETAIL database table.
 * 
 */
@Entity
@Table(name="ACTION_DETAIL")
@NamedQuery(name="ActionDetail.findAll", query="SELECT a FROM ActionDetail a")
public class ActionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActionDetailPK id;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Column(name="COL_NAME")
	private String colName;

	@Column(name="NEW_VALUE")
	private String newValue;

	@Column(name="OLD_VALUE")
	private String oldValue;

	@Column(name="ROW_ID")
	private BigDecimal rowId;

	@Column(name="TABLE_NAME")
	private String tableName;

	public ActionDetail() {
	}

	public ActionDetailPK getId() {
		return this.id;
	}

	public void setId(ActionDetailPK id) {
		this.id = id;
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