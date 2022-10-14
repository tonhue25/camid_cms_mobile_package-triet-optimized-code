package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONFIG_TABLE database table.
 * 
 */
@Entity
@Table(name="CONFIG_TABLE")
@NamedQuery(name="ConfigTable.findAll", query="SELECT c FROM ConfigTable c")
public class ConfigTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONFIG_TABLE_ID")
	private BigDecimal configTableId;

	@Column(name="EXPORT_TABLE_ID")
	private BigDecimal exportTableId;

	@Column(name="FIELD_ID")
	private BigDecimal fieldId;

	@Column(name="FIELD_ORDER")
	private BigDecimal fieldOrder;

	public ConfigTable() {
	}

	public BigDecimal getConfigTableId() {
		return this.configTableId;
	}

	public void setConfigTableId(BigDecimal configTableId) {
		this.configTableId = configTableId;
	}

	public BigDecimal getExportTableId() {
		return this.exportTableId;
	}

	public void setExportTableId(BigDecimal exportTableId) {
		this.exportTableId = exportTableId;
	}

	public BigDecimal getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(BigDecimal fieldId) {
		this.fieldId = fieldId;
	}

	public BigDecimal getFieldOrder() {
		return this.fieldOrder;
	}

	public void setFieldOrder(BigDecimal fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

}