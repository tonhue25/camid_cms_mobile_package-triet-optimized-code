/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.precall;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thomc
 */
@Entity
@Table(name = "V_SELFCARE_DETAIL_VAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VSelfcareDetailVas.findAll", query = "SELECT v FROM VSelfcareDetailVas v"),
    @NamedQuery(name = "VSelfcareDetailVas.findByCallingNumber", query = "SELECT v FROM VSelfcareDetailVas v WHERE v.callingNumber = :callingNumber"),
    @NamedQuery(name = "VSelfcareDetailVas.findByCalledNumber", query = "SELECT v FROM VSelfcareDetailVas v WHERE v.calledNumber = :calledNumber"),
    @NamedQuery(name = "VSelfcareDetailVas.findByStartTime", query = "SELECT v FROM VSelfcareDetailVas v WHERE v.startTime = :startTime"),
    @NamedQuery(name = "VSelfcareDetailVas.findByDuration", query = "SELECT v FROM VSelfcareDetailVas v WHERE v.duration = :duration"),
    @NamedQuery(name = "VSelfcareDetailVas.findByBasicBalance", query = "SELECT v FROM VSelfcareDetailVas v WHERE v.basicBalance = :basicBalance"),
    @NamedQuery(name = "VSelfcareDetailVas.findByPromBalance", query = "SELECT v FROM VSelfcareDetailVas v WHERE v.promBalance = :promBalance")})
public class VSelfcareDetailVas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 21)
    @Column(name = "CALLING_NUMBER")
    private String callingNumber;
    @Size(max = 21)
    @Column(name = "CALLED_NUMBER")
    private String calledNumber;
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "DURATION")
    private Integer duration;
    @Size(max = 20)
    @Column(name = "BASIC_BALANCE")
    private BigDecimal basicBalance;
    @Column(name = "PROM_BALANCE")
    private BigDecimal promBalance;

    public VSelfcareDetailVas() {
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

    public BigDecimal getBasicBalance() {
        return basicBalance;
    }

    public void setBasicBalance(BigDecimal basicBalance) {
        this.basicBalance = basicBalance;
    }

    public BigDecimal getPromBalance() {
        return promBalance;
    }

    public void setPromBalance(BigDecimal promBalance) {
        this.promBalance = promBalance;
    }
    
}
