package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TEMPLATE_CONFIG database table.
 * 
 */
@Entity
@Table(name="TEMPLATE_CONFIG")
@NamedQuery(name="TemplateConfig.findAll", query="SELECT t FROM TemplateConfig t")
public class TemplateConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal align;

	private String color;

	@Column(name="FIELD_NAME")
	private String fieldName;

	@Column(name="FONT_NAME")
	private String fontName;

	@Column(name="FONT_SIZE")
	private BigDecimal fontSize;

	@Column(name="FONT_STYLE")
	private BigDecimal fontStyle;

	@Column(name="FUNCTION_ID")
	private BigDecimal functionId;

	private BigDecimal height;

	@Column(name="ITEM_ID")
	private BigDecimal itemId;

	@Column(name="ITEM_NAME")
	private String itemName;

	@Column(name="KMLN_ID")
	private BigDecimal kmlnId;

	@Column(name="PAGE_NUMBER")
	private BigDecimal pageNumber;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	private BigDecimal width;

	@Column(name="X_POS")
	private BigDecimal xPos;

	@Column(name="Y_POS")
	private BigDecimal yPos;

	public TemplateConfig() {
	}

	public BigDecimal getAlign() {
		return this.align;
	}

	public void setAlign(BigDecimal align) {
		this.align = align;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFontName() {
		return this.fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public BigDecimal getFontSize() {
		return this.fontSize;
	}

	public void setFontSize(BigDecimal fontSize) {
		this.fontSize = fontSize;
	}

	public BigDecimal getFontStyle() {
		return this.fontStyle;
	}

	public void setFontStyle(BigDecimal fontStyle) {
		this.fontStyle = fontStyle;
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

	public BigDecimal getKmlnId() {
		return this.kmlnId;
	}

	public void setKmlnId(BigDecimal kmlnId) {
		this.kmlnId = kmlnId;
	}

	public BigDecimal getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(BigDecimal pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
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