/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author daibq
 */
@JsonInclude(Include.NON_NULL)
public class RequestDto {

    @JsonProperty("isdn")
    private String isdn;
    @JsonProperty("subId")
    private Long subId;
    @JsonProperty("idNo")
    private String idNo;
    @JsonProperty("custId")
    private Long custId;
    @JsonProperty("language")
    private String language;
    @JsonProperty("sDatetime")
    private String date;

    //tru diem changeLoyalty
    @JsonProperty("pointAmount")
    private String pointAmount;
    @JsonProperty("pointId")
    private String pointId;
    @JsonProperty("transTypeId")
    private String transTypeId;
    @JsonProperty("isdns")
    private String isdns;
    @JsonProperty("transId")
    private String transId;
    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public RequestDto() {
    }

    public RequestDto(String isdn, Long subId, String date) {
        this.isdn = isdn;
        this.subId = subId;
        this.date = date;
    }

    public RequestDto(String isdn, Long subId, String language, String date) {
        this.isdn = isdn;
        this.subId = subId;
        this.language = language;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RequestDto(String isdn, Long subId, Long custId, String language, String date) {
        this.isdn = isdn;
        this.subId = subId;
        this.custId = custId;
        this.language = language;
        this.date = date;
    }

    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(String transTypeId) {
        this.transTypeId = transTypeId;
    }

    public String getIsdns() {
        return isdns;
    }

    public void setIsdns(String isdns) {
        this.isdns = isdns;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
    

}
