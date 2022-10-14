package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

// save log in myvtg when camid request emoney
@Entity
@Table(name = "EMONEY_LOG")
public class EmoneyLog implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "EMONEY_LOG_SEQ_GENERATOR", sequenceName = "EMONEY_LOG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMONEY_LOG_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "REQUEST")
    private String request;
    @Column(name = "RESPONSE")
    private String response;
    @Column(name = "ERROR_CODE")
    private String errorCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ACCOUNT_NO")
    private String accountNo;
    @Column(name = "TRANS_ID")
    private String transId;
    @Column(name = "TOKEN")
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
