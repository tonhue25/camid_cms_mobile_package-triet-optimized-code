package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the CONTRACT_OFFER database table.
 * 
 */
@Entity
@Table(name = "CONTRACT_OFFER")
@NamedQuery(name = "ContractOffer.findAll", query = "SELECT c FROM ContractOffer c")
public class ContractOffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_OFFER_ID")
	private long contractOfferId;

	private String code;

	@Column(name = "CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name = "GROUP_ID")
	private String groupId;

	@Column(name = "GROUP_IS_ACTIVATE")
	private BigDecimal groupIsActivate;

	@Column(name = "GROUP_TYPE")
	private String groupType;

	@Column(name = "IS_BUNDLE")
	private BigDecimal isBundle;

	private String name;

	@Column(name = "OFFER_ID")
	private BigDecimal offerId;

	private BigDecimal qualities;

	@Column(name = "REQ_OFFER_ID")
	private BigDecimal reqOfferId;

	private BigDecimal status;

	@Column(name = "\"TYPE\"")
	private String type;

	public ContractOffer() {
	}

	public ContractOffer(ContractOffer c) {
		super();
		this.code = c.code;
		this.contractId = c.contractId;
		this.groupId = c.groupId;
		this.groupIsActivate = c.groupIsActivate;
		this.groupType = c.groupType;
		this.isBundle = c.isBundle;
		this.name = c.name;
		this.offerId = c.offerId;
		this.qualities = c.qualities;
		this.reqOfferId = c.reqOfferId;
		this.status = c.status;
		this.type = c.type;
	}

	public long getContractOfferId() {
		return this.contractOfferId;
	}

	public void setContractOfferId(long contractOfferId) {
		this.contractOfferId = contractOfferId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getGroupIsActivate() {
		return this.groupIsActivate;
	}

	public void setGroupIsActivate(BigDecimal groupIsActivate) {
		this.groupIsActivate = groupIsActivate;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public BigDecimal getIsBundle() {
		return this.isBundle;
	}

	public void setIsBundle(BigDecimal isBundle) {
		this.isBundle = isBundle;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOfferId() {
		return this.offerId;
	}

	public void setOfferId(BigDecimal offerId) {
		this.offerId = offerId;
	}

	public BigDecimal getQualities() {
		return this.qualities;
	}

	public void setQualities(BigDecimal qualities) {
		this.qualities = qualities;
	}

	public BigDecimal getReqOfferId() {
		return this.reqOfferId;
	}

	public void setReqOfferId(BigDecimal reqOfferId) {
		this.reqOfferId = reqOfferId;
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

}