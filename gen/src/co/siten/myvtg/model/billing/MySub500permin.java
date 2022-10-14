package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_SUB_500PERMIN database table.
 * 
 */
@Entity
@Table(name="MY_SUB_500PERMIN")
@NamedQuery(name="MySub500permin.findAll", query="SELECT m FROM MySub500permin m")
public class MySub500permin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_ID")
	private String contractId;

	private BigDecimal id;

	private String isdn;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public MySub500permin() {
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getPromotionCode() {
		return this.promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}