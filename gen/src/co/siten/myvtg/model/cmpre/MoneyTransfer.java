package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MONEY_TRANSFER database table.
 * 
 */
@Entity
@Table(name="MONEY_TRANSFER")
@NamedQuery(name="MoneyTransfer.findAll", query="SELECT m FROM MoneyTransfer m")
public class MoneyTransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FROM_MSISDN")
	private String fromMsisdn;

	@Column(name="NEWBALANCE_CALLED")
	private BigDecimal newbalanceCalled;

	@Column(name="NEWBALANCE_CALLING")
	private BigDecimal newbalanceCalling;

	@Column(name="OPER_FEE")
	private BigDecimal operFee;

	@Column(name="OPER_RESULT")
	private String operResult;

	private String serialno;

	@Column(name="TO_MSISDN")
	private String toMsisdn;

	private BigDecimal transfermoney;

	@Column(name="TRATE_TIME")
	private Object trateTime;

	public MoneyTransfer() {
	}

	public String getFromMsisdn() {
		return this.fromMsisdn;
	}

	public void setFromMsisdn(String fromMsisdn) {
		this.fromMsisdn = fromMsisdn;
	}

	public BigDecimal getNewbalanceCalled() {
		return this.newbalanceCalled;
	}

	public void setNewbalanceCalled(BigDecimal newbalanceCalled) {
		this.newbalanceCalled = newbalanceCalled;
	}

	public BigDecimal getNewbalanceCalling() {
		return this.newbalanceCalling;
	}

	public void setNewbalanceCalling(BigDecimal newbalanceCalling) {
		this.newbalanceCalling = newbalanceCalling;
	}

	public BigDecimal getOperFee() {
		return this.operFee;
	}

	public void setOperFee(BigDecimal operFee) {
		this.operFee = operFee;
	}

	public String getOperResult() {
		return this.operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getSerialno() {
		return this.serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getToMsisdn() {
		return this.toMsisdn;
	}

	public void setToMsisdn(String toMsisdn) {
		this.toMsisdn = toMsisdn;
	}

	public BigDecimal getTransfermoney() {
		return this.transfermoney;
	}

	public void setTransfermoney(BigDecimal transfermoney) {
		this.transfermoney = transfermoney;
	}

	public Object getTrateTime() {
		return this.trateTime;
	}

	public void setTrateTime(Object trateTime) {
		this.trateTime = trateTime;
	}

}