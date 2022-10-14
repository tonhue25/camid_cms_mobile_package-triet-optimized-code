package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_TEMPLATE_CONFIG database table.
 * 
 */
@Entity
@Table(name="MY_TEMPLATE_CONFIG")
@NamedQuery(name="MyTemplateConfig.findAll", query="SELECT m FROM MyTemplateConfig m")
public class MyTemplateConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FIELD_NAME")
	private String fieldName;

	@Column(name="FUNCTION_ID")
	private BigDecimal functionId;

	private BigDecimal height;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="ITEM_NAME")
	private String itemName;

	@Column(name="TABLE_NAME")
	private String tableName;

	private BigDecimal width;

	@Column(name="X_POS")
	private BigDecimal xPos;

	@Column(name="Y_POS")
	private BigDecimal yPos;

	public MyTemplateConfig() {
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public BigDecimal getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(BigDecimal functionId) {
		this.functionId = functionId;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getXPos() {
		return this.xPos;
	}

	public void setXPos(BigDecimal xPos) {
		this.xPos = xPos;
	}

	public BigDecimal getYPos() {
		return this.yPos;
	}

	public void setYPos(BigDecimal yPos) {
		this.yPos = yPos;
	}

}