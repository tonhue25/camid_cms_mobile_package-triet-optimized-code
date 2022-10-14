package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATA_BUY database table.
 * 
 */
@Entity
@Table(name="DATA_BUY")
@NamedQuery(name="DataBuy.findAll", query="SELECT d FROM DataBuy d")
public class DataBuy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUY_BY")
	private String buyBy;
	@Id
	private String name;

	private String syntax;

	public DataBuy() {
	}

	public String getBuyBy() {
		return this.buyBy;
	}

	public void setBuyBy(String buyBy) {
		this.buyBy = buyBy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

}