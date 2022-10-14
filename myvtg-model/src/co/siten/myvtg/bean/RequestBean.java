package co.siten.myvtg.bean;

import co.siten.myvtg.util.CommonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author thomc
 *
 */
@XmlRootElement
public class RequestBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Object sessionId;
    private String wsCode;
    private String apiKey;
    private String username;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private LinkedHashMap<String, Object> wsRequest;
//    private List<Object> result;
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

    public LinkedHashMap<String, Object> getWsRequest() {
        return wsRequest;
    }

    public void setWsRequest(LinkedHashMap<String, Object> wsRequest) {
        this.wsRequest = wsRequest;
    }

//    public List<Object> getResult() {
//        return result;
//    }
//
//    public void setResult(List<Object> result) {
//        this.result = result;
//    }
    
    
}
