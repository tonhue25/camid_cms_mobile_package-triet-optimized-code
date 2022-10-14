package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_CONTRACT_OFFER_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_CONTRACT_OFFER_TEMP")
@NamedQuery(name="BiContractOfferTemp.findAll", query="SELECT b FROM BiContractOfferTemp b")
public class BiContractOfferTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_OFFER_ID")
	private BigDecimal contractOfferId;

	@Column(name="GROUP_ID")
	private String groupId;

	@Column(name="GROUP_IS_ACTIVATE")
	private BigDecimal groupIsActivate;

	@Column(name="GROUP_TYPE")
	private String groupType;

	@Column(name="IS_BUNDLE")
	private BigDecimal isBundle;

	private String name;

	@Column(name="OFFER_ID")
	private BigDecimal offerId;

	private BigDecimal qualities;

	@Column(name="REQ_OFFER_ID")
	private BigDecimal reqOfferId;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private String type;

	public BiContractOfferTemp() {
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

	public BigDecimal getContractOfferId() {
		return this.contractOfferId;
	}

	public void setContractOfferId(BigDecimal contractOfferId) {
		this.contractOfferId = contractOfferId;
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