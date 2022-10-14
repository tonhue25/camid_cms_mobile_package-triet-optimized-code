package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the COUNTRY database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="IA_CODE")
	private String iaCode;

	private String name;

	@Column(name="NOTE_ID")
	private BigDecimal noteId;

	private String status;

	public Country() {
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getIaCode() {
		return this.iaCode;
	}

	public void setIaCode(String iaCode) {
		this.iaCode = iaCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNoteId() {
		return this.noteId;
	}

	public void setNoteId(BigDecimal noteId) {
		this.noteId = noteId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}