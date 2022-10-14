/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import co.siten.myvtg.util.CommonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daibq
 */
@XmlRootElement
public class BaseRequest<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object sessionId;
    private String wsCode;
    private String apiKey;
    private String username;
    private ObjectRequest<T> wsRequest;
    
    @JsonIgnore
    private Date startTime = CommonUtil.getCurrentTime();

    @JsonIgnore
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getSessionId() {
        return sessionId;
    }

    public void setSessionId(Object sessionId) {
        this.sessionId = sessionId;
    }

    public String getWsCode() {
        return wsCode;
    }

    public void setWsCode(String wsCode) {
        this.wsCode = wsCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ObjectRequest<T> getWsRequest() {
        return wsRequest;
    }

    public void setWsRequest(ObjectRequest<T> wsRequest) {
        this.wsRequest = wsRequest;
    }

    public static class ObjectRequest<T> {
        String language;
        LinkedHashMap<String, T> params;

        public String getLanguage() {
            return language;
        }

        public LinkedHashMap<String, T> getParams() {
            return params;
        }
    }
}
