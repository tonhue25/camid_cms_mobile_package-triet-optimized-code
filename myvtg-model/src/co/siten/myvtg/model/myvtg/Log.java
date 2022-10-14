package co.siten.myvtg.model.myvtg;

import co.siten.myvtg.bean.BaseRequest;
import co.siten.myvtg.bean.BaseResponseBean;
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
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.dto.ResponseDto;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import javax.persistence.Basic;
import javax.persistence.Lob;

/**
 * The persistent class for the "LOG" database table.
 *
 */
@Entity
@Table(name = "LOG")
@NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "LOG_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "SESSION_ID")
    private String sessionId;
    @Column(name = "API_KEY")
    private String apiKey;
    @Column(name = "WS_CODE")
    private String wsCode;
    @Column(name = "STA_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date staTime;
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "ERROR_CODE")
    private String errorCode;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "INSERT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Column(name = "REQUEST")
    private String request;
    @Column(name = "STAFF_CODE")
    private String staffCode;
    @Column(name = "SHOP_CODE")
    private String shopCode;
    @Lob
    @Column(name = "RESPONSE")
    private String response;
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "DEVICE_ID")
    private String deviceId;
    
    @Column(name = "VERSION_APP")
    private String versionApp;
    
    public Log() {
    }
    
    public Log(RequestBean request, ResponseBean bean) {
        if (request != null) {
            //daibq fix
            String rqStr = CommonUtil.convertObjectToJsonString(request);
            if (rqStr.length() > 4000) {
                rqStr = rqStr.substring(0, 3050);
            }
            setApiKey(request.getApiKey());
            setErrorCode(bean.getErrorCode());
            setInsertDate(CommonUtil.getCurrentTime());
            String msg = bean.getMessage();
            if (msg != null) {
                msg = msg.length() > 4000 ? msg.substring(0, 3050) : msg;
            }
            setMessage(msg);
            setRequest(rqStr);
            setResponse(CommonUtil.convertObjectToJsonString(bean));
            setSessionId(request.getSessionId() != null ? request.getSessionId().toString() : "");
            setWsCode(request.getWsCode());
            setStaTime(request.getStartTime());
            setEndTime(CommonUtil.getCurrentTime());
            setShopCode(Constants.MYVTG);
            setStaffCode(Constants.MYVTG);
            setUserName(request.getUsername() == null || request.getUsername().isEmpty() ? "" : request.getUsername());
            setDeviceId(request.getWsRequest().get("deviceId") == null ? "" : request.getWsRequest().get("deviceId").toString());
            setVersionApp(request.getWsRequest().get("versionApp") == null ? "" : request.getWsRequest().get("versionApp").toString());
        }
    }

    /**
     * @author daibq Log BaseResponseBean
     * @param request
     * @param bean
     */
    public Log(RequestBean request, BaseResponseBean bean) {
        if (request != null) {
            //daibq fix
            String rqStr = CommonUtil.convertObjectToJsonString(request);
            if (rqStr.length() > 4000) {
                rqStr = rqStr.substring(0, 3050);
            }
            setApiKey(request.getApiKey() == null || request.getApiKey().isEmpty() ? "" : request.getApiKey());
            setRequest(rqStr);
            setSessionId(request.getSessionId() != null ? request.getSessionId().toString() : "");
            setWsCode(request.getWsCode());
            setStaTime(request.getStartTime());
            setShopCode(Constants.MYVTG);
            setStaffCode(Constants.MYVTG);
            setInsertDate(CommonUtil.getCurrentTime());
            setErrorCode(bean.getErrorCode());
            String msg = bean.getUserMsg();
            if (bean.getUserMsg() != null) {
                msg = bean.getUserMsg().length() > 4000 ? bean.getUserMsg().substring(0, 3050) : bean.getUserMsg();
            }
            setMessage(msg);
            setResponse(CommonUtil.convertObjectToJsonString(bean));
            setUserName(request.getUsername() == null || request.getUsername().isEmpty() ? "" : request.getUsername());
        }
    }

    /**
     * @author daibq Log ResponseDto
     * @param request
     * @param bean
     */
    public Log(RequestBean request, ResponseDto bean) {
        if (request != null) {
            String rqStr = CommonUtil.convertObjectToJsonString(request);
            if (rqStr.length() > 4000) {
                rqStr = rqStr.substring(0, 4000);
            }
            setApiKey(request.getApiKey() == null || request.getApiKey().isEmpty() ? "" : request.getApiKey());
            setRequest(rqStr);
            setSessionId(request.getSessionId() != null ? request.getSessionId().toString() : "");
            setWsCode(request.getWsCode());
            setStaTime(request.getStartTime());
            setShopCode(Constants.MYVTG);
            setStaffCode(Constants.MYVTG);
            setInsertDate(CommonUtil.getCurrentTime());
            setErrorCode(bean.getErrorCode());
            setMessage(bean.getDescription());
            setResponse(CommonUtil.convertObjectToJsonString(bean));
            setUserName(request.getUsername() == null || request.getUsername().isEmpty() ? "" : request.getUsername());
        }
    }

    /**
     * @author daibq Log
     * @param request
     * @param bean
     */
    public Log(Object[] request, Object[] bean) {
//        if (request != null) {
//            setApiKey(request.getApiKey() == null || request.getApiKey().isEmpty() ? "" : request.getApiKey());
//            setRequest(CommonUtil.convertObjectToJsonString(request));
//            setSessionId(request.getSessionId() != null ? request.getSessionId().toString() : "");
//            setWsCode(request.getWsCode());
//            setStaTime(request.getStartTime());
//            setShopCode(Constants.MYVTG);
//            setStaffCode(Constants.MYVTG);
//            setInsertDate(CommonUtil.getCurrentTime());
//            setErrorCode(bean.getErrorCode());
//            setMessage(bean.getUserMsg());
//            setResponse(CommonUtil.convertObjectToJsonString(bean));
//        }
    }

    /**
     * @author duyth Log
     * @param request
     * @param bean
     */
    public Log(BaseRequest request, BaseResponseBean bean) {
        if (request != null) {
            //daibq fix
            String rqStr = CommonUtil.convertObjectToJsonString(request);
            if (rqStr.length() > 4000) {
                rqStr = rqStr.substring(0, 3050);
            }
            setApiKey(request.getApiKey() == null || request.getApiKey().isEmpty() ? "" : request.getApiKey());
            setRequest(rqStr);
            setSessionId(request.getSessionId() != null ? request.getSessionId().toString() : "");
            setWsCode(request.getWsCode());
            setStaTime(request.getStartTime());
            setShopCode(Constants.MYVTG);
            setStaffCode(Constants.MYVTG);
            setInsertDate(CommonUtil.getCurrentTime());
            setErrorCode(bean.getErrorCode());
            String msg = bean.getUserMsg();
            if (bean.getUserMsg() != null) {
                msg = bean.getUserMsg().length() > 4000 ? bean.getUserMsg().substring(0, 3050) : bean.getUserMsg();
            }
            setMessage(msg);
            setResponse(CommonUtil.convertObjectToJsonString(bean));
            setUserName(request.getUsername() == null || request.getUsername().isEmpty() ? "" : request.getUsername());
        }
    }

    public BigDecimal getId() {
        return id;
    }

    public String getApiKey() {
        return apiKey;
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

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }

}
