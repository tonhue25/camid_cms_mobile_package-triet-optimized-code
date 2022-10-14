package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BUS_TYPE_ID_REQUIRE database table.
 * 
 */
@Entity
@Table(name="BUS_TYPE_ID_REQUIRE")
@NamedQuery(name="BusTypeIdRequire.findAll", query="SELECT b FROM BusTypeIdRequire b")
public class BusTypeIdRequire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUS_TYPE")
	private String busType;

	private BigDecimal id;

	@Column(name="ID_TYPE")
	private BigDecimal idType;

	private BigDecimal status;

	public BusTypeIdRequire() {
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdType() {
		return this.idType;
	}

	public void setIdType(BigDecimal idType) {
		this.idType = idType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}