package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the OBJECT_MAP database table.
 * 
 */
@Entity
@Table(name="OBJECT_MAP")
@NamedQuery(name="ObjectMap.findAll", query="SELECT o FROM ObjectMap o")
public class ObjectMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="OBJECT_MAP_CODE")
	private String objectMapCode;

	@Column(name="OBJECT_MAP_ID")
	private BigDecimal objectMapId;

	private String param;

	@Column(name="SQL_COMMAND")
	private String sqlCommand;

	public ObjectMap() {
	}

	public String getObjectMapCode() {
		return this.objectMapCode;
	}

	public void setObjectMapCode(String objectMapCode) {
		this.objectMapCode = objectMapCode;
	}

	public BigDecimal getObjectMapId() {
		return this.objectMapId;
	}

	public void setObjectMapId(BigDecimal objectMapId) {
		this.objectMapId = objectMapId;
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

}