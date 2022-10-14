/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author daibq
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceOuput {
    private Long status;
    private String code;
    private String message;
    private TxDetail txDetail;

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TxDetail getTxDetail() {
        return txDetail;
    }

    public void setTxDetail(TxDetail txDetail) {
        this.txDetail = txDetail;
    }
    
}
