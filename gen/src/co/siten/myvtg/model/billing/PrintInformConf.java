package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRINT_INFORM_CONF database table.
 * 
 */
@Entity
@Table(name="PRINT_INFORM_CONF")
@NamedQuery(name="PrintInformConf.findAll", query="SELECT p FROM PrintInformConf p")
public class PrintInformConf implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private String keyword;

	@Lob
	@Column(name="\"VALUE\"")
	private String value;

	public PrintInformConf() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}