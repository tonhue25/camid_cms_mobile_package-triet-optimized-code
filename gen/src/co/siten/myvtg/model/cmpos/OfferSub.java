package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OFFER_SUB database table.
 * 
 */
@Entity
@Table(name="OFFER_SUB")
@NamedQuery(name="OfferSub.findAll", query="SELECT o FROM OfferSub o")
public class OfferSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BAL_SHARE_ID")
	private BigDecimal balShareId;

	@Column(name="CONTRACT_OFFER_ID")
	private BigDecimal contractOfferId;

	@Temporal(TemporalType.DATE)
	@Column(name="DB_MODI_DATETIME")
	private Date dbModiDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="DB_STA_DATETIME")
	private Date dbStaDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="END_ZTE_LOG_ID")
	private BigDecimal endZteLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="IN_MODI_DATETIME")
	private Date inModiDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="IN_STA_DATETIME")
	private Date inStaDatetime;

	@Column(name="IS_NEW_SUB")
	private BigDecimal isNewSub;

	@Column(name="\"MAIN\"")
	private String main;

	@Column(name="OFFER_SUB_ID")
	private BigDecimal offerSubId;

	@Column(name="PAY_LIMIT")
	private BigDecimal payLimit;

	@Column(name="PAY_METHOD")
	private BigDecimal payMethod;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="SPEED_DIAL_NUMBER")
	private String speedDialNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="TEL_SERVICE")
	private String telService;

	@Column(name="ZTE_LOG_ID")
	private BigDecimal zteLogId;

	public OfferSub() {
	}

	public BigDecimal getBalShareId() {
		return this.balShareId;
	}

	public void setBalShareId(BigDecimal balShareId) {
		this.balShareId = balShareId;
	}

	public BigDecimal getContractOfferId() {
		return this.contractOfferId;
	}

	public void setContractOfferId(BigDecimal contractOfferId) {
		this.contractOfferId = contractOfferId;
	}

	public Date getDbModiDatetime() {
		return this.dbModiDatetime;
	}

	public void setDbModiDatetime(Date dbModiDatetime) {
		this.dbModiDatetime = dbModiDatetime;
	}

	public Date getDbStaDatetime() {
		return this.dbStaDatetime;
	}

	public void setDbStaDatetime(Date dbStaDatetime) {
		this.dbStaDatetime = dbStaDatetime;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getEndZteLogId() {
		return this.endZteLogId;
	}

	public void setEndZteLogId(BigDecimal endZteLogId) {
		this.endZteLogId = endZteLogId;
	}

	public Date getInModiDatetime() {
		return this.inModiDatetime;
	}

	public void setInModiDatetime(Date inModiDatetime) {
		this.inModiDatetime = inModiDatetime;
	}

	public Date getInStaDatetime() {
		return this.inStaDatetime;
	}

	public void setInStaDatetime(Date inStaDatetime) {
		this.inStaDatetime = inStaDatetime;
	}

	public BigDecimal getIsNewSub() {
		return this.isNewSub;
	}

	public void setIsNewSub(BigDecimal isNewSub) {
		this.isNewSub = isNewSub;
	}

	public String getMain() {
		return this.main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public BigDecimal getOfferSubId() {
		return this.offerSubId;
	}

	public void setOfferSubId(BigDecimal offerSubId) {
		this.offerSubId = offerSubId;
	}

	public BigDecimal getPayLimit() {
		return this.payLimit;
	}

	public void setPayLimit(BigDecimal payLimit) {
		this.payLimit = payLimit;
	}

	public BigDecimal getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(BigDecimal payMethod) {
		this.payMethod = payMethod;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getSpeedDialNumber() {
		return this.speedDialNumber;
	}

	public void setSpeedDialNumber(String speedDialNumber) {
		this.speedDialNumber = speedDialNumber;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
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

	public String getTelService() {
		return this.telService;
	}

	public void setTelService(String telService) {
		this.telService = telService;
	}

	public BigDecimal getZteLogId() {
		return this.zteLogId;
	}

	public void setZteLogId(BigDecimal zteLogId) {
		this.zteLogId = zteLogId;
	}

}