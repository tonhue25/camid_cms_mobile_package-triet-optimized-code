/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.exception;

/**
 *
 * @author duyth
 */
public class MyMetfoneException extends Exception {

    private String errorCode;
    private String message;
    private String userMsg;
    
    
    public MyMetfoneException(String errorCode, String message, String userMsg) {
        this.errorCode = errorCode;
        this.message = message;
        this.userMsg = userMsg;
    }

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

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }
    
    
}
