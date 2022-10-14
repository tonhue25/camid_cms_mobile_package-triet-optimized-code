package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the CONTENT database table.
 * 
 */
@Entity
@NamedQuery(name="Content.findAll", query="SELECT c FROM Content c")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CONTENT_ID")
	private long contentId;

	@Column(name="ACTION_TYPE")
	private BigDecimal actionType;

	private String description;

	@Column(name="ONLY_RECEIVER")
	private BigDecimal onlyReceiver;

	//bi-directional many-to-one association to Syntax
	@OneToMany(mappedBy="content")
	private List<Syntax> syntaxs;

	public Content() {
	}

	public long getContentId() {
		return this.contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getOnlyReceiver() {
		return this.onlyReceiver;
	}

	public void setOnlyReceiver(BigDecimal onlyReceiver) {
		this.onlyReceiver = onlyReceiver;
	}

	public List<Syntax> getSyntaxs() {
		return this.syntaxs;
	}

	public void setSyntaxs(List<Syntax> syntaxs) {
		this.syntaxs = syntaxs;
	}

	public Syntax addSyntax(Syntax syntax) {
		getSyntaxs().add(syntax);
		syntax.setContent(this);

		return syntax;
	}

	public Syntax removeSyntax(Syntax syntax) {
		getSyntaxs().remove(syntax);
		syntax.setContent(null);

		return syntax;
	}

}