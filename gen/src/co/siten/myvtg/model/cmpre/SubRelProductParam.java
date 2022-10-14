package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_REL_PRODUCT_PARAM database table.
 * 
 */
@Entity
@Table(name="SUB_REL_PRODUCT_PARAM")
@NamedQuery(name="SubRelProductParam.findAll", query="SELECT s FROM SubRelProductParam s")
public class SubRelProductParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PARAM_CODE")
	private String paramCode;

	@Column(name="PARAM_ID")
	private BigDecimal paramId;

	@Column(name="PARAM_NAME")
	private String paramName;

	@Column(name="PARAM_VALUE")
	private String paramValue;

	@Column(name="SUB_REL_PRODUCT_ID")
	private BigDecimal subRelProductId;

	public SubRelProductParam() {
	}

	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public BigDecimal getParamId() {
		return this.paramId;
	}

	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public BigDecimal getSubRelProductId() {
		return this.subRelProductId;
	}

	public void setSubRelProductId(BigDecimal subRelProductId) {
		this.subRelProductId = subRelProductId;
	}

}