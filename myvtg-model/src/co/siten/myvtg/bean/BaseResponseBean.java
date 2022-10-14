/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * @author buiquangdai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseBean implements Serializable {
    private String errorCode;
    private String message;
    private Object object;
    private String userMsg;
    private Object wsResponse;

    public BaseResponseBean(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public void setWsResponse(List<Object> wsResponse) {
        this.wsResponse = wsResponse;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public BaseResponseBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public BaseResponseBean(String errorCode, String message, String userMsg) {
        this.errorCode = errorCode;
        this.message = message;
        this.userMsg = userMsg;
    }

    public BaseResponseBean(String errorCode, String message, String userMsg, Object wsResponse) {
        this.errorCode = errorCode;
        this.message = message;
        this.userMsg = userMsg;
        this.wsResponse = wsResponse;
    }

    public Object getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(Object wsResponse) {
        this.wsResponse = wsResponse;
    }
}
