package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the WEBSERVICE database table.
 *
 */
@Entity
@NamedQuery(name = "Webservice.findAll", query = "SELECT w FROM Webservice w")
public class Webservice implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "DATA_SIGN")
    private String dataSign;

    private String des;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATED_TIME")
    private Date lastUpdatedTime;

    private String params;

    private String password;

    @Column(name = "RAW_XML")
    private String rawXml;

    @Column(name = "RETURN_TAG")
    private String returnTag;

    @Column(name = "SOAP_ACTION")
    private String soapAction;

    private Integer status;

    @Column(name = "TAG_PREFIX")
    private String tagPrefix;

    @Column(name = "\"TIMEOUT\"")
    private Integer timeout;

    private String url;

    private String username;

    @Column(name = "WS_NAME")
    private String wsName;

    @Column(name = "WS_TYPE")
    private Integer wsType;

    @Column(name = "XPATH_RESPONSE_CODE")
    private String xpathResponseCode;

    @Column(name = "RSP_CODE_SUCC")
    private String rspCodeSucc;

    @Column(name = "XPATH_EXTENSION_01")
    private String xpathExtension01;

    @Column(name = "XPATH_EXTENSION_02")
    private String xpathExtension02;

    @Column(name = "XPATH_EXTENSION_03")
    private String xpathExtension03;

    @Column(name = "METHOD")
    private String method;

    public String getXpathResponseCode() {
        return xpathResponseCode;
    }

    public void setXpathResponseCode(String xpathResponseCode) {
        this.xpathResponseCode = xpathResponseCode;
    }

    public String getRspCodeSucc() {
        return rspCodeSucc;
    }

    public void setRspCodeSucc(String rspCodeSucc) {
        this.rspCodeSucc = rspCodeSucc;
    }

    public String getXpathExtension01() {
        return xpathExtension01;
    }

    public void setXpathExtension01(String xpathExtension01) {
        this.xpathExtension01 = xpathExtension01;
    }

    public String getXpathExtension02() {
        return xpathExtension02;
    }

    public void setXpathExtension02(String xpathExtension02) {
        this.xpathExtension02 = xpathExtension02;
    }

    public String getXpathExtension03() {
        return xpathExtension03;
    }

    public void setXpathExtension03(String xpathExtension03) {
        this.xpathExtension03 = xpathExtension03;
    }

    public Webservice() {
    }

    public String getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getDataSign() {
        return dataSign;
    }

    public String getDes() {
        return des;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public String getParams() {
        return params;
    }

    public String getPassword() {
        return password;
    }

    public String getRawXml() {
        return rawXml;
    }

    public String getReturnTag() {
        return returnTag;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTagPrefix() {
        return tagPrefix;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getWsName() {
        return wsName;
    }

    public Integer getWsType() {
        return wsType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setDataSign(String dataSign) {
        this.dataSign = dataSign;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRawXml(String rawXml) {
        this.rawXml = rawXml;
    }

    public void setReturnTag(String returnTag) {
        this.returnTag = returnTag;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTagPrefix(String tagPrefix) {
        this.tagPrefix = tagPrefix;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public void setWsType(Integer wsType) {
        this.wsType = wsType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    

}
