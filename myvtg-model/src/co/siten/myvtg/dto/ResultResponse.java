package co.siten.myvtg.dto;

import javax.validation.Valid;

public class ResultResponse<T> {
    private String errorCode;
    private String message;
    private String object;
    private String userMsg;
    @Valid
    private T wsResponse;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    //    @JsonProperty("wsResponse")
    public T getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(T wsResponse) {
        this.wsResponse = wsResponse;
    }
}
