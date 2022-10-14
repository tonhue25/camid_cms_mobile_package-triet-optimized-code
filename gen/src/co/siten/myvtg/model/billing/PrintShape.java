package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_SHAPE database table.
 * 
 */
@Entity
@Table(name="PRINT_SHAPE")
@NamedQuery(name="PrintShape.findAll", query="SELECT p FROM PrintShape p")
public class PrintShape implements Serializable {
	private static final long serialVersionUID = 1L;

	private String color;

	private BigDecimal height;

	@Column(name="PRINT_OBJECT_ID")
	private BigDecimal printObjectId;

	@Column(name="PRINT_SHAPE_TABLE_ID")
	private BigDecimal printShapeTableId;

	@Column(name="PRINT_SHAPE_TYPE")
	private BigDecimal printShapeType;

	private BigDecimal width;

	private BigDecimal xpos;

	private BigDecimal ypos;

	public PrintShape() {
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getPrintObjectId() {
		return this.printObjectId;
	}

	public void setPrintObjectId(BigDecimal printObjectId) {
		this.printObjectId = printObjectId;
	}

	public BigDecimal getPrintShapeTableId() {
		return this.printShapeTableId;
	}

	public void setPrintShapeTableId(BigDecimal printShapeTableId) {
		this.printShapeTableId = printShapeTableId;
	}

	public BigDecimal getPrintShapeType() {
		return this.printShapeType;
	}

	public void setPrintShapeType(BigDecimal printShapeType) {
		this.printShapeType = printShapeType;
	}

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getXpos() {
		return this.xpos;
	}

	public void setXpos(BigDecimal xpos) {
		this.xpos = xpos;
	}

	public BigDecimal getYpos() {
		return this.ypos;
	}

	public void setYpos(BigDecimal ypos) {
		this.ypos = ypos;
	}

}