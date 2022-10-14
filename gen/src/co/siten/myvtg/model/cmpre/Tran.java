package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TRANS database table.
 * 
 */
@Entity
@Table(name="TRANS")
@NamedQuery(name="Tran.findAll", query="SELECT t FROM Tran t")
public class Tran implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AMOUNT_PROMOTION")
	private BigDecimal amountPromotion;

	private BigDecimal channel;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="DEST_AGENT_ID")
	private BigDecimal destAgentId;

	@Column(name="ERR_CODE")
	private String errCode;

	@Column(name="LAST_MODIFIED")
	private Object lastModified;

	@Column(name="PROCESS_STATUS")
	private BigDecimal processStatus;

	@Column(name="SM_TRANS_ID")
	private BigDecimal smTransId;

	@Column(name="SOURCE_ACCOUNT_ID")
	private BigDecimal sourceAccountId;

	@Column(name="SOURCE_AGENT_ID")
	private BigDecimal sourceAgentId;

	@Column(name="SOURCE_CURR_BALANCE")
	private BigDecimal sourceCurrBalance;

	@Column(name="SOURCE_MSISDN")
	private String sourceMsisdn;

	@Column(name="STOCK_SM_ID")
	private String stockSmId;

	@Column(name="STOCK_TRANS_ID")
	private BigDecimal stockTransId;

	@Column(name="TARGET_ACCOUNT_ID")
	private BigDecimal targetAccountId;

	@Column(name="TARGET_CURR_BALANCE")
	private BigDecimal targetCurrBalance;

	@Column(name="TARGET_MSISDN")
	private String targetMsisdn;

	@Column(name="TRANS_AMOUNT")
	private BigDecimal transAmount;

	@Column(name="TRANS_ID")
	private BigDecimal transId;

	@Column(name="TRANS_TYPE")
	private String transType;

	public Tran() {
	}

	public BigDecimal getAmountPromotion() {
		return this.amountPromotion;
	}

	public void setAmountPromotion(BigDecimal amountPromotion) {
		this.amountPromotion = amountPromotion;
	}

	public BigDecimal getChannel() {
		return this.channel;
	}

	public void setChannel(BigDecimal channel) {
		this.channel = channel;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getDestAgentId() {
		return this.destAgentId;
	}

	public void setDestAgentId(BigDecimal destAgentId) {
		this.destAgentId = destAgentId;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public Object getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Object lastModified) {
		this.lastModified = lastModified;
	}

	public BigDecimal getProcessStatus() {
		return this.processStatus;
	}

	public void setProcessStatus(BigDecimal processStatus) {
		this.processStatus = processStatus;
	}

	public BigDecimal getSmTransId() {
		return this.smTransId;
	}

	public void setSmTransId(BigDecimal smTransId) {
		this.smTransId = smTransId;
	}

	public BigDecimal getSourceAccountId() {
		return this.sourceAccountId;
	}

	public void setSourceAccountId(BigDecimal sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public BigDecimal getSourceAgentId() {
		return this.sourceAgentId;
	}

	public void setSourceAgentId(BigDecimal sourceAgentId) {
		this.sourceAgentId = sourceAgentId;
	}

	public BigDecimal getSourceCurrBalance() {
		return this.sourceCurrBalance;
	}

	public void setSourceCurrBalance(BigDecimal sourceCurrBalance) {
		this.sourceCurrBalance = sourceCurrBalance;
	}

	public String getSourceMsisdn() {
		return this.sourceMsisdn;
	}

	public void setSourceMsisdn(String sourceMsisdn) {
		this.sourceMsisdn = sourceMsisdn;
	}

	public String getStockSmId() {
		return this.stockSmId;
	}

	public void setStockSmId(String stockSmId) {
		this.stockSmId = stockSmId;
	}

	public BigDecimal getStockTransId() {
		return this.stockTransId;
	}

	public void setStockTransId(BigDecimal stockTransId) {
		this.stockTransId = stockTransId;
	}

	public BigDecimal getTargetAccountId() {
		return this.targetAccountId;
	}

	public void setTargetAccountId(BigDecimal targetAccountId) {
		this.targetAccountId = targetAccountId;
	}

	public BigDecimal getTargetCurrBalance() {
		return this.targetCurrBalance;
	}

	public void setTargetCurrBalance(BigDecimal targetCurrBalance) {
		this.targetCurrBalance = targetCurrBalance;
	}

	public String getTargetMsisdn() {
		return this.targetMsisdn;
	}

	public void setTargetMsisdn(String targetMsisdn) {
		this.targetMsisdn = targetMsisdn;
	}

	public BigDecimal getTransAmount() {
		return this.transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public BigDecimal getTransId() {
		return this.transId;
	}

	public void setTransId(BigDecimal transId) {
		this.transId = transId;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}