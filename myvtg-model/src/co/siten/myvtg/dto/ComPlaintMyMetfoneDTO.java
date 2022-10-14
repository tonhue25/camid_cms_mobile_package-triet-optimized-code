/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author buiquangdai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComPlaintMyMetfoneDTO {
    //complaintmymetfone

    private String isRightInfor;
    private List<Object> listComType;
    private List<Object> listProcess;
    private List<Object> listComplaintHistory;
    private List<Object> listComplaintFilter;
    private Object complaintConfirm;

    public String getIsRightInfor() {
        return isRightInfor;
    }

    public void setIsRightInfor(String isRightInfor) {
        this.isRightInfor = isRightInfor;
    }

    public List<Object> getListComType() {
        return listComType;
    }

    public void setListComType(List<Object> listComType) {
        this.listComType = listComType;
    }

    public List<Object> getListProcess() {
        return listProcess;
    }

    public void setListProcess(List<Object> listProcess) {
        this.listProcess = listProcess;
    }

    public List<Object> getListComplaintHistory() {
        return listComplaintHistory;
    }

    public void setListComplaintHistory(List<Object> listComplaintHistory) {
        this.listComplaintHistory = listComplaintHistory;
    }

    public List<Object> getListComplaintFilter() {
        return listComplaintFilter;
    }

    public void setListComplaintFilter(List<Object> listComplaintFilter) {
        this.listComplaintFilter = listComplaintFilter;
    }

    public Object getComplaintConfirm() {
        return complaintConfirm;
    }

    public void setComplaintConfirm(Object complaintConfirm) {
        this.complaintConfirm = complaintConfirm;
    }
}
