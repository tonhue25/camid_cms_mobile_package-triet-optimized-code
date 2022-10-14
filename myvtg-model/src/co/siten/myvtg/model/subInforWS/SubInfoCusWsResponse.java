/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.subInforWS;

import java.util.List;

/**
 * SubInfoCusWsResponse
 *
 * @author partner7
 */
public class SubInfoCusWsResponse {

    private String errorCode;
    private String errorDescription;
    private String messageCode;
    private Cus cus;

    public SubInfoCusWsResponse() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Cus getCus() {
        return cus;
    }

    public void setCus(Cus cus) {
        this.cus = cus;
    }
}
