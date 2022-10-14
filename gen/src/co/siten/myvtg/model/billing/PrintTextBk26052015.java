package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_TEXT_BK26052015 database table.
 * 
 */
@Entity
@Table(name="PRINT_TEXT_BK26052015")
@NamedQuery(name="PrintTextBk26052015.findAll", query="SELECT p FROM PrintTextBk26052015 p")
public class PrintTextBk26052015 implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal align;

	private String color;

	@Column(name="FONT_NAME")
	private String fontName;

	@Column(name="FONT_SIZE")
	private BigDecimal fontSize;

	@Column(name="KMLN_ID")
	private BigDecimal kmlnId;

	@Column(name="\"POSTFIX\"")
	private String postfix;

	@Column(name="PRINT_DATA_ID")
	private BigDecimal printDataId;

	@Column(name="PRINT_OBJECT_ID")
	private BigDecimal printObjectId;

	private BigDecimal style;

	private String text;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	private BigDecimal xpos;

	private BigDecimal ypos;

	public PrintTextBk26052015() {
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

	public BigDecimal getKmlnId() {
		return this.kmlnId;
	}

	public void setKmlnId(BigDecimal kmlnId) {
		this.kmlnId = kmlnId;
	}

	public String getPostfix() {
		return this.postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public BigDecimal getPrintDataId() {
		return this.printDataId;
	}

	public void setPrintDataId(BigDecimal printDataId) {
		this.printDataId = printDataId;
	}

	public BigDecimal getPrintObjectId() {
		return this.printObjectId;
	}

	public void setPrintObjectId(BigDecimal printObjectId) {
		this.printObjectId = printObjectId;
	}

	public BigDecimal getStyle() {
		return this.style;
	}

	public void setStyle(BigDecimal style) {
		this.style = style;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
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