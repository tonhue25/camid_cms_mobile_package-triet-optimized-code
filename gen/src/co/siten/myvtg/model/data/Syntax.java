package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SYNTAX database table.
 * 
 */
@Entity
@NamedQuery(name="Syntax.findAll", query="SELECT s FROM Syntax s")
public class Syntax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SYNTAX_ID")
	private long syntaxId;

	@Column(name="COMMAND_ID")
	private BigDecimal commandId;

	@Column(name="PARAM_BEFORE")
	private BigDecimal paramBefore;

	@Column(name="PARAM_OPTION")
	private BigDecimal paramOption;

	//bi-directional many-to-one association to Content
	@ManyToOne
	@JoinColumn(name="CONTENT_ID")
	private Content content;

	//bi-directional many-to-one association to Param
	@ManyToOne
	@JoinColumn(name="PARAM_ID")
	private Param param;

	public Syntax() {
	}

	public long getSyntaxId() {
		return this.syntaxId;
	}

	public void setSyntaxId(long syntaxId) {
		this.syntaxId = syntaxId;
	}

	public BigDecimal getCommandId() {
		return this.commandId;
	}

	public void setCommandId(BigDecimal commandId) {
		this.commandId = commandId;
	}

	public BigDecimal getParamBefore() {
		return this.paramBefore;
	}

	public void setParamBefore(BigDecimal paramBefore) {
		this.paramBefore = paramBefore;
	}

	public BigDecimal getParamOption() {
		return this.paramOption;
	}

	public void setParamOption(BigDecimal paramOption) {
		this.paramOption = paramOption;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Param getParam() {
		return this.param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

}