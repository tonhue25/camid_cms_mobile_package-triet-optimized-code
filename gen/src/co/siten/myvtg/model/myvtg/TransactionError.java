package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TRANSACTION_ERROR database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_ERROR")
@NamedQuery(name="TransactionError.findAll", query="SELECT t FROM TransactionError t")
public class TransactionError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="API_KEY")
	private String apiKey;

	@Column(name="END_TIME")
	private Timestamp endTime;

	@Column(name="ERROR_CODE")
	private String errorCode;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="\"MESSAGE\"")
	private String message;

	private String request;

	private String response;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STA_TIME")
	private Timestamp staTime;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="WS_CODE")
	private String wsCode;

	public TransactionError() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Timestamp getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public Timestamp getStaTime() {
		return this.staTime;
	}

	public void setStaTime(Timestamp staTime) {
		this.staTime = staTime;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getWsCode() {
		return this.wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

}