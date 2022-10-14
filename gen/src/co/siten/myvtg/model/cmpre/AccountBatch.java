package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACCOUNT_BATCH database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_BATCH")
@NamedQuery(name="AccountBatch.findAll", query="SELECT a FROM AccountBatch a")
public class AccountBatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADD_DAY")
	private BigDecimal addDay;

	private String amount;

	@Column(name="APP_ID")
	private String appId;

	private String description;

	@Column(name="EXPIRE_DATE")
	private Object expireDate;

	private BigDecimal id;

	@Column(name="IMPORT_DATE")
	private Object importDate;

	private String isdn;

	@Column(name="LAST_NUMBER")
	private String lastNumber;

	@Column(name="PROCESS_DATE")
	private Object processDate;

	@Column(name="PROCESS_TYPE")
	private String processType;

	private String request;

	@Column(name="REQUEST_ID")
	private BigDecimal requestId;

	private String response;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="\"TYPE\"")
	private String type;

	public AccountBatch() {
	}

	public BigDecimal getAddDay() {
		return this.addDay;
	}

	public void setAddDay(BigDecimal addDay) {
		this.addDay = addDay;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Object expireDate) {
		this.expireDate = expireDate;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getImportDate() {
		return this.importDate;
	}

	public void setImportDate(Object importDate) {
		this.importDate = importDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getLastNumber() {
		return this.lastNumber;
	}

	public void setLastNumber(String lastNumber) {
		this.lastNumber = lastNumber;
	}

	public Object getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Object processDate) {
		this.processDate = processDate;
	}

	public String getProcessType() {
		return this.processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public BigDecimal getRequestId() {
		return this.requestId;
	}

	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}