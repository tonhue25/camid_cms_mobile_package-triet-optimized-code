package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TRANSACTION_LOG database table.
 *
 */
@Entity
@Table(name = "TRANSACTION_LOG")
@NamedQuery(name = "TransactionLog.findAll", query = "SELECT t FROM TransactionLog t")
public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "TRANSACTION_LOG_SEQ", allocationSize = 1)
    private BigDecimal id;
    private String channel;

    @Column(name = "\"COMMAND\"")
    private String command;

    @Column(name = "ERROR_CODE")
    private String errorCode;

    private String msisdn;

    private String request;

    @Temporal(TemporalType.DATE)
    @Column(name = "REQUEST_TIME")
    private Date requestTime;

    private String response;

    @Temporal(TemporalType.DATE)
    @Column(name = "RESPONSE_TIME")
    private Date responseTime;

    @Column(name = "RESULT_VALUE")
    private String resultValue;

    @Column(name = "\"TYPE\"")
    private String type;
    @Transient
    private String serial;
    @Transient
    private Double money;

    @Column(name = "EXTRA_INFO")
    private String extraInfo;

    @Column(name = "REFILL_ISDN")
    private String refillIsdn;

    @Column(name = "SERI_NUMBER")
    private String seriNumber;

    public String getSerial() {
        return serial;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public TransactionLog() {
    }

    public String getChannel() {
        return this.channel;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Date getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getResultValue() {
        return this.resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getRefillIsdn() {
        return refillIsdn;
    }

    public void setRefillIsdn(String refillIsdn) {
        this.refillIsdn = refillIsdn;
    }

    public String getSeriNumber() {
        return seriNumber;
    }

    public void setSeriNumber(String seriNumber) {
        this.seriNumber = seriNumber;
    }

}
