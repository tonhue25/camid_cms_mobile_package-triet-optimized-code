package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REPRESENTATIVE_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="REPRESENTATIVE_CUSTOMER")
@NamedQuery(name="RepresentativeCustomer.findAll", query="SELECT r FROM RepresentativeCustomer r")
public class RepresentativeCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="BIRTH_DATE")
	private Object birthDate;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="ID_EXPIRE_DATE")
	private Object idExpireDate;

	@Column(name="ID_ISSUE_DATE")
	private Object idIssueDate;

	@Column(name="ID_ISSUE_PLACE")
	private String idIssuePlace;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ID_TYPE")
	private BigDecimal idType;

	private String name;

	@Column(name="TEL_FAX")
	private String telFax;

	public RepresentativeCustomer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public Object getIdExpireDate() {
		return this.idExpireDate;
	}

	public void setIdExpireDate(Object idExpireDate) {
		this.idExpireDate = idExpireDate;
	}

	public Object getIdIssueDate() {
		return this.idIssueDate;
	}

	public void setIdIssueDate(Object idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdIssuePlace() {
		return this.idIssuePlace;
	}

	public void setIdIssuePlace(String idIssuePlace) {
		this.idIssuePlace = idIssuePlace;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public BigDecimal getIdType() {
		return this.idType;
	}

	public void setIdType(BigDecimal idType) {
		this.idType = idType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

}