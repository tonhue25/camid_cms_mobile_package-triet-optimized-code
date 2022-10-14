package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the OWNER_SCHAME_OPTION database table.
 * 
 */
@Entity
@Table(name="OWNER_SCHAME_OPTION")
@NamedQuery(name="OwnerSchameOption.findAll", query="SELECT o FROM OwnerSchameOption o")
public class OwnerSchameOption implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal optional;

	@Column(name="OWNER_ID")
	private BigDecimal ownerId;

	@Column(name="SCHAME_NAME")
	private String schameName;

	public OwnerSchameOption() {
	}

	public BigDecimal getOptional() {
		return this.optional;
	}

	public void setOptional(BigDecimal optional) {
		this.optional = optional;
	}

	public BigDecimal getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	public String getSchameName() {
		return this.schameName;
	}

	public void setSchameName(String schameName) {
		this.schameName = schameName;
	}

}