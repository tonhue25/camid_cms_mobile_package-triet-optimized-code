package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_SHAPE_TABLE database table.
 * 
 */
@Entity
@Table(name="PRINT_SHAPE_TABLE")
@NamedQuery(name="PrintShapeTable.findAll", query="SELECT p FROM PrintShapeTable p")
public class PrintShapeTable implements Serializable {
	private static final long serialVersionUID = 1L;

	private String align;

	@Column(name="COLUMN_WIDTH")
	private String columnWidth;

	private String description;

	@Column(name="GET_FPT")
	private BigDecimal getFpt;

	private String kmln;

	@Column(name="NUM_APPEAR_IN_ROW")
	private BigDecimal numAppearInRow;

	@Column(name="NUM_COLUMN")
	private BigDecimal numColumn;

	@Column(name="NUM_ROW")
	private BigDecimal numRow;

	private String param;

	@Column(name="PRINT_SHAPE_TABLE_ID")
	private BigDecimal printShapeTableId;

	@Column(name="ROW_HEIGHT")
	private String rowHeight;

	@Lob
	private String sqlcommand;

	private String sqlcommand1;

	@Column(name="TITLE_PRINT_TEXT_ID")
	private BigDecimal titlePrintTextId;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public PrintShapeTable() {
	}

	public String getAlign() {
		return this.align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getColumnWidth() {
		return this.columnWidth;
	}

	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getGetFpt() {
		return this.getFpt;
	}

	public void setGetFpt(BigDecimal getFpt) {
		this.getFpt = getFpt;
	}

	public String getKmln() {
		return this.kmln;
	}

	public void setKmln(String kmln) {
		this.kmln = kmln;
	}

	public BigDecimal getNumAppearInRow() {
		return this.numAppearInRow;
	}

	public void setNumAppearInRow(BigDecimal numAppearInRow) {
		this.numAppearInRow = numAppearInRow;
	}

	public BigDecimal getNumColumn() {
		return this.numColumn;
	}

	public void setNumColumn(BigDecimal numColumn) {
		this.numColumn = numColumn;
	}

	public BigDecimal getNumRow() {
		return this.numRow;
	}

	public void setNumRow(BigDecimal numRow) {
		this.numRow = numRow;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public BigDecimal getPrintShapeTableId() {
		return this.printShapeTableId;
	}

	public void setPrintShapeTableId(BigDecimal printShapeTableId) {
		this.printShapeTableId = printShapeTableId;
	}

	public String getRowHeight() {
		return this.rowHeight;
	}

	public void setRowHeight(String rowHeight) {
		this.rowHeight = rowHeight;
	}

	public String getSqlcommand() {
		return this.sqlcommand;
	}

	public void setSqlcommand(String sqlcommand) {
		this.sqlcommand = sqlcommand;
	}

	public String getSqlcommand1() {
		return this.sqlcommand1;
	}

	public void setSqlcommand1(String sqlcommand1) {
		this.sqlcommand1 = sqlcommand1;
	}

	public BigDecimal getTitlePrintTextId() {
		return this.titlePrintTextId;
	}

	public void setTitlePrintTextId(BigDecimal titlePrintTextId) {
		this.titlePrintTextId = titlePrintTextId;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}