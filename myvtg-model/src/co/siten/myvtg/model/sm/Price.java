package co.siten.myvtg.model.sm;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRICE database table.
 * 
 */
@Entity
@Table(name="PRICE")
@NamedQuery(name="Price.findAll", query="SELECT p FROM Price p")
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="CHANNEL_TYPE_ID")
	private BigDecimal channelTypeId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String currency;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="PLEDGE_AMOUNT")
	private BigDecimal pledgeAmount;

	@Column(name="PLEDGE_TIME")
	private BigDecimal pledgeTime;

	private BigDecimal price;

	@Id
	@Column(name="PRICE_ID")
	private BigDecimal priceId;

	@Column(name="PRICE_POLICY")
	private String pricePolicy;

	@Column(name="PRIOR_PAY")
	private BigDecimal priorPay;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	private BigDecimal status;

	@Column(name="STOCK_MODEL_ID")
	private BigDecimal stockModelId;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USER_NAME")
	private String userName;

	private BigDecimal vat;

	public Price() {
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public BigDecimal getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(BigDecimal channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPledgeAmount() {
		return this.pledgeAmount;
	}

	public void setPledgeAmount(BigDecimal pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	public BigDecimal getPledgeTime() {
		return this.pledgeTime;
	}

	public void setPledgeTime(BigDecimal pledgeTime) {
		this.pledgeTime = pledgeTime;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceId() {
		return this.priceId;
	}

	public void setPriceId(BigDecimal priceId) {
		this.priceId = priceId;
	}

	public String getPricePolicy() {
		return this.pricePolicy;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
	}

	public BigDecimal getPriorPay() {
		return this.priorPay;
	}

	public void setPriorPay(BigDecimal priorPay) {
		this.priorPay = priorPay;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStockModelId() {
		return this.stockModelId;
	}

	public void setStockModelId(BigDecimal stockModelId) {
		this.stockModelId = stockModelId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

}