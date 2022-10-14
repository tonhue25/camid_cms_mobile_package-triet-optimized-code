package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CATALOG_LOG_DETAIL database table.
 * 
 */
@Entity
@Table(name="CATALOG_LOG_DETAIL")
@NamedQuery(name="CatalogLogDetail.findAll", query="SELECT c FROM CatalogLogDetail c")
public class CatalogLogDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CATALOG_LOG_ID")
	private BigDecimal catalogLogId;

	@Column(name="COL_NAME")
	private String colName;

	private BigDecimal id;

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

	public CatalogLogDetail() {
	}

	public BigDecimal getCatalogLogId() {
		return this.catalogLogId;
	}

	public void setCatalogLogId(BigDecimal catalogLogId) {
		this.catalogLogId = catalogLogId;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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