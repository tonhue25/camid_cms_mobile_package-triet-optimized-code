package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ERR$_MONEY_TRANSFER database table.
 * 
 */
@Entity
@Table(name="ERR$_MONEY_TRANSFER")
@NamedQuery(name="Err$MoneyTransfer.findAll", query="SELECT e FROM Err$MoneyTransfer e")
public class Err$MoneyTransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FROM_MSISDN")
	private String fromMsisdn;

	@Column(name="NEWBALANCE_CALLED")
	private String newbalanceCalled;

	@Column(name="NEWBALANCE_CALLING")
	private String newbalanceCalling;

	@Column(name="OPER_FEE")
	private String operFee;

	@Column(name="OPER_RESULT")
	private String operResult;

	@Column(name="ORA_ERR_MESG$")
	private String oraErrMesg$;

	@Column(name="ORA_ERR_NUMBER$")
	private BigDecimal oraErrNumber$;

	@Column(name="ORA_ERR_OPTYP$")
	private String oraErrOptyp$;

	@Column(name="ORA_ERR_TAG$")
	private String oraErrTag$;

	private String serialno;

	@Column(name="TO_MSISDN")
	private String toMsisdn;

	private String transfermoney;

	@Column(name="TRATE_TIME")
	private String trateTime;

	public Err$MoneyTransfer() {
	}

	public String getFromMsisdn() {
		return this.fromMsisdn;
	}

	public void setFromMsisdn(String fromMsisdn) {
		this.fromMsisdn = fromMsisdn;
	}

	public String getNewbalanceCalled() {
		return this.newbalanceCalled;
	}

	public void setNewbalanceCalled(String newbalanceCalled) {
		this.newbalanceCalled = newbalanceCalled;
	}

	public String getNewbalanceCalling() {
		return this.newbalanceCalling;
	}

	public void setNewbalanceCalling(String newbalanceCalling) {
		this.newbalanceCalling = newbalanceCalling;
	}

	public String getOperFee() {
		return this.operFee;
	}

	public void setOperFee(String operFee) {
		this.operFee = operFee;
	}

	public String getOperResult() {
		return this.operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getOraErrMesg$() {
		return this.oraErrMesg$;
	}

	public void setOraErrMesg$(String oraErrMesg$) {
		this.oraErrMesg$ = oraErrMesg$;
	}

	public BigDecimal getOraErrNumber$() {
		return this.oraErrNumber$;
	}

	public void setOraErrNumber$(BigDecimal oraErrNumber$) {
		this.oraErrNumber$ = oraErrNumber$;
	}

	public String getOraErrOptyp$() {
		return this.oraErrOptyp$;
	}

	public void setOraErrOptyp$(String oraErrOptyp$) {
		this.oraErrOptyp$ = oraErrOptyp$;
	}

	public String getOraErrTag$() {
		return this.oraErrTag$;
	}

	public void setOraErrTag$(String oraErrTag$) {
		this.oraErrTag$ = oraErrTag$;
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

	public String getTransfermoney() {
		return this.transfermoney;
	}

	public void setTransfermoney(String transfermoney) {
		this.transfermoney = transfermoney;
	}

	public String getTrateTime() {
		return this.trateTime;
	}

	public void setTrateTime(String trateTime) {
		this.trateTime = trateTime;
	}

}