/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author daibq
 */
@JsonInclude(Include.NON_NULL)
public class BaseRequestDto {

    private Object wsRequest;

    public Object getWsRequest() {
        return wsRequest;
    }

    public void setWsRequest(Object wsRequest) {
        this.wsRequest = wsRequest;
    }

    public BaseRequestDto(Object wsRequest) {
        this.wsRequest = wsRequest;
    }

    public BaseRequestDto() {
    }

}
