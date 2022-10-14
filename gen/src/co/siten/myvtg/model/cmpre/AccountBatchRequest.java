package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACCOUNT_BATCH_REQUEST database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_BATCH_REQUEST")
@NamedQuery(name="AccountBatchRequest.findAll", query="SELECT a FROM AccountBatchRequest a")
public class AccountBatchRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	private String ip;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REQUEST_DATE")
	private Object requestDate;

	@Column(name="REQUEST_ID")
	private BigDecimal requestId;

	@Column(name="REQUEST_PAPER_NAME")
	private String requestPaperName;

	@Column(name="REQUEST_USER_NAME")
	private String requestUserName;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	public AccountBatchRequest() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public Object getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Object requestDate) {
		this.requestDate = requestDate;
	}

	public BigDecimal getRequestId() {
		return this.requestId;
	}

	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}

	public String getRequestPaperName() {
		return this.requestPaperName;
	}

	public void setRequestPaperName(String requestPaperName) {
		this.requestPaperName = requestPaperName;
	}

	public String getRequestUserName() {
		return this.requestUserName;
	}

	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}