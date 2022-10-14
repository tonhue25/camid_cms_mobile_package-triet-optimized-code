package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;

import co.siten.myvtg.config.Config;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ACTION_DETAIL database table.
 * 
 */
@Entity
@Table(name="ACTION_DETAIL")
@NamedQuery(name="ActionDetail.findAll", query="SELECT a FROM ActionDetail a")
public class ActionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_AUDIT_ID")
	private BigDecimal actionAuditId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_CM_POS_ACTION_DETAIL, allocationSize = 1)
	@Column(name="ACTION_DETAIL_ID")
	private BigDecimal actionDetailId;


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

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATETIME")
	private java.util.Date issueDatetime;
	
	public ActionDetail() {
	}
	

	public ActionDetail(BigDecimal actionAuditId, String colName, Date issueDatetime, String newValue,
			String oldValue, BigDecimal rowId, String tableName) {
		super();
		this.actionAuditId = actionAuditId;
		this.colName = colName;
		this.issueDatetime = issueDatetime;
		this.newValue = newValue;
		this.oldValue = oldValue;
		this.rowId = rowId;
		this.tableName = tableName;
	}


	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}
	

	public BigDecimal getActionDetailId() {
		return actionDetailId;
	}

	public java.util.Date getIssueDatetime() {
		return issueDatetime;
	}

	public void setActionDetailId(BigDecimal actionDetailId) {
		this.actionDetailId = actionDetailId;
	}

	public void setIssueDatetime(java.util.Date issueDatetime) {
		this.issueDatetime = issueDatetime;
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