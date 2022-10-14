/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 *
 * @author buiquangdai
 */
@JsonInclude(Include.NON_NULL)
public class ResponseDto {

    private String errorCode;
    private String description;
    private String content;
    private Long type;
    private String isdnTmp;
    private List<ResultDto> resultList;
    private List<Object> listItem;
    private List<DetailResultDTO> lstResult;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ResultDto> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultDto> resultList) {
        this.resultList = resultList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ResponseDto(String errorCode, String description, String content) {
        this.errorCode = errorCode;
        this.description = description;
        this.content = content;
    }

    public ResponseDto(String errorCode, String description, List<ResultDto> resultList) {
        this.errorCode = errorCode;
        this.description = description;
        this.resultList = resultList;
    }

    public ResponseDto(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public ResponseDto() {
    }

    public ResponseDto(String errorCode, String description, Long type, List<ResultDto> resultList) {
        this.errorCode = errorCode;
        this.description = description;
        this.type = type;
        this.resultList = resultList;
    }

    public ResponseDto(String errorCode, String description, String content, Long type, String isdnTmp) {
        this.errorCode = errorCode;
        this.description = description;
        this.content = content;
        this.type = type;
        this.isdnTmp = isdnTmp;
    }

    public String getIsdnTmp() {
        return isdnTmp;
    }

    public void setIsdnTmp(String isdnTmp) {
        this.isdnTmp = isdnTmp;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public List<Object> getListItem() {
        return listItem;
    }

    public void setListItem(List<Object> listItem) {
        this.listItem = listItem;
    }

    public List<DetailResultDTO> getLstResult() {
        return lstResult;
    }

    public void setLstResult(List<DetailResultDTO> lstResult) {
        this.lstResult = lstResult;
    }

}
