package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_DATA database table.
 * 
 */
@Entity
@Table(name="PRINT_DATA")
@NamedQuery(name="PrintData.findAll", query="SELECT p FROM PrintData p")
public class PrintData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRINT_DATA_ID")
	private long printDataId;

	@Column(name="DATA_TYPE")
	private BigDecimal dataType;

	@Column(name="FIELD_NAME")
	private String fieldName;

	private String param;

	@Column(name="SQL_COMMAND")
	private String sqlCommand;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public PrintData() {
	}

	public long getPrintDataId() {
		return this.printDataId;
	}

	public void setPrintDataId(long printDataId) {
		this.printDataId = printDataId;
	}

	public BigDecimal getDataType() {
		return this.dataType;
	}

	public void setDataType(BigDecimal dataType) {
		this.dataType = dataType;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getSqlCommand() {
		return this.sqlCommand;
	}

	public void setSqlCommand(String sqlCommand) {
		this.sqlCommand = sqlCommand;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}