package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRINT_PARAM database table.
 * 
 */
@Entity
@Table(name="PRINT_PARAM")
@NamedQuery(name="PrintParam.findAll", query="SELECT p FROM PrintParam p")
public class PrintParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRINT_PARAM_ID")
	private long printParamId;

	@Column(name="FONT_NAME")
	private String fontName;

	@Column(name="FONT_SIZE")
	private BigDecimal fontSize;

	@Column(name="FUNCTION_ID")
	private BigDecimal functionId;

	@Column(name="L_MARGIN")
	private BigDecimal lMargin;

	@Column(name="L_PAD")
	private BigDecimal lPad;

	private String name;

	@Column(name="R_PAD")
	private BigDecimal rPad;

	@Column(name="TEMPLATE_NAME")
	private String templateName;

	@Column(name="TEMPLATE_PATH")
	private String templatePath;

	@Column(name="TOP_MARGIN")
	private BigDecimal topMargin;

	public PrintParam() {
	}

	public long getPrintParamId() {
		return this.printParamId;
	}

	public void setPrintParamId(long printParamId) {
		this.printParamId = printParamId;
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

	public BigDecimal getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(BigDecimal functionId) {
		this.functionId = functionId;
	}

	public BigDecimal getLMargin() {
		return this.lMargin;
	}

	public void setLMargin(BigDecimal lMargin) {
		this.lMargin = lMargin;
	}

	public BigDecimal getLPad() {
		return this.lPad;
	}

	public void setLPad(BigDecimal lPad) {
		this.lPad = lPad;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRPad() {
		return this.rPad;
	}

	public void setRPad(BigDecimal rPad) {
		this.rPad = rPad;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplatePath() {
		return this.templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public BigDecimal getTopMargin() {
		return this.topMargin;
	}

	public void setTopMargin(BigDecimal topMargin) {
		this.topMargin = topMargin;
	}

}