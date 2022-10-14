package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_MB_ACTIVE_DAILY database table.
 * 
 */
@Entity
@Table(name="SUB_MB_ACTIVE_DAILY")
@NamedQuery(name="SubMbActiveDaily.findAll", query="SELECT s FROM SubMbActiveDaily s")
public class SubMbActiveDaily implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PROMOTION_CODE")
	private String promotionCode;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	@Column(name="START_MONEY")
	private String startMoney;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public SubMbActiveDaily() {
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

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
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

}