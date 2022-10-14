package co.siten.myvtg.model.cmpos;

import java.io.Serializable;
import javax.persistence.*;

import co.siten.myvtg.config.Config;

import java.util.Date;


/**
 * The persistent class for the ACTION_LOG_PR database table.
 * 
 */
@Entity
@Table(name="ACTION_LOG_PR")
@NamedQuery(name="ActionLogPr.findAll", query="SELECT a FROM ActionLogPr a")
public class ActionLogPr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_CM_POS_ACTION_LOG_PR, allocationSize = 1)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="\"EXCEPTION\"")
	private String exception;

	private String isdn;

	private String request;

	private String response;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	public ActionLogPr() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getException() {
		return this.exception;
	}

	public void setException(String exception) {
		this.exception = exception;
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