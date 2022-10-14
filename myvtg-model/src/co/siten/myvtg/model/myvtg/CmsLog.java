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

/**
 * The persistent class for the CMS_LOG database table.
 * 
 */
@Entity
@Table(name = "CMS_LOG")
@NamedQuery(name = "CmsLog.findAll", query = "SELECT c FROM CmsLog c")
public class CmsLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "API_KEY")
	private String apiKey;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "ERROR_CODE")
	private String errorCode;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "CMS_LOG_SEQ", allocationSize = 1)
	private BigDecimal id;
	@Temporal(TemporalType.DATE)
	@Column(name = "INSERT_DATE")
	private Date insertDate;

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

	public CmsLog() {
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

	public BigDecimal getId() {
		return id;
	}

	public Date getInsertDate() {
		return insertDate;
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

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
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