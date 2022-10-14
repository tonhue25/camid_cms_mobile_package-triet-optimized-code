package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "FIELD" database table.
 * 
 */
@Entity
@Table(name="\"FIELD\"")
@NamedQuery(name="Field.findAll", query="SELECT f FROM Field f")
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FIELD_ID")
	private BigDecimal fieldId;

	@Column(name="FIELD_NAME")
	private String fieldName;

	@Column(name="FIELD_TYPE")
	private String fieldType;

	public Field() {
	}

	public BigDecimal getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(BigDecimal fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

}