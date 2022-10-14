package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the POST_PAID database table.
 * 
 */
@Entity
@Table(name="POST_PAID")
@NamedQuery(name="PostPaid.findAll", query="SELECT p FROM PostPaid p")
public class PostPaid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_TYPE")
	private BigDecimal actionType;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private BigDecimal fee;

	private String isdn;

	@Column(name="MO_HIS_ID")
	private BigDecimal moHisId;

	@Temporal(TemporalType.DATE)
	@Column(name="PAID_TIME")
	private Date paidTime;

	@Column(name="POST_PAID_ID")
	private BigDecimal postPaidId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private BigDecimal subType;

	@Column(name="VAS_CODE")
	private String vasCode;

	public PostPaid() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getMoHisId() {
		return this.moHisId;
	}

	public void setMoHisId(BigDecimal moHisId) {
		this.moHisId = moHisId;
	}

	public Date getPaidTime() {
		return this.paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	public BigDecimal getPostPaidId() {
		return this.postPaidId;
	}

	public void setPostPaidId(BigDecimal postPaidId) {
		this.postPaidId = postPaidId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSubType() {
		return this.subType;
	}

	public void setSubType(BigDecimal subType) {
		this.subType = subType;
	}

	public String getVasCode() {
		return this.vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

}