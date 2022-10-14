package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the AP_DOMAIN database table.
 * 
 */
@Entity
@Table(name="AP_DOMAIN")
@NamedQuery(name="ApDomain.findAll", query="SELECT a FROM ApDomain a")
public class ApDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;

	@Column(name="REASON_TYPE")
	private String reasonType;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="\"VALUE\"")
	private String value;

	public ApDomain() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReasonType() {
		return this.reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}