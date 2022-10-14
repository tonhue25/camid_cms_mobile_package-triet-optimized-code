package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_DATA_BK26052015 database table.
 * 
 */
@Entity
@Table(name="PRINT_DATA_BK26052015")
@NamedQuery(name="PrintDataBk26052015.findAll", query="SELECT p FROM PrintDataBk26052015 p")
public class PrintDataBk26052015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATA_TYPE")
	private BigDecimal dataType;

	@Column(name="FIELD_NAME")
	private String fieldName;

	private String param;

	@Column(name="PRINT_DATA_ID")
	private BigDecimal printDataId;

	@Column(name="SQL_COMMAND")
	private String sqlCommand;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public PrintDataBk26052015() {
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

	public BigDecimal getPrintDataId() {
		return this.printDataId;
	}

	public void setPrintDataId(BigDecimal printDataId) {
		this.printDataId = printDataId;
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