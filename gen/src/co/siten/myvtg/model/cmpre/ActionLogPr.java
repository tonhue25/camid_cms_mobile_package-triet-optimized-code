package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTION_LOG_PR database table.
 * 
 */
@Entity
@Table(name="ACTION_LOG_PR")
@NamedQuery(name="ActionLogPr.findAll", query="SELECT a FROM ActionLogPr a")
public class ActionLogPr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_TYPE_ID")
	private BigDecimal actionTypeId;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="\"EXCEPTION\"")
	private String exception;

	private BigDecimal id;

	private String imsi;

	private String ip;

	private String isdn;

	private String request;

	private String response;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	private String serial;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="USER_NAME")
	private String userName;

	public ActionLogPr() {
	}

	public BigDecimal getActionTypeId() {
		return this.actionTypeId;
	}

	public void setActionTypeId(BigDecimal actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public String getException() {
		return this.exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
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

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}