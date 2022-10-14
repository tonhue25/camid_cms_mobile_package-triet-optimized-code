package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_REL_PRODUCT_CODE database table.
 * 
 */
@Entity
@Table(name="MISS_REL_PRODUCT_CODE")
@NamedQuery(name="MissRelProductCode.findAll", query="SELECT m FROM MissRelProductCode m")
public class MissRelProductCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="REL_PRODUCT_CODE")
	private String relProductCode;

	@Column(name="STA_DATE")
	private Object staDate;

	public MissRelProductCode() {
	}

	public String getRelProductCode() {
		return this.relProductCode;
	}

	public void setRelProductCode(String relProductCode) {
		this.relProductCode = relProductCode;
	}

	public Object getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Object staDate) {
		this.staDate = staDate;
	}

}