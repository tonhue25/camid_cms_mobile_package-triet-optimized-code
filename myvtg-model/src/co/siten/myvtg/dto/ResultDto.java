/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author buiquangdai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto {

    private String isdn;
    private Long status;
    private String content;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ResultDto(String isdn, Long status) {
        this.isdn = isdn;
        this.status = status;
    }

    public ResultDto(String isdn, Long status, String content) {
        this.isdn = isdn;
        this.status = status;
        this.content = content;
    }

    public ResultDto() {
    }
}
