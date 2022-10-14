package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TRIGER_DISABLE_OPTION database table.
 * 
 */
@Entity
@Table(name="TRIGER_DISABLE_OPTION")
@NamedQuery(name="TrigerDisableOption.findAll", query="SELECT t FROM TrigerDisableOption t")
public class TrigerDisableOption implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal optional;

	@Column(name="TRIGER_ID")
	private BigDecimal trigerId;

	@Column(name="TRIGER_NAME")
	private String trigerName;

	public TrigerDisableOption() {
	}

	public BigDecimal getOptional() {
		return this.optional;
	}

	public void setOptional(BigDecimal optional) {
		this.optional = optional;
	}

	public BigDecimal getTrigerId() {
		return this.trigerId;
	}

	public void setTrigerId(BigDecimal trigerId) {
		this.trigerId = trigerId;
	}

	public String getTrigerName() {
		return this.trigerName;
	}

	public void setTrigerName(String trigerName) {
		this.trigerName = trigerName;
	}

}