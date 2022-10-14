package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GET_FILE_NAME database table.
 * 
 */
@Entity
@Table(name="GET_FILE_NAME")
@NamedQuery(name="GetFileName.findAll", query="SELECT g FROM GetFileName g")
public class GetFileName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DATE_TIME")
	private Object dateTime;

	@Column(name="DATETIME_GET")
	private Object datetimeGet;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_SEQUENCE")
	private BigDecimal fileSequence;

	@Column(name="FILE_TYPE")
	private String fileType;

	@Column(name="ID_IN")
	private String idIn;

	public GetFileName() {
	}

	public Object getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Object dateTime) {
		this.dateTime = dateTime;
	}

	public Object getDatetimeGet() {
		return this.datetimeGet;
	}

	public void setDatetimeGet(Object datetimeGet) {
		this.datetimeGet = datetimeGet;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getFileSequence() {
		return this.fileSequence;
	}

	public void setFileSequence(BigDecimal fileSequence) {
		this.fileSequence = fileSequence;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getIdIn() {
		return this.idIn;
	}

	public void setIdIn(String idIn) {
		this.idIn = idIn;
	}

}