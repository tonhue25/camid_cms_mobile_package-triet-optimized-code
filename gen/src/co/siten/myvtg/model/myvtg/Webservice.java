package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the WEBSERVICE database table.
 * 
 */
@Entity
@NamedQuery(name="Webservice.findAll", query="SELECT w FROM Webservice w")
public class Webservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_TIME")
	private Timestamp createdTime;

	@Column(name="DATA_SIGN")
	private String dataSign;

	private String des;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="LAST_UPDATED_TIME")
	private Timestamp lastUpdatedTime;

	private String params;

	private String password;

	@Column(name="RAW_XML")
	private String rawXml;

	@Column(name="RETURN_TAG")
	private String returnTag;

	@Column(name="SOAP_ACTION")
	private String soapAction;

	private BigDecimal status;

	@Column(name="TAG_PREFIX")
	private String tagPrefix;

	@Column(name="\"TIMEOUT\"")
	private BigDecimal timeout;

	private String url;

	private String username;

	@Column(name="WS_NAME")
	private String wsName;

	@Column(name="WS_TYPE")
	private BigDecimal wsType;

	public Webservice() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getDataSign() {
		return this.dataSign;
	}

	public void setDataSign(String dataSign) {
		this.dataSign = dataSign;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRawXml() {
		return this.rawXml;
	}

	public void setRawXml(String rawXml) {
		this.rawXml = rawXml;
	}

	public String getReturnTag() {
		return this.returnTag;
	}

	public void setReturnTag(String returnTag) {
		this.returnTag = returnTag;
	}

	public String getSoapAction() {
		return this.soapAction;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTagPrefix() {
		return this.tagPrefix;
	}

	public void setTagPrefix(String tagPrefix) {
		this.tagPrefix = tagPrefix;
	}

	public BigDecimal getTimeout() {
		return this.timeout;
	}

	public void setTimeout(BigDecimal timeout) {
		this.timeout = timeout;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWsName() {
		return this.wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public BigDecimal getWsType() {
		return this.wsType;
	}

	public void setWsType(BigDecimal wsType) {
		this.wsType = wsType;
	}

}