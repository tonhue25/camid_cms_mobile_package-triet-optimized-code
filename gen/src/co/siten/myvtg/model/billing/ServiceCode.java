package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SERVICE_CODE database table.
 * 
 */
@Entity
@Table(name="SERVICE_CODE")
@NamedQuery(name="ServiceCode.findAll", query="SELECT s FROM ServiceCode s")
public class ServiceCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CODE_DESCRIPTION")
	private String codeDescription;

	@Column(name="CODE_ID")
	private String codeId;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	public ServiceCode() {
	}

	public String getCodeDescription() {
		return this.codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}