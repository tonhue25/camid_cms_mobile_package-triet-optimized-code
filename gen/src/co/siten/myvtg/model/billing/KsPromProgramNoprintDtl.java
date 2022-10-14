package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KS_PROM_PROGRAM_NOPRINT_DTL database table.
 * 
 */
@Entity
@Table(name="KS_PROM_PROGRAM_NOPRINT_DTL")
@NamedQuery(name="KsPromProgramNoprintDtl.findAll", query="SELECT k FROM KsPromProgramNoprintDtl k")
public class KsPromProgramNoprintDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	public KsPromProgramNoprintDtl() {
	}

	public String getPromProgramCode() {
		return this.promProgramCode;
	}

	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}

}