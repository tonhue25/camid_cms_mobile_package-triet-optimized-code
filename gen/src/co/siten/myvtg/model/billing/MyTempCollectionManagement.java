package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_TEMP_COLLECTION_MANAGEMENT database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_COLLECTION_MANAGEMENT")
@NamedQuery(name="MyTempCollectionManagement.findAll", query="SELECT m FROM MyTempCollectionManagement m")
public class MyTempCollectionManagement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	@Column(name="COLLECTION_STAFF_ID")
	private BigDecimal collectionStaffId;

	@Column(name="CONTRACT_FORM_MNGT")
	private String contractFormMngt;

	@Column(name="CONTRACT_FORM_MNGT_GROUP")
	private String contractFormMngtGroup;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	public MyTempCollectionManagement() {
	}

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public BigDecimal getCollectionStaffId() {
		return this.collectionStaffId;
	}

	public void setCollectionStaffId(BigDecimal collectionStaffId) {
		this.collectionStaffId = collectionStaffId;
	}

	public String getContractFormMngt() {
		return this.contractFormMngt;
	}

	public void setContractFormMngt(String contractFormMngt) {
		this.contractFormMngt = contractFormMngt;
	}

	public String getContractFormMngtGroup() {
		return this.contractFormMngtGroup;
	}

	public void setContractFormMngtGroup(String contractFormMngtGroup) {
		this.contractFormMngtGroup = contractFormMngtGroup;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

}