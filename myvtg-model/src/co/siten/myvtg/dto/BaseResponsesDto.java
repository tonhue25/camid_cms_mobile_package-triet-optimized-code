/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author buiquangdai
 */
public class BaseResponsesDto {
    private Integer statusCode ;
    private String messageCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    

    public BaseResponsesDto() {
    }

    public BaseResponsesDto(Integer statusCode, String messageCode) {
        this.statusCode = statusCode;
        this.messageCode = messageCode;
    }

}
