/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.precall;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author thomc
 */
@Entity
@Table(name = "V_SELFCARE_DETAIL_CALL", catalog = "", schema = "PRE_CALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VSelfcareDetailCall.findAll", query = "SELECT v FROM VSelfcareDetailCall v"),
    @NamedQuery(name = "VSelfcareDetailCall.findByCallingNumber", query = "SELECT v FROM VSelfcareDetailCall v WHERE v.callingNumber = :callingNumber"),
    @NamedQuery(name = "VSelfcareDetailCall.findByCalledNumber", query = "SELECT v FROM VSelfcareDetailCall v WHERE v.calledNumber = :calledNumber"),
    @NamedQuery(name = "VSelfcareDetailCall.findByStartTime", query = "SELECT v FROM VSelfcareDetailCall v WHERE v.startTime = :startTime"),
    @NamedQuery(name = "VSelfcareDetailCall.findByDuration", query = "SELECT v FROM VSelfcareDetailCall v WHERE v.duration = :duration"),
    @NamedQuery(name = "VSelfcareDetailCall.findByBasicBalance", query = "SELECT v FROM VSelfcareDetailCall v WHERE v.basicBalance = :basicBalance"),
    @NamedQuery(name = "VSelfcareDetailCall.findByPromBalance", query = "SELECT v FROM VSelfcareDetailCall v WHERE v.promBalance = :promBalance")})
public class VSelfcareDetailCall implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 21)
    @Column(name = "CALLING_NUMBER")
    private String callingNumber;
    @Size(max = 21)
    @Column(name = "CALLED_NUMBER")
    private String calledNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "DURATION")
    private Integer duration;
    @Size(max = 20)
    @Column(name = "BASIC_BALANCE")
    private String basicBalance;
    @Column(name = "PROM_BALANCE")
    private BigInteger promBalance;

    public VSelfcareDetailCall() {
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getBasicBalance() {
        return basicBalance;
    }

    public void setBasicBalance(String basicBalance) {
        this.basicBalance = basicBalance;
    }

    public BigInteger getPromBalance() {
        return promBalance;
    }

    public void setPromBalance(BigInteger promBalance) {
        this.promBalance = promBalance;
    }
    
}
