/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "LOG_EMONEY_GAME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogEmoneyGame.findAll", query = "SELECT l FROM LogEmoneyGame l")
    , @NamedQuery(name = "LogEmoneyGame.findByProgramCode", query = "SELECT l FROM LogEmoneyGame l WHERE l.programCode = :programCode")
    , @NamedQuery(name = "LogEmoneyGame.findByLuckyId", query = "SELECT l FROM LogEmoneyGame l WHERE l.luckyId = :luckyId")
    , @NamedQuery(name = "LogEmoneyGame.findByError", query = "SELECT l FROM LogEmoneyGame l WHERE l.error = :error")
    , @NamedQuery(name = "LogEmoneyGame.findByCreareDate", query = "SELECT l FROM LogEmoneyGame l WHERE l.creareDate = :creareDate")
    , @NamedQuery(name = "LogEmoneyGame.findByExpriredDate", query = "SELECT l FROM LogEmoneyGame l WHERE l.expriredDate = :expriredDate")
    , @NamedQuery(name = "LogEmoneyGame.findByUpdateDate", query = "SELECT l FROM LogEmoneyGame l WHERE l.updateDate = :updateDate")
    , @NamedQuery(name = "LogEmoneyGame.findByStatus", query = "SELECT l FROM LogEmoneyGame l WHERE l.status = :status")
    , @NamedQuery(name = "LogEmoneyGame.findByAccount", query = "SELECT l FROM LogEmoneyGame l WHERE l.account = :account")
    , @NamedQuery(name = "LogEmoneyGame.findByAccountReceiver", query = "SELECT l FROM LogEmoneyGame l WHERE l.accountReceiver = :accountReceiver")
    , @NamedQuery(name = "LogEmoneyGame.findByAmount", query = "SELECT l FROM LogEmoneyGame l WHERE l.amount = :amount")
    , @NamedQuery(name = "LogEmoneyGame.findByCurrCode", query = "SELECT l FROM LogEmoneyGame l WHERE l.currCode = :currCode")
    , @NamedQuery(name = "LogEmoneyGame.findByDescription", query = "SELECT l FROM LogEmoneyGame l WHERE l.description = :description")})
public class LogEmoneyGame implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 200)
    @Column(name = "PROGRAM_CODE")
    private String programCode;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "LUCKY_ID")
    private String luckyId;
    @Lob
    @Column(name = "REQUEST")
    private String request;
    @Lob
    @Column(name = "RESPONSE")
    private String response;
    @Size(max = 20)
    @Column(name = "ERROR")
    private String error;
    @Column(name = "CREARE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creareDate;
    @Column(name = "EXPRIRED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expriredDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "STATUS")
    private Long status;
    @Size(max = 20)
    @Column(name = "ACCOUNT")
    private String account;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNT_RECEIVER")
    private String accountReceiver;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private Double amount;
    @Size(max = 20)
    @Column(name = "CURR_CODE")
    private String currCode;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TID")
    private String paidTid;
    @Column(name = "REF_ID")
    private String refId;

    public LogEmoneyGame() {
    }

    public LogEmoneyGame(String luckyId) {
        this.luckyId = luckyId;
    }

    public LogEmoneyGame(String luckyId, String accountReceiver) {
        this.luckyId = luckyId;
        this.accountReceiver = accountReceiver;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getLuckyId() {
        return luckyId;
    }

    public void setLuckyId(String luckyId) {
        this.luckyId = luckyId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getCreareDate() {
        return creareDate;
    }

    public void setCreareDate(Date creareDate) {
        this.creareDate = creareDate;
    }

    public Date getExpriredDate() {
        return expriredDate;
    }

    public void setExpriredDate(Date expriredDate) {
        this.expriredDate = expriredDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountReceiver() {
        return accountReceiver;
    }

    public void setAccountReceiver(String accountReceiver) {
        this.accountReceiver = accountReceiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.LogEmoneyGame[ luckyId=" + luckyId + " ]";
    }

    public String getPaidTid() {
        return paidTid;
    }

    public void setPaidTid(String paidTid) {
        this.paidTid = paidTid;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

}
