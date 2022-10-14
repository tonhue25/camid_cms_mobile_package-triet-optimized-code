package co.siten.myvtg.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.siten.myvtg.util.Constants;

/**
 *
 * @author thomc
 *
 */
public class ResponseErrorBean extends ResponseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private BigDecimal logId;
    private String msg;

    @JsonIgnore
    public BigDecimal getLogId() {
        return logId;
    }

    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }

    public ResponseErrorBean() {
        errorCode = Constants.ERROR_CODE_1;
    }

    public ResponseErrorBean(Exception e) {
        message = e.getMessage();
        errorCode = Constants.ERROR_CODE_1;
        userMsg = Constants.ERROR_500;
    }

    public ResponseErrorBean(Exception e, String code) {
        message = e.getMessage();
        errorCode = Constants.ERROR_CODE_1;
        userMsg = code;
    }

    /**
     * dai bq bo sung
     *
     * @param msg
     * @param code
     */
    public ResponseErrorBean(String msg, String code) {
        message = msg;
        errorCode = Constants.ERROR_CODE_1;
        userMsg = code;
    }

    /**
     * dai bq bo sung
     *
     * @param msg
     * @param errorMsg
     * @param error
     */
    public ResponseErrorBean(String msg, String errorMsg, String error) {
        message = msg;
        errorCode = error;
        userMsg = errorMsg;
    }
}
