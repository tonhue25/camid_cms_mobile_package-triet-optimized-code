package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the VIEW_HLR_MB database table.
 * 
 */
@Entity
@Table(name="VIEW_HLR_MB")
@NamedQuery(name="ViewHlrMb.findAll", query="SELECT v FROM ViewHlrMb v")
public class ViewHlrMb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_STATUS")
	private String actStatus;

	private String imsi;

	private String isdn;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="REL_PRODUCT_ID")
	private String relProductId;

	@Column(name="START_MONEY")
	private String startMoney;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="VALID_DATETIME")
	private Timestamp validDatetime;

	public ViewHlrMb() {
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPromotionCode() {
		return this.promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getRelProductId() {
		return this.relProductId;
	}

	public void setRelProductId(String relProductId) {
		this.relProductId = relProductId;
	}

	public String getStartMoney() {
		return this.startMoney;
	}

	public void setStartMoney(String startMoney) {
		this.startMoney = startMoney;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public Timestamp getValidDatetime() {
		return this.validDatetime;
	}

	public void setValidDatetime(Timestamp validDatetime) {
		this.validDatetime = validDatetime;
	}

}