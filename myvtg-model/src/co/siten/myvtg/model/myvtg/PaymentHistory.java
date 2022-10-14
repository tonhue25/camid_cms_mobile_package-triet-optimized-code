/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author anjav
 */
@Entity
@Table(name = "PAYMENT_HISTORY")
public class PaymentHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PAYMENT_HISTORY_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currentBalance;

    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "TID")
    private long tid;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "PAYMENT_ACCOUNT")
    private String paymentAccount;

    @Column(name = "FTTH_TYPE")
    private String ftthType;

    @Column(name = "ID_BILL")
    private String idBill;

    @Column(name = "FTTH_ACCOUNT")
    private String ftthAccount;

    @Column(name = "FTTH_NAME")
    private String ftthName;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "CONTRACT_ID")
    private String contractIdInfo;

    public String getContractIdInfo() {
        return contractIdInfo;
    }

    public void setContractIdInfo(String contractIdInfo) {
        this.contractIdInfo = contractIdInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getFtthType() {
        return ftthType;
    }

    public void setFtthType(String ftthType) {
        this.ftthType = ftthType;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getFtthAccount() {
        return ftthAccount;
    }

    public void setFtthAccount(String ftthAccount) {
        this.ftthAccount = ftthAccount;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFtthName() {
        return ftthName;
    }

    public void setFtthName(String ftthName) {
        this.ftthName = ftthName;
    }

}
