package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PARAM database table.
 * 
 */
@Entity
@NamedQuery(name="Param.findAll", query="SELECT p FROM Param p")
public class Param implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARAM_ID")
	private long paramId;

	@Column(name="PARAM_LENGTH")
	private BigDecimal paramLength;

	@Column(name="PARAM_NAME")
	private String paramName;

	@Column(name="PARAM_PATTERN")
	private String paramPattern;

	//bi-directional many-to-one association to Syntax
	@OneToMany(mappedBy="param")
	private List<Syntax> syntaxs;

	public Param() {
	}

	public long getParamId() {
		return this.paramId;
	}

	public void setParamId(long paramId) {
		this.paramId = paramId;
	}

	public BigDecimal getParamLength() {
		return this.paramLength;
	}

	public void setParamLength(BigDecimal paramLength) {
		this.paramLength = paramLength;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamPattern() {
		return this.paramPattern;
	}

	public void setParamPattern(String paramPattern) {
		this.paramPattern = paramPattern;
	}

	public List<Syntax> getSyntaxs() {
		return this.syntaxs;
	}

	public void setSyntaxs(List<Syntax> syntaxs) {
		this.syntaxs = syntaxs;
	}

	public Syntax addSyntax(Syntax syntax) {
		getSyntaxs().add(syntax);
		syntax.setParam(this);

		return syntax;
	}

	public Syntax removeSyntax(Syntax syntax) {
		getSyntaxs().remove(syntax);
		syntax.setParam(null);

		return syntax;
	}

}