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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "LOG_SEND_MAIL")
@XmlRootElement
public class LogSendMail implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "LOG_SEND_MAIL_SEQ_GENERATOR", sequenceName = "LOG_SEND_MAIL_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_SEND_MAIL_SEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Lob
    @Column(name = "REQUEST")
    private String request;
    @Lob
    @Column(name = "RESPONSE")
    private String response;
    @Size(max = 200)
    @Column(name = "PROGRAM_CODE")
    private String programCode;
    @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Size(max = 20)
    @Column(name = "ERROR_CODE")
    private String errorCode;
    @Size(max = 200)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Size(max = 300)
    @Column(name = "CUST_NAME")
    private String custName;
    @Size(max = 20)
    @Column(name = "CUST_PHONE")
    private String custPhone;
    @Size(max = 1000)
    @Column(name = "CUST_ADDR")
    private String custAddr;
    @Size(max = 200)
    @Column(name = "PACKAGE")
    private String packageStr;
    @Size(max = 100)
    @Column(name = "MONTHLY_CHARGE")
    private String monthlyCharge;
    @Size(max = 50)
    @Column(name = "PAY_ADVANCE")
    private String payAdvance;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "SENDER")
    private String sender;
    @Lob
    @Column(name = "RECIEVERS")
    private String recievers;
    @Lob
    @Column(name = "CC")
    private String cc;

    public LogSendMail() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setMonthlyCharge(String monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }

    public String getPayAdvance() {
        return payAdvance;
    }

    public void setPayAdvance(String payAdvance) {
        this.payAdvance = payAdvance;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecievers() {
        return recievers;
    }

    public void setRecievers(String recievers) {
        this.recievers = recievers;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogSendMail)) {
            return false;
        }
        LogSendMail other = (LogSendMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.LogSendMail[ id=" + id + " ]";
    }

}
