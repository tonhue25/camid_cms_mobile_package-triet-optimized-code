package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the POSTAL database table.
 * 
 */
@Entity
@NamedQuery(name="Postal.findAll", query="SELECT p FROM Postal p")
public class Postal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DIR_CLASS1")
	private String dirClass1;

	@Column(name="DIR_CLASS2")
	private String dirClass2;

	@Column(name="DIR_CLASS3")
	private String dirClass3;

	@Column(name="EXIST_SERVICE")
	private String existService;

	private String name;

	@Column(name="NOTE_ID")
	private BigDecimal noteId;

	@Column(name="ORG_PO_CODE")
	private String orgPoCode;

	@Column(name="PO_CODE")
	private String poCode;

	private String status;

	public Postal() {
	}

	public String getDirClass1() {
		return this.dirClass1;
	}

	public void setDirClass1(String dirClass1) {
		this.dirClass1 = dirClass1;
	}

	public String getDirClass2() {
		return this.dirClass2;
	}

	public void setDirClass2(String dirClass2) {
		this.dirClass2 = dirClass2;
	}

	public String getDirClass3() {
		return this.dirClass3;
	}

	public void setDirClass3(String dirClass3) {
		this.dirClass3 = dirClass3;
	}

	public String getExistService() {
		return this.existService;
	}

	public void setExistService(String existService) {
		this.existService = existService;
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

	public String getOrgPoCode() {
		return this.orgPoCode;
	}

	public void setOrgPoCode(String orgPoCode) {
		this.orgPoCode = orgPoCode;
	}

	public String getPoCode() {
		return this.poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}