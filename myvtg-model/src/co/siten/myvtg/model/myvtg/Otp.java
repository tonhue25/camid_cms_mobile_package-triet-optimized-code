/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "OTP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otp.findAll", query = "SELECT o FROM Otp o"),
    @NamedQuery(name = "Otp.findById", query = "SELECT o FROM Otp o WHERE o.id = :id"),
    @NamedQuery(name = "Otp.findByIsdn", query = "SELECT o FROM Otp o WHERE o.isdn = :isdn"),
    @NamedQuery(name = "Otp.findByOtp", query = "SELECT o FROM Otp o WHERE o.otp = :otp"),
    @NamedQuery(name = "Otp.findByService", query = "SELECT o FROM Otp o WHERE o.service = :service"),
    @NamedQuery(name = "Otp.findByExpireTime", query = "SELECT o FROM Otp o WHERE o.expireTime = :expireTime"),
    @NamedQuery(name = "Otp.findByStatus", query = "SELECT o FROM Otp o WHERE o.status = :status"),
    @NamedQuery(name = "Otp.findByTotalGetOtp", query = "SELECT o FROM Otp o WHERE o.totalGetOtp = :totalGetOtp"),
    @NamedQuery(name = "Otp.findByCreateDate", query = "SELECT o FROM Otp o WHERE o.createDate = :createDate"),
    @NamedQuery(name = "Otp.findByUpdateDate", query = "SELECT o FROM Otp o WHERE o.updateDate = :updateDate")})
public class Otp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ISDN")
    private String isdn;
    @Size(max = 20)
    @Column(name = "OTP")
    private String otp;
    @Size(max = 500)
    @Column(name = "SERVICE")
    private String service;
    @Column(name = "EXPIRE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "TOTAL_GET_OTP")
    private Long totalGetOtp;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "TOTAL_FAIL")
    private Long totalFail;
    @Column(name = "LOCK_UNTIL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockUntil;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_ALLOW_RESEND")
    private Date timeAllowResend;

    public Otp() {
    }

    public Otp(String id) {
        this.id = id;
    }

    public Otp(String id, String isdn) {
        this.id = id;
        this.isdn = isdn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTotalGetOtp() {
        return totalGetOtp;
    }

    public void setTotalGetOtp(Long totalGetOtp) {
        this.totalGetOtp = totalGetOtp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getLockUntil() {
        return lockUntil;
    }

    public void setLockUntil(Date lockUntil) {
        this.lockUntil = lockUntil;
    }

    public Date getTimeAllowResend() {
        return timeAllowResend;
    }

    public void setTimeAllowResend(Date timeAllowResend) {
        this.timeAllowResend = timeAllowResend;
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
        if (!(object instanceof Otp)) {
            return false;
        }
        Otp other = (Otp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Long getTotalFail() {
        return totalFail;
    }

    public void setTotalFail(Long totalFail) {
        this.totalFail = totalFail;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.Otp[ id=" + id + " ]";
    }

}
