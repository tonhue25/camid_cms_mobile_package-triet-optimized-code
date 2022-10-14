package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BUS_TYPE database table.
 * 
 */
@Entity
@Table(name="BUS_TYPE")
@NamedQuery(name="BusType.findAll", query="SELECT b FROM BusType b")
public class BusType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CHANGE_DATETIME")
	private Object changeDatetime;

	@Column(name="CREATE_DATETIME")
	private Object createDatetime;

	@Column(name="CUST_TYPE")
	private String custType;

	@Column(name="EXPIRE_REQUIRED")
	private String expireRequired;

	@Column(name="ID_REQUIRED")
	private String idRequired;

	private String name;

	@Column(name="PERMIT_REQUIRED")
	private String permitRequired;

	@Column(name="POP_REQUIRED")
	private String popRequired;

	private BigDecimal status;

	private BigDecimal tax;

	@Column(name="TIN_REQUIRED")
	private String tinRequired;

	public BusType() {
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Object getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Object changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCustType() {
		return this.custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getExpireRequired() {
		return this.expireRequired;
	}

	public void setExpireRequired(String expireRequired) {
		this.expireRequired = expireRequired;
	}

	public String getIdRequired() {
		return this.idRequired;
	}

	public void setIdRequired(String idRequired) {
		this.idRequired = idRequired;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermitRequired() {
		return this.permitRequired;
	}

	public void setPermitRequired(String permitRequired) {
		this.permitRequired = permitRequired;
	}

	public String getPopRequired() {
		return this.popRequired;
	}

	public void setPopRequired(String popRequired) {
		this.popRequired = popRequired;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getTinRequired() {
		return this.tinRequired;
	}

	public void setTinRequired(String tinRequired) {
		this.tinRequired = tinRequired;
	}

}