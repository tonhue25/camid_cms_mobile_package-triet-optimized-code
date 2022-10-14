/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import java.util.Date;

/**
 *
 * @author buiquangdai
 */
public class InviteLogDTO {

    private Long id;
    private String isdnSender;
    private String isdnReciver;
    private Date createDate;
    private String content;
    private Long status;
    private String description;
    private Long statusLogin;
    private String createDateStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsdnSender() {
        return isdnSender;
    }

    public void setIsdnSender(String isdnSender) {
        this.isdnSender = isdnSender;
    }

    public String getIsdnReciver() {
        return isdnReciver;
    }

    public void setIsdnReciver(String isdnReciver) {
        this.isdnReciver = isdnReciver;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(Long statusLogin) {
        this.statusLogin = statusLogin;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }
    
}
