package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;

/**
 * The persistent class for the TRANSACTION_ERROR database table.
 * 
 */
@Entity
@Table(name = "TRANSACTION_ERROR")
@NamedQuery(name = "TransactionError.findAll", query = "SELECT t FROM TransactionError t")
public class TransactionError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "TRANSACTION_ERROR_SEQ", allocationSize = 1)
	private long id;

	@Column(name = "API_KEY")
	private String apiKey;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "ERROR_CODE")
	private String errorCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "INSERT_DATE")
	private Date insertDate;

	@Column(name = "LOG_ID")
	private BigDecimal logId;

	@Column(name = "\"MESSAGE\"")
	private String message;

	private String request;

	private String response;

	@Column(name = "SESSION_ID")
	private String sessionId;

	@Column(name = "SHOP_CODE")
	private String shopCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "STA_TIME")
	private Date staTime;

	@Column(name = "STAFF_CODE")
	private String staffCode;

	@Column(name = "WS_CODE")
	private String wsCode;

	public TransactionError() {
	}

	public TransactionError(RequestBean request2, Exception e, BigDecimal logId) {
		setApiKey(request2.getApiKey());
		setEndTime(CommonUtil.getCurrentTime());
		setErrorCode(Constants.ERROR_CODE_1);
		setMessage(e.getMessage());
		setRequest(CommonUtil.convertObjectToJsonString(request2));
		setResponse(response);
		setLogId(logId);
		setSessionId(request2.getSessionId().toString());
		setShopCode(Constants.MYVTG);
		setStaffCode(Constants.MYVTG);
		setStaTime(request2.getStartTime());
		setResponse(CommonUtil.convertObjectToJsonString(new ResponseErrorBean(e)));
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

	public Date getEndTime() {
		return endTime;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public BigDecimal getLogId() {
		return logId;
	}

	public String getMessage() {
		return message;
	}

	public String getRequest() {
		return request;
	}

	public String getResponse() {
		return response;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public Date getStaTime() {
		return staTime;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public void setStaTime(Date staTime) {
		this.staTime = staTime;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

}