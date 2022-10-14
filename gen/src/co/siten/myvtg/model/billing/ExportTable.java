package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EXPORT_TABLE database table.
 * 
 */
@Entity
@Table(name="EXPORT_TABLE")
@NamedQuery(name="ExportTable.findAll", query="SELECT e FROM ExportTable e")
public class ExportTable implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="EXPORT_TABLE_ID")
	private BigDecimal exportTableId;

	@Column(name="KEY_BY_NUMBER")
	private String keyByNumber;

	@Column(name="TABLE_NAME")
	private String tableName;

	public ExportTable() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getExportTableId() {
		return this.exportTableId;
	}

	public void setExportTableId(BigDecimal exportTableId) {
		this.exportTableId = exportTableId;
	}

	public String getKeyByNumber() {
		return this.keyByNumber;
	}

	public void setKeyByNumber(String keyByNumber) {
		this.keyByNumber = keyByNumber;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}